import java.io.*;
import java.util.*;

public class Main {
    private static BFF bff = new BFF();
    private static DataManager dataManager = new DataManager(); // Assuming DataManager handles user data
    private static FlightService flightService; //

    static {
        try {
            flightService = new FlightService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static RentalCarService rentalCarService;

    static {
        try {
            rentalCarService = new RentalCarService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HotelService hotelService;

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

    private static void signUp() {
        String username = bff.input("Enter username");
        String password = bff.input("Enter password");
        String role = bff.input("Enter role (Admin/Premium/Regular)", "Admin", "Premium", "Regular");

        // Add user to the file
        if (dataManager.usernameExists(username)) {
            bff.print("Username already exists. Please choose a different username.");
        } else {
            dataManager.addUser(role, username, password);
            bff.print("User registered successfully.");
        }

    }

    private static User login() {
        String username = bff.input("Enter username");
        String password = bff.input("Enter password");

        User user = dataManager.authenticateUser(username, password);
        if (user == null) {
            bff.printRed("Login failed. Invalid username or password.");
        } else {
            bff.printFancy("Login successful. Welcome, " + user.getUsername());
        }
        return user;
    }

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

    private static Service selectFlightService() {
        Map<String, FlightDetails> flights = flightService.getFlightInventory();
        if (flights.isEmpty()) {
            System.out.println("No flights are currently available.");
            return null;
        }

        System.out.println("Available Flights:");
        List<String> flightKeys = new ArrayList<>(flights.keySet());
        for (int i = 0; i < flightKeys.size(); i++) {
            String key = flightKeys.get(i);
            FlightDetails details = flights.get(key);
            System.out.println((i + 1) + ". " + key + ": " + details); // Assuming FlightDetails has a meaningful toString() method
        }

        int flightIndex = -1;
        while (flightIndex < 1 || flightIndex > flightKeys.size()) {
            try {
                flightIndex = bff.inputInt("Enter the number of the flight to book (or 0 to return):", 0, flightKeys.size());
                if (flightIndex == 0) {
                    System.out.println("Returning to previous menu.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        FlightDetails selectedFlight = flights.get(flightKeys.get(flightIndex - 1));
        return new FlightServiceHelper(selectedFlight);
    }

    private static Service selectHotelService() {
        Map<String, RoomDetails> rooms = hotelService.getRoomInventory();
        if (rooms.isEmpty()) {
            System.out.println("No hotel rooms are currently available.");
            return null;
        }

        System.out.println("Available Hotel Rooms:");
        List<String> roomKeys = new ArrayList<>(rooms.keySet());
        for (int i = 0; i < roomKeys.size(); i++) {
            String key = roomKeys.get(i);
            RoomDetails details = rooms.get(key);
            System.out.println((i + 1) + ". " + key + ": " + details); // Assuming RoomDetails has a meaningful toString() method
        }

        int roomIndex = -1;
        while (roomIndex < 1 || roomIndex > roomKeys.size()) {
            try {
                roomIndex = bff.inputInt("Enter the number of the room to book (or 0 to return):", 0, roomKeys.size());
                if (roomIndex == 0) {
                    System.out.println("Returning to previous menu.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        RoomDetails selectedRoom = rooms.get(roomKeys.get(roomIndex - 1));
        return new HotelServiceHelper(selectedRoom);
    }


    private static Service selectCarRentalService() {
        Map<String, CarDetails> cars = rentalCarService.getCarInventory();
        if (cars.isEmpty()) {
            System.out.println("No cars are currently available for rental.");
            return null;
        }

        System.out.println("Available Cars for Rental:");
        List<String> carKeys = new ArrayList<>(cars.keySet());
        for (int i = 0; i < carKeys.size(); i++) {
            String key = carKeys.get(i);
            CarDetails details = cars.get(key);
            System.out.println((i + 1) + ". " + key + ": " + details); // Assuming CarDetails has a meaningful toString() method
        }

        int carIndex = -1;
        while (carIndex < 1 || carIndex > carKeys.size()) {
            try {
                carIndex = bff.inputInt("Enter the number of the car to rent (or 0 to return):", 0, carKeys.size());
                if (carIndex == 0) {
                    System.out.println("Returning to previous menu.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        CarDetails selectedCar = cars.get(carKeys.get(carIndex - 1));
        return new CarServiceHelper(selectedCar);
    }



}

