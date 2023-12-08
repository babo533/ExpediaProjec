import java.io.*;
import java.util.*;

public abstract class User {
    private String username;
    private String password;
    private List<Service> bookedServices;
    protected UserTypeStrategy userTypeStrategy;

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

    public void bookService(Service service) {
        userTypeStrategy.bookService(this, service);
    }

    public void viewBookedServices() {
        userTypeStrategy.viewBookedServices(this);
    }

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


    public List<Service> getBookedServices() {
        return bookedServices;
    }

    public void setBookedServices(List<Service> bookedServices) {
        this.bookedServices = bookedServices;
    }

    public UserTypeStrategy getUserTypeStrategy() {
        return userTypeStrategy;
    }

    public void setUserTypeStrategy(UserTypeStrategy userTypeStrategy) {
        this.userTypeStrategy = userTypeStrategy;
    }
}
