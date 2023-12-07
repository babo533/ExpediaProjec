public interface UserTypeStrategy {
    void bookService(User user, Service service);
    void viewBookedServices(User user);
}
