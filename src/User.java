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

    public void bookService(Service service) {
        userTypeStrategy.bookService(this, service);
    }

    public void viewBookedServices() {
        userTypeStrategy.viewBookedServices(this);
    }

    public void addBookedService(Service service) {
        bookedServices.add(service);
    }

    public List<Service> getBookedServices() {
        return bookedServices;
    }

    public UserTypeStrategy getUserTypeStrategy() {
        return userTypeStrategy;
    }

    public void setUserTypeStrategy(UserTypeStrategy userTypeStrategy) {
        this.userTypeStrategy = userTypeStrategy;
    }
}
