public class RegularUserStrategy implements UserTypeStrategy {
    @Override
    public void bookService(User user, Service service) {
        System.out.println(user.getUsername() + " booked " + service.getName());
        user.addBookedService(service);
    }

    @Override
    public void viewBookedServices(User user) {
        System.out.println("Booked Services for " + user.getUsername() + ":");
        for (Service service : user.getBookedServices()) {
            System.out.println(service.getName());
        }
    }
}
