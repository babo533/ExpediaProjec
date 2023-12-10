import java.io.*;
import java.util.*;
/**
 * The Main class is the entry point of the service booking application.
 * It handles user interactions, including sign up, login, and service selection.
 *  * @author Seung Hoon Lee
 *  ITP 265, tea
 *   Email: slee3471@usc.edu
 */
public class Main {
    private static BFF bff = new BFF();
    private static DataManager dataManager = new DataManager(); // Assuming DataManager handles user data
    private static FlightService flightService; // the ide told me to do it like this ?

    static {
        try {
            flightService = new FlightService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static RentalCarService rentalCarService; // the ide told me to do it like this ?

    static {
        try {
            rentalCarService = new RentalCarService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HotelService hotelService; // the ide told me to do it like this ?

    static {
        try {
            hotelService = new HotelService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        bff.printFancy("Welcome to Expedia Clone");

        while (true) {
            String choice = bff.input("Choose an option:\n1. Sign Up\n2. Login\n3. Exit", "1", "2", "3");

            switch (choice) {
                case "1":
                    signUp();
                    break;
                case "2":
                    User user = login();
                    if (user != null) {
                        showUserMenu(user);
                    }
                    break;
                case "3":
                    bff.print("Exiting...");
                    return;
                default:
                    bff.printRed("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Handles the process of user registration.
     * It prompts the user for a username, password, and role, and registers a new user if the username doesn't already exist.
     */
    private static void signUp() {
        // Prompt for user information
        String username = bff.input("Enter username");
        String password = bff.input("Enter password");
        String role = bff.input("Enter role (Admin/Premium/Regular)", "Admin", "Premium", "Regular");

        // Check if username already exists
        if (dataManager.usernameExists(username)) {
            bff.print("Username already exists. Please choose a different username.");
        } else {
            // Add new user to the file
            dataManager.addUser(role, username, password);
            bff.print("User registered successfully.");
        }
    }

    /**
     * Handles the process of user login.
     * It prompts the user for a username and password and attempts to authenticate the user.
     *
     * @return The authenticated {@code User} object if login is successful, or {@code null} if it fails.
     */
    private static User login() {
        // Prompt for login credentials
        String username = bff.input("Enter username");
        String password = bff.input("Enter password");

        // Attempt to authenticate the user
        User user = dataManager.authenticateUser(username, password);
        if (user == null) {
            // Inform the user if login fails
            bff.printRed("Login failed. Invalid username or password.");
        } else {
            // Welcome message on successful login
            bff.printFancy("Login successful. Welcome, " + user.getUsername());
        }
        return user; // Return the authenticated user or null
    }

    /**
     * Displays a user-specific menu and handles user interactions based on their type (e.g., PremiumUser, AdminUser).
     * The method provides different options for different user types and processes the user's choices.
     *
     * @param user The user for whom the menu is displayed and interactions are handled.
     */
    private static void showUserMenu(User user) {
        boolean isSessionActive = true;

        while (isSessionActive) {
            if (user instanceof PremiumUser) {
                String choice = bff.input("Select an option:\n1. Book a Service\n2. View Booked Services\n3. Logout", "1", "2", "3");
                switch (choice) {
                    case "1":
                        // Call method to handle booking for premium user
                        Service selectedService = selectService(); // Implement this method
                        user.getUserTypeStrategy().bookService(user, selectedService);
                        dataManager.bookServiceForUser(user, selectedService);
                        break;
                    case "2":
                        // Call method to view booked services for premium user
                        user.getUserTypeStrategy().viewBookedServices(user);
                        break;
                    case "3":
                        isSessionActive = false; // Logout
                        break;
                }
            }
            else if (user instanceof AdminUser) {
                AdminUser adminUser = (AdminUser) user;
                String adminChoice = bff.input("Select an option:\n1. View All Users\n2. View All Booked Services\n3. Add Flight\n4. Add Car\n5. Add Hotel\n6. Logout", "1", "2", "3", "4", "5", "6");
                switch (adminChoice) {
                    case "1":
                        adminUser.printAllUsers();
                        break;
                    case "2":
                        adminUser.viewAllBookedServices();
                        break;
                    case "3":
                        adminUser.addFlightAsAdmin(flightService);
                        break;
                    case "4":
                        adminUser.addCarsAsAdmin(rentalCarService);
                        break;
                    case "5":
                        adminUser.addHotelsAsAdmin(hotelService);
                        break;
                    case "6":
                        isSessionActive = false; // Logout
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                // ... handle other user types ...
            }
            else {
                String choice = bff.input("Select an option:\n1. Book a Service\n2. View Booked Services\n3. Logout", "1", "2", "3");
                switch (choice) {
                    case "1":
                        // Call method to handle booking for regular user
                        Service selectedService = selectService(); // Implement this method
                        user.getUserTypeStrategy().bookService(user, selectedService);
                        dataManager.bookServiceForUser(user, selectedService);
                        break;
                    case "2":
                        // Call method to view booked services for premium user
                        user.getUserTypeStrategy().viewBookedServices(user);
                        break;
                    case "3":
                        isSessionActive = false; // Logout
                        break;
                }
            }
        }
    }

    /**
     * Presents options for the user to choose a service type (flight, hotel, or car rental) and returns the selected service.
     * The user is prompted to choose a type of service and then directed to the corresponding selection method.
     *
     * @return A {@code Service} instance corresponding to the user's choice, or {@code null} if an invalid selection is made.
     */
    private static Service selectService() {
        String serviceType = bff.input("Choose a service to book:\n1. Flight\n2. Hotel\n3. Car Rental", "1", "2", "3");
        Service selectedService = null;

        switch (serviceType) {
            case "1":
                selectedService = selectFlightService();
                break;
            case "2":
                selectedService = selectHotelService();
                break;
            case "3":
                selectedService = selectCarRentalService();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                break;
        }

        return selectedService;
    }

    /**
     * Presents a list of available flights and allows the user to choose one.
     * The user is shown a list of flights from the flight inventory and can select one to book.
     * If there are no flights available, a message is displayed and the method returns {@code null}.
     *
     * @return A {@code FlightServiceHelper} instance for the selected flight, or {@code null} if no flight is selected or available.
     */
    private static Service selectFlightService() {
        Map<String, FlightDetails> flights = flightService.getFlightInventory();
        // Check if the flight inventory is empty
        if (flights.isEmpty()) {
            System.out.println("No flights are currently available.");
            return null;
        }

        // Display available flights
        System.out.println("Available Flights:");
        List<String> flightKeys = new ArrayList<>(flights.keySet());
        for (int i = 0; i < flightKeys.size(); i++) {
            String key = flightKeys.get(i);
            FlightDetails details = flights.get(key);
            // Display each flight and its details
            System.out.println((i + 1) + ". " + key + ": " + details); // FlightDetails should have a meaningful toString() method
        }

        // Prompt user to select a flight
        int flightIndex = -1;
        while (flightIndex < 1 || flightIndex > flightKeys.size()) {
            try {
                // Get user input and validate it
                flightIndex = bff.inputInt("Enter the number of the flight to book (or 0 to return):", 0, flightKeys.size());
                if (flightIndex == 0) {
                    System.out.println("Returning to previous menu.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Get the selected flight's details
        FlightDetails selectedFlight = flights.get(flightKeys.get(flightIndex - 1));
        // Return a new FlightServiceHelper instance for the selected flight
        return new FlightServiceHelper(selectedFlight);
    }

    /**
     * Presents a selection of available hotel rooms and allows the user to choose one.
     * The user is prompted to select a room from the list of available rooms in the room inventory.
     * If there are no rooms available, a message is displayed and the method returns {@code null}.
     *
     * @return A {@code HotelServiceHelper} instance for the selected room, or {@code null} if no room is selected or available.
     */
    private static Service selectHotelService() {
        Map<String, RoomDetails> rooms = hotelService.getRoomInventory();
        // Check if the room inventory is empty
        if (rooms.isEmpty()) {
            System.out.println("No hotel rooms are currently available.");
            return null;
        }

        // Display available hotel rooms
        System.out.println("Available Hotel Rooms:");
        List<String> roomKeys = new ArrayList<>(rooms.keySet());
        for (int i = 0; i < roomKeys.size(); i++) {
            String key = roomKeys.get(i);
            RoomDetails details = rooms.get(key);
            // Display each room and its details
            System.out.println((i + 1) + ". " + key + ": " + details); // RoomDetails should have a meaningful toString() method
        }

        // Prompt user to select a room
        int roomIndex = -1;
        while (roomIndex < 1 || roomIndex > roomKeys.size()) {
            try {
                // Get user input and validate it
                roomIndex = bff.inputInt("Enter the number of the room to book (or 0 to return):", 0, roomKeys.size());
                if (roomIndex == 0) {
                    System.out.println("Returning to previous menu.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Get the selected room's details
        RoomDetails selectedRoom = rooms.get(roomKeys.get(roomIndex - 1));
        // Return a new HotelServiceHelper instance for the selected room
        return new HotelServiceHelper(selectedRoom);
    }

    /**
     * Presents a selection of available cars for rental and allows the user to choose one.
     * The user is prompted to select a car from the list of available cars in the car inventory.
     * If there are no cars available, a message is displayed and the method returns {@code null}.
     *
     * @return A {@code CarServiceHelper} instance for the selected car, or {@code null} if no car is selected or available.
     */
    private static Service selectCarRentalService() {
        Map<String, CarDetails> cars = rentalCarService.getCarInventory();
        // Check if the car inventory is empty
        if (cars.isEmpty()) {
            System.out.println("No cars are currently available for rental.");
            return null;
        }

        // Display available cars
        System.out.println("Available Cars for Rental:");
        List<String> carKeys = new ArrayList<>(cars.keySet());
        for (int i = 0; i < carKeys.size(); i++) {
            String key = carKeys.get(i);
            CarDetails details = cars.get(key);
            // Display each car and its details
            System.out.println((i + 1) + ". " + key + ": " + details); // CarDetails should have a meaningful toString() method
        }

        // Prompt user to select a car
        int carIndex = -1;
        while (carIndex < 1 || carIndex > carKeys.size()) {
            try {
                // Get user input and validate it
                carIndex = bff.inputInt("Enter the number of the car to rent (or 0 to return):", 0, carKeys.size());
                if (carIndex == 0) {
                    System.out.println("Returning to previous menu.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Get the selected car's details
        CarDetails selectedCar = cars.get(carKeys.get(carIndex - 1));
        // Return a new CarServiceHelper instance for the selected car
        return new CarServiceHelper(selectedCar);
    }



}

