import java.io.*;
import java.util.*;

public class DataManager {
    private static final String USER_DATA_FILE = "/Users/hoon/IdeaProjects/A11_FinalProject/src/users.txt"; // Update with actual file path


    // Method to read data from a file and load it into a hashmap
    public static Map<String, Object> loadFromFile(String filePath) {
        Map<String, Object> dataMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line is a key-value pair separated by a comma
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    dataMap.put(parts[0], parts[1]); // Modify as needed based on file format
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    // Method to save data from a hashmap back to a file
    public static void saveToFile(Map<String, Object> dataMap, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",", 4);
                if (userDetails.length < 3) {
                    continue;
                }

                String role = userDetails[0];
                String username = userDetails[1];
                String password = userDetails[2];
                User user = createUser(role, username, password);

                if (userDetails.length == 4 && userDetails[3] != null && !userDetails[3].isEmpty()) {
                    String[] bookedServices = userDetails[3].split(";");
                    for (String serviceString : bookedServices) {
                        Service bookedService = createServiceFromString(serviceString);
                        if (bookedService != null) {
                            user.addBookedService(bookedService);
                        }
                    }
                }

                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return users;
    }



    public static boolean usernameExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 2 && userDetails[1].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return false;
    }


    public static void addUser(String role, String username, String password) {
        try (FileWriter fw = new FileWriter(USER_DATA_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(role + "," + username + "," + password);
        } catch (IOException e) {
            System.err.println("Error writing to user data file: " + e.getMessage());
        }
    }


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

    private static CarDetails createCarDetailsFromString(String[] parts) {
        if (parts.length < 4) {
            return null; // Not enough information
        }
        String brand = parts[2];
        String model = parts[3];
        double pricePerDay = Double.parseDouble(parts[1]);
        return new CarDetails(brand, model, pricePerDay);
    }


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

    // Example scenario where a user books a service
    public void bookServiceForUser(User user, Service service) {
        // Logic to book the service
        user.addBookedService(service);

        // Update the user's booked services in the data file
        DataManager.updateUserBookedServices(user);
    }



}
