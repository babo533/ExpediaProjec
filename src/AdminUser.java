import java.util.*;
import java.io.*;
public class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password);
        this.userType = "Admin";
    }


    public void printAllUsers(String filePath) {
        if (!login(filePath)) {
            System.out.println("Authentication failed. Access denied.");
            return;
        }

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] userDetails = scanner.nextLine().split(",");
                if (userDetails.length == 3) {
                    String userType = userDetails[0];
                    String username = userDetails[1];
                    String password = userDetails[2];
                    if (this.username.equals(username) && this.password.equals(password) && userType.equals("Admin")) {
                        this.setAuthenticated(true);
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to add another admin user
    public void addAdminUser(String username, String password, UserManager userManager) throws Exception {
        if (this.isAuthenticated()) {
            userManager.addUser("Admin", username, password);
        }
        else {
            System.out.println("Unauthorized: Only authenticated admin users can add other admin users.");
        }
    }

    @Override
    protected boolean isCorrectUserType(String userType) {
        return "Admin".equals(userType);
    }

}
