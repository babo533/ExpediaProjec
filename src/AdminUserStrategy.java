import java.io.*;
import java.util.*;

public class AdminUserStrategy implements UserTypeStrategy {
    @Override
    public void bookService(User user, Service service) {
        // Admins can book services
        System.out.println("Admin " + user.getUsername() + " booked " + service.getName());
        user.addBookedService(service);
    }

    @Override
    public void viewBookedServices(User user) {
        // Assuming this method is for viewing all users' booked services
        List<User> allUsers = DataManager.getAllUsers();
        for (User u : allUsers) {
            System.out.println("User: " + u.getUsername());
            List<Service> bookedServices = u.getBookedServices();
            if (bookedServices.isEmpty()) {
                System.out.println("  No services booked.");
            }
            else {
                for (Service service : bookedServices) {
                    System.out.println("Booked Service: " + service.getName() + ", Price: " + service.getPrice());
                }
            }
        }
    }

    private List<User> getAllUsers() {
        // This method should return a list of all users
        // For example, it could retrieve this information from DataManager
        return DataManager.getAllUsers();
    }
}
