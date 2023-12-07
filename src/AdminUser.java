import java.io.*;
import java.util.*;

public class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password, new AdminUserStrategy());
    }

    @Override
    public void bookService(Service service) {
        userTypeStrategy.bookService(this, service);
    }

    public void viewAllBookedServices() {
        List<User> allUsers = DataManager.getAllUsers();
        for (User user : allUsers) {
            System.out.println("User: " + user.getUsername());
            List<Service> bookedServices = user.getBookedServices();
            if (bookedServices.isEmpty()) {
                System.out.println("  No services booked.");
            } else {
                for (Service service : bookedServices) {
                    System.out.println("  Booked Service: " + service.getName() + ", Price: " + service.getPrice());
                }
            }
        }
    }

    public void printAllUsers() {
        List<User> allUsers = DataManager.getAllUsers();
        for (User user : allUsers) {
            System.out.println("Username: " + user.getUsername() + ", Booked Services: " + user.getBookedServices().size());
            // Add more details as needed
        }
    }
}