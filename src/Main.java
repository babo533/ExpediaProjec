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
        dataManager.addUser(role, username, password);
        bff.print("User registered successfully.");
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
            // ... handle other user types ...
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
            System.out.println((i + 1) + ". " + key + ": " + details); // Display index and flight details
        }

        int flightIndex;
        try {
            flightIndex = bff.inputInt("Enter the number of the flight to book (or 0 to return):", 0, flightKeys.size());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }

        if (flightIndex > 0 && flightIndex <= flightKeys.size()) {
            return flights.get(flightKeys.get(flightIndex - 1));
        } else {
            System.out.println("Returning to previous menu or invalid selection.");
            return null;
        }
    }





    private static Service selectHotelService() {
        Map<String, RoomDetails> rooms = hotelService.getRoomInventory();
        rooms.forEach((key, value) -> System.out.println(key + ": " + value));

        String roomKey = bff.input("Enter the key of the room to book or 'back' to return:");
        if (!"back".equalsIgnoreCase(roomKey) && rooms.containsKey(roomKey)) {
            return rooms.get(roomKey);
        } else {
            System.out.println("Invalid selection or no such room.");
            return null;
        }
    }


    private static Service selectCarRentalService() {
        Map<String, CarDetails> cars = rentalCarService.getCarInventory();
        cars.forEach((key, value) -> System.out.println(key + ": " + value));

        String carKey = bff.input("Enter the key of the car to rent or 'back' to return:");
        if (!"back".equalsIgnoreCase(carKey) && cars.containsKey(carKey)) {
            return cars.get(carKey);
        } else {
            System.out.println("Invalid selection or no such car.");
            return null;
        }
    }



    public void handleServiceAddition() {
        int choice = bff.inputInt("Enter choice (1 for flights, 2 for hotels, 3 for cars)");

        if (choice == 1) {
            //addFlightDetails();
        }
        // Handle other choices similarly
    }




    // ... other methods ...

}

