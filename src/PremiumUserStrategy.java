import java.io.*;
import java.util.*;

public class PremiumUserStrategy implements UserTypeStrategy {
    @Override
    public void bookService(User user, Service service) {
        System.out.println(user.getUsername() + " booked " + service.getName() + " with premium benefits");
        service.applyDiscount(0.1); // 10% discount for premium users
        user.addBookedService(service);
    }

    @Override
    public void viewBookedServices(User user) {
        System.out.println("Premium Booked Services for " + user.getUsername() + ":");
        for (Service service : user.getBookedServices()) {
            System.out.println(service.getName() + " (Premium)");
        }
    }
}

