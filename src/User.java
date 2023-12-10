import java.io.*;
import java.util.*;
/**
 * The User abstract class serves as a base for different types of users in the system.
 * It provides common properties like username, password, and a list of booked services,
 * and delegates user-specific behaviors to the UserTypeStrategy
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public abstract class User {
    private String username; // The username of the user.
    private String password; // The password of the user.
    private List<Service> bookedServices; // A list of services booked by the user.
    protected UserTypeStrategy userTypeStrategy; // The strategy defining user-specific behaviors.

    /**
     * Constructs a new User instance with the specified username, password, and user type strategy.
     *
     * @param username            The username of the user.
     * @param password            The password of the user.
     * @param userTypeStrategy    The strategy for user-specific behaviors.
     */
    public User(String username, String password, UserTypeStrategy userTypeStrategy) {
        this.username = username;
        this.password = password;
        this.userTypeStrategy = userTypeStrategy;
        this.bookedServices = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Delegates the action of booking a service to the UserTypeStrategy
     *
     * @param service The service to be booked.
     */
    public void bookService(Service service) {
        userTypeStrategy.bookService(this, service);
    }

    /**
     * Delegates the action of viewing booked services to the UserTypeStrategy
     */
    public void viewBookedServices() {
        userTypeStrategy.viewBookedServices(this);
    }

    /**
     * Adds a booked service to the user's list of booked services.
     *
     * @param service The service to be added to the booked services.
     * @throws IllegalArgumentException If the provided object is not of type {@code Service}.
     */
    public void addBookedService(Object service) {
        if (service instanceof Service) {
            bookedServices.add((Service) service);
        } else {
            // Include the actual types in the error message
            String expectedType = "Service";
            String receivedType = service == null ? "null" : service.getClass().getName();
            throw new IllegalArgumentException("Invalid argument: expected type " + expectedType + " but received type " + receivedType + ".");
        }
    }

    /**
     * Retrieves the list of services booked by the user.
     *
     * @return The list of booked services.
     */
    public List<Service> getBookedServices() {
        return bookedServices;
    }

    /**
     * Sets the list of services booked by the user.
     *
     * @param bookedServices The list of booked services to set.
     */
    public void setBookedServices(List<Service> bookedServices) {
        this.bookedServices = bookedServices;
    }

    /**
     * Gets the user type strategy associated with the user.
     *
     * @return The UserTypeStrategy of the user.
     */
    public UserTypeStrategy getUserTypeStrategy() {
        return userTypeStrategy;
    }

    /**
     * Sets the user type strategy for the user.
     *
     * @param userTypeStrategy The UserTypeStrategy to set.
     */
    public void setUserTypeStrategy(UserTypeStrategy userTypeStrategy) {
        this.userTypeStrategy = userTypeStrategy;
    }
}
