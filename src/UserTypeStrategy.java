/**
 * The UserTypeStrategy interface defines the contract for strategies related to different types of users.
 * Implementations of this interface provide specific behaviors for booking services and viewing booked services,
 * tailored to the type of user (e.g., regular, premium).
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public interface UserTypeStrategy {
    /**
     * Books a service for a user. Implementations of this method should define how a service is booked by a user.
     *
     * @param user    The user who is booking the service.
     * @param service The service to be booked by the user.
     */
    void bookService(User user, Service service);

    /**
     * Views the services booked by a user. Implementations of this method should define how a user views their booked services.
     *
     * @param user The user whose booked services are to be viewed.
     */
    void viewBookedServices(User user);
}
