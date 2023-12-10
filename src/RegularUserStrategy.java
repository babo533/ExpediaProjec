/**
 * The RegularUserStrategy class implements the UserTypeStrategy interface,
 * providing behavior specific to regular users. It includes methods for booking services and
 * viewing the services that a user has booked.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class RegularUserStrategy implements UserTypeStrategy {
    /**
     * Books a service for the regular user.
     *
     * @param user    The user who is booking the service.
     * @param service The service that is being booked.
     */
    @Override
    public void bookService(User user, Service service) {
        System.out.println(user.getUsername() + " booked " + service.getName());
        user.addBookedService(service);
    }

    /**
     * Displays all services booked by the regular user.
     *
     * @param user The user whose booked services are to be displayed.
     */
    @Override
    public void viewBookedServices(User user) {
        System.out.println("Booked Services for " + user.getUsername() + ":");
        for (Service service : user.getBookedServices()) {
            System.out.println(service.getName());
        }
    }
}
