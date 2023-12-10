import java.io.*;
import java.util.*;

/**
 * The AdminUserStrategy class implements the  interface.
 * This strategy is used for admin users, allowing them to book services and view all users' booked services.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */

public class AdminUserStrategy implements UserTypeStrategy {

    /**
     * Allows the admin user to book a service.
     *
     * @param user    The admin user who is booking the service.
     * @param service The service that is being booked.
     */
    @Override
    public void bookService(User user, Service service) {
        // Admin users have the privilege to book services.
        System.out.println("Admin " + user.getUsername() + " booked " + service.getName());
        user.addBookedService(service); // Adds the booked service to the user's list of booked services.
    }

    /**
     * Allows the admin user to view all booked services by all users.
     *
     * @param user The admin user who is viewing the booked services.
     */
    @Override
    public void viewBookedServices(User user) {
        // This method enables viewing of all users' booked services.
        List<User> allUsers = DataManager.getAllUsers(); // Retrieves all users from the data manager.
        for (User u : allUsers) {
            System.out.println("User: " + u.getUsername()); // Prints the username of each user.
            List<Service> bookedServices = u.getBookedServices(); // Retrieves the booked services for the user.
            if (bookedServices.isEmpty()) {
                System.out.println("  No services booked."); // Indicates if the user has no booked services.
            } else {
                for (Service service : bookedServices) {
                    // Prints each booked service's name and price.
                    System.out.println("Booked Service: " + service.getName() + ", Price: " + service.getPrice());
                }
            }
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    private List<User> getAllUsers() {
        // This method should interact with the data layer to fetch all users.
        // Example: Retrieve user information from DataManager.
        return DataManager.getAllUsers();
    }
}
