public class RegularUserStrategy implements UserTypeStrategy {
    @Override
    public void bookService(User user, Service service) {
        // Regular booking logic
        service.bookService();
    }

    @Override
    public void viewBookedServices(User user) {

    }
}