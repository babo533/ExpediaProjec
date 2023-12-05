import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    private static final String USER_FILE = "users.txt";
    private final Map<String, Map<String, String>> users;
    private final Map<String, Integer> premiumUserLoyaltyPoints; // Map for premium user loyalty points

    public UserManager() {
        this.users = new HashMap<>();
        this.premiumUserLoyaltyPoints = new HashMap<>();
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        String fileName = USER_FILE;
        boolean read;
        boolean triedAlt = false;
        do {
            try (FileInputStream fis = new FileInputStream(fileName);
                 Scanner scan = new Scanner(fis)) {
                if (scan.hasNext()) {
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        parseStringIntoUser(line);
                    }
                }
                else {
                    System.err.println("File was empty: " + fileName);
                }
                read = true;
            }
            catch (IOException e) {
                System.err.println("User File not found, trying alternative path.");
                read = false; // to force a second try with an alternative path if necessary
                // Handle alternative file path or exit
            }
        }
        while (!read);
    }

    private void parseStringIntoUser(String line) {
        try {
            Scanner sc = new Scanner(line);
            sc.useDelimiter(",");
            String userType = sc.next();
            String username = sc.next();
            String password = sc.next();

            if (!users.containsKey(userType)) {
                users.put(userType, new HashMap<String, String>());
            }
            Map<String, String> userMap = users.get(userType);
            userMap.put(username, password);
        } catch (Exception e) {
            System.err.println("Error reading line of file: " + line + "\nerror; " + e);
        }
    }

    public void printUsers(String filePath, AdminUser adminUser) {
        adminUser.printAllUsers(filePath);
    }

    public void addUser(String userType, String username, String password) throws Exception {
        Map<String, String> userMap = users.get(userType);
        if (userMap == null) {
            userMap = new HashMap<>();
            users.put(userType, userMap);
        } else {
            // Check if the username already exists within this user type
            if (userMap.containsKey(username)) {
                throw new Exception("Username '" + username + "' already exists in " + userType + " users.");
            }
        }
        userMap.put(username, password);
        updateUserFile();
    }

    private void updateUserFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (String userType : users.keySet()) {
                for (Map.Entry<String, String> entry : users.get(userType).entrySet()) {
                    writer.write(userType + "," + entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userSignUp(String userType, String username, String password) throws Exception {
        if (!userType.equals("Admin")) {
            addUser(userType, username, password);
        }
        else {
            System.out.println("Sign-up for admin users is not allowed.");
        }
    }

    public void addLoyaltyPoints(String username, int points) {
        if (isPremiumUser(username)) {
            premiumUserLoyaltyPoints.put(username, premiumUserLoyaltyPoints.getOrDefault(username, 0) + points);
        }
        else {
            System.out.println("User " + username + " is not a premium user.");
        }
    }

    public int getLoyaltyPoints(String username) {
        return premiumUserLoyaltyPoints.getOrDefault(username, 0);
    }

    private boolean isPremiumUser(String username) {
        return users.containsKey("Premium") && users.get("Premium").containsKey(username);
    }


    // Other methods like addUser, authenticateUser, etc.
}
