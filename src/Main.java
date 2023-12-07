import java.io.*;
import java.util.*;

public class Main {
    private static BFF bff = new BFF();
    private static DataManager dataManager = new DataManager(); // Assuming DataManager handles user data

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
            if (user instanceof AdminUser) {
                String adminChoice = bff.input("Select an option:\n1. View All Users\n2. Other Admin Options\n3. Logout", "1", "2", "3");
                switch (adminChoice) {
                    case "1":
                        System.out.println("Debug: Viewing all users"); // Debug message
                        ((AdminUser) user).printAllUsers();
                        bff.input("Press Enter to continue..."); // Pause
                        break;
                    case "2":
                        // Other admin functionalities
                        break;
                    case "3":
                        isSessionActive = false; // Logout
                        break;
                }
            } else if (user instanceof PremiumUser) {
                // Premium user-specific options
            } else if (user instanceof RegularUser) {
                // Regular user-specific options
            }
        }
    }

}

