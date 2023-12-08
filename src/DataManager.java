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
                String[] userDetails = line.split(",");
                if (userDetails.length == 3) {
                    User user = createUser(userDetails[0], userDetails[1], userDetails[2]);
                    if (user != null) {
                        users.add(user);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return users;
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
            }
        } catch (IOException e) {
            System.err.println("Error reading user data file: " + e.getMessage());
        }
        return null;
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

}
