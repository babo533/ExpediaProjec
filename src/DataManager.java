import java.io.*;
import java.util.*;

/**
 * The DataManager class handles operations related to data storage and retrieval.
 * It includes methods to load and save user and service data from and to a file, and to perform various data manipulation tasks.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */

public class DataManager {
    private static final String USER_DATA_FILE = "/Users/hoon/IdeaProjects/A11_FinalProject/src/users.txt"; // Update with actual file path


    /**
     * Loads data from a file and returns it as a hashmap.
     *
     * @param filePath The path of the file to be read.
     * @return A hashmap containing the data from the file.
     */
    public static Map<String, Object> loadFromFile(String filePath) {
        Map<String, Object> dataMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assumes each line is a key-value pair separated by a comma.
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    dataMap.put(parts[0], parts[1]); // Add key-value pair to the map.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    /**
     * Saves data from a hashmap back to a file.
     *
     * @param dataMap  The hashmap containing the data to be saved.
     * @param filePath The path of the file where data will be saved.
     */
    public static void saveToFile(Map<String, Object> dataMap, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue()); // Write key-value pair to file.
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all users from the user data file.
     *
     * @return A list of all users.
     */
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Processing each line to create User objects.
                String[] userDetails = line.split(",", 4);
                if (userDetails.length < 3) {
                    continue;
                }

                // Extracting user details from the line.
                String role = userDetails[0];
                String username = userDetails[1];
                String password = userDetails[2];
                User user = createUser(role, username, password);

                // Handling booked services for the user.
                if (userDetails.length == 4 && userDetails[3] != null && !userDetails[3].isEmpty()) {
                    String[] bookedServices = userDetails[3].split(";");
                    for (String serviceString : bookedServices) {
                        Service bookedService = createServiceFromString(serviceString);
                        if (bookedService != null) {
                            user.addBookedService(bookedService);
                        }
                    }
                }

                users.add(user); // Adding the user to the list.
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return users;
    }

