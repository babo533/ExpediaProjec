import java.io.*;
import java.util.*;

/**
 * The {@code PremiumUserStrategy} class implements the {@code UserTypeStrategy} interface.
 * This strategy is used for premium users, providing them with special benefits like discounts on services.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class PremiumUserStrategy implements UserTypeStrategy {

    /**
     * Books a service for the premium user with additional benefits like discounts.
     *
     * @param user    The premium user who is booking the service.
     * @param service The service that is being booked.
     */
    @Override
    public void bookService(User user, Service service) {
        System.out.println(user.getUsername() + " booked " + service.getName() + " with premium benefits");
        service.applyDiscount(0.1); // Applies a 10% discount for premium users.
        user.addBookedService(service); // Adds the booked service to the user's list of booked services.
    }

    /**
     * Displays all services booked by the premium user.
     *
     * @param user The premium user whose booked services are to be displayed.
     */
    @Override
    public void viewBookedServices(User user) {
        System.out.println("Premium Booked Services for " + user.getUsername() + ":");
        for (Service service : user.getBookedServices()) {
            System.out.println(service.getName() + " (Premium)"); // Displays each booked service with a premium tag.
        }
    }
}