    /**
     * Checks if a username already exists in the user data file.
     *
     * @param username The username to check.
     * @return {@code true} if the username exists, otherwise {@code false}.
     */
    public static boolean usernameExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 2 && userDetails[1].equals(username)) {
                    return true; // Username found.
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return false;
    }


    /**
     * Adds a new user to the user data file.
     *
     * @param role     The role of the new user (e.g., Admin, Premium, Regular).
     * @param username The username of the new user.
     * @param password The password of the new user.
     */
    public static void addUser(String role, String username, String password) {
        try (FileWriter fw = new FileWriter(USER_DATA_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(role + "," + username + "," + password);
        } catch (IOException e) {
            System.err.println("Error writing to user data file: " + e.getMessage());
        }
    }

    /**
     * Authenticates a user by checking the provided username and password against the user data file.
     *
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @return An authenticated User object, or null if authentication fails.
     */
    public static User authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 3) {
                    if (userDetails[1].equals(username) && userDetails[2].equals(password)) {
                        return createUser(userDetails[0], userDetails[1], userDetails[2]);
                    }
                }
                if (userDetails.length >= 3 && userDetails[1].equals(username) && userDetails[2].equals(password)) {
                    User user = createUser(userDetails[0], userDetails[1], userDetails[2]);
                    if (user != null) {
                        // Load booked services
                        for (int i = 3; i < userDetails.length; i++) {
                            Service bookedService = createServiceFromString(userDetails[i]);
                            user.addBookedService(bookedService);
                        }
                    }
                    return user;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return null;
    }

    /**
     * Creates a Service object from a string representation.
     *
     * @param serviceString The string representation of the service.
     * @return A Service object created from the string, or null if the string format is invalid.
     */
    private static Service createServiceFromString(String serviceString) {
        // Split the service string into parts
        String[] parts = serviceString.split(":");
        if (parts.length < 2) {
            return null; // Invalid format
        }

        String serviceName = parts[0];
        double price = Double.parseDouble(parts[1]);

        // Depending on the serviceName, create the appropriate Service object
        switch (serviceName) {
            case "Flight":
                // Assuming FlightServiceHelper takes a FlightDetails object
                // You need to create a FlightDetails object from the string
                return new FlightServiceHelper(createFlightDetailsFromString(parts));
            case "Hotel":
                // Similar logic for Hotel
                return new HotelServiceHelper(createRoomDetailsFromString(parts));
            case "CarRental":
                // Similar logic for Car Rental
                return new CarServiceHelper(createCarDetailsFromString(parts));
            default:
                return null; // Unknown service type
        }
    }

    /**
     * Creates FlightDetails from a string array.
     *
     * @param parts A string array representing flight details.
     * @return A FlightDetails object, or null if the array does not contain sufficient information.
     */
    private static FlightDetails createFlightDetailsFromString(String[] parts) {
        if (parts.length < 5) {
            return null; // Not enough information
        }
        FlightType flightType = FlightType.valueOf(parts[2]);
        String departureLocation = parts[3];
        String arrivalLocation = parts[4];
        double price = Double.parseDouble(parts[1]);
        return new FlightDetails(flightType, departureLocation, arrivalLocation, price);
    }

    /**
     * Creates RoomDetails from a string array.
     *
     * @param parts A string array representing room details.
     * @return A RoomDetails object, or null if the array does not contain sufficient information.
     */
    private static RoomDetails createRoomDetailsFromString(String[] parts) {
        if (parts.length < 5) {
            return null; // Not enough information
        }
        RoomType roomType = RoomType.valueOf(parts[2]);
        String hotelCompany = parts[3];
        String location = parts[4];
        double pricePerNight = Double.parseDouble(parts[1]);
        return new RoomDetails(roomType, hotelCompany, location, pricePerNight);
    }

    /**
     * Creates CarDetails from a string array.
     *
     * @param parts A string array representing car details.
     * @return A CarDetails object, or null if the array does not contain sufficient information.
     */
    private static CarDetails createCarDetailsFromString(String[] parts) {
        if (parts.length < 4) {
            return null; // Not enough information
        }
        String brand = parts[2];
        String model = parts[3];
        double pricePerDay = Double.parseDouble(parts[1]);
        return new CarDetails(brand, model, pricePerDay);
    }


    /**
     * Creates a User object based on the role, username, and password.
     *
     * @param role     The role of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A User object corresponding to the role, or null for an unrecognized role.
     */
    private static User createUser(String role, String username, String password) {
        switch (role) {
            case "Admin":
                return new AdminUser(username, password, new AdminUserStrategy());
            case "Premium":
                return new PremiumUser(username, password, new PremiumUserStrategy());
            case "Regular":
                return new RegularUser(username, password, new RegularUserStrategy());
            default:
                return null;
        }
    }

    /**
     * Converts a UserTypeStrategy to its corresponding role string.
     *
     * @param userTypeStrategy The UserTypeStrategy to convert.
     * @return A string representing the role, or "Unknown" for an unrecognized strategy.
     */
    private static String getRoleString(UserTypeStrategy userTypeStrategy) {
        if (userTypeStrategy instanceof AdminUserStrategy) {
            return "Admin";
        } else if (userTypeStrategy instanceof PremiumUserStrategy) {
            return "Premium";
        } else if (userTypeStrategy instanceof RegularUserStrategy) {
            return "Regular";
        } else {
            return "Unknown";
        }
    }

    /**
     * Updates a user's booked services in the user data file.
     *
     * @param user The user whose booked services are to be updated.
     */
    public static void updateUserBookedServices(User user) {
        // Read all users from the file
        List<User> allUsers = getAllUsers();

        // Update the specific user's booked services
        for (User u : allUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                u.setBookedServices(user.getBookedServices());
                break;
            }
        }

        // Write all users back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (User u : allUsers) {
                writer.write(userToString(u));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to user data file: " + e.getMessage());
        }
    }

    /**
     * Converts a User object to a string representation for storage.
     *
     * @param user The User object to convert.
     * @return A string representation of the User object.
     */
    private static String userToString(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(getRoleString(user.getUserTypeStrategy())).append(",");
        sb.append(user.getUsername()).append(",");
        sb.append(user.getPassword());

        if (!user.getBookedServices().isEmpty()) {
            sb.append(",");
            boolean first = true;
            for (Service service : user.getBookedServices()) {
                if (!first) {
                    sb.append(";");
                }
                sb.append(serviceToString(service));
                first = false;
            }
        }

        return sb.toString();
    }


    /**
     * Converts a Service object to a string representation for storage.
     *
     * @param service The Service object to convert.
     * @return A string representation of the Service object.
     */
    private static String serviceToString(Service service) {
        if (service instanceof FlightServiceHelper) {
            FlightServiceHelper flightService = (FlightServiceHelper) service;
            FlightDetails flightDetails = flightService.getFlightDetails();
            return "Flight:" + flightDetails.getPrice() + ":" +
                    flightDetails.getFlightType() + ":" +
                    flightDetails.getDepartureLocation() + ":" +
                    flightDetails.getArrivalLocation();
        } else if (service instanceof HotelServiceHelper) {
            HotelServiceHelper hotelService = (HotelServiceHelper) service;
            RoomDetails roomDetails = hotelService.getRoomDetails();
            return "Hotel:" + roomDetails.getPricePerNight() + ":" +
                    roomDetails.getRoomType() + ":" +
                    roomDetails.getHotelCompany() + ":" +
                    roomDetails.getLocation();
        } else if (service instanceof CarServiceHelper) {
            CarServiceHelper carService = (CarServiceHelper) service;
            CarDetails carDetails = carService.getCarDetails();
            return "Car:" + carDetails.getPricePerDay() + ":" +
                    carDetails.getType() + ":" +
                    carDetails.getModel();
        }
        return ""; // Or handle other types of services
    }

    /**
     * Books a service for a user and updates the user data file.
     *
     * @param user    The user for whom the service is to be booked.
     * @param service The service to be booked.
     */
    public void bookServiceForUser(User user, Service service) {
        // Logic to book the service
        user.addBookedService(service);

        // Update the user's booked services in the data file
        DataManager.updateUserBookedServices(user);
    }



}
