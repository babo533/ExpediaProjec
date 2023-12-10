import java.io.*;
import java.util.*;

/**
 * The {@code AdminUser} class extends the {@code User} class to provide additional administrative functionalities.
 * This class allows an admin user to book services, view all booked services, manage users, and add various services such as flights, rental cars, and hotel rooms.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class AdminUser extends User {
    private static BFF bff = new BFF(); // Helper class for input handling.

    /**
     * Constructs an {@code AdminUser} with specified username, password, and user type strategy.
     *
     * @param username            the username of the admin user.
     * @param password            the password of the admin user.
     * @param userTypeStrategy    the strategy for user type, set to {@code AdminUserStrategy} for admin users.
     */
    public AdminUser(String username, String password, UserTypeStrategy userTypeStrategy) {
        super(username, password, new AdminUserStrategy());
    }

    /**
     * Books a service for the admin user.
     *
     * @param service the service to be booked.
     */
    @Override
    public void bookService(Service service) {
        userTypeStrategy.bookService(this, service);
    }

    /**
     * Views all services booked by the admin user.
     */
    public void viewAllBookedServices() {
        getUserTypeStrategy().viewBookedServices(this);
    }

    /**
     * Prints all registered users and their booked services.
     */
    public void printAllUsers() {
        List<User> allUsers = DataManager.getAllUsers();
        for (User user : allUsers) {
            System.out.println("Username: " + user.getUsername() + ", Booked Services: " + user.getBookedServices().size());
            // Additional user details can be printed here if needed.
        }
    }

    /**
     * Adds a new flight service as an admin.
     *
     * @param flightService the flight service to which the new flight will be added.
     */
    public void addFlightAsAdmin(FlightService flightService) {
        // Handles input for flight type.
        String flightTypeInput = bff.input("Enter flight type (Economy, Economy Plus, Business, Business Plus, First Class): (ENTER IN ALL CAPS)");
        FlightType flightType;
        try {
            flightType = FlightType.valueOf(flightTypeInput.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid flight type. Please try again.");
            return;
        }

        // Handles input for departure and arrival locations, and price.
        String departureLocation = bff.input("Enter departure location:");
        String arrivalLocation = bff.input("Enter arrival location:");
        double price = bff.inputDouble("Enter price:");

        // Creates new flight details and adds to the flight service.
        FlightDetails newFlight = new FlightDetails(flightType, departureLocation, arrivalLocation, price);
        flightService.addFlightToFile(flightType, departureLocation, arrivalLocation, price);
    }

    /**
     * Adds a new car to the rental car service as an admin.
     *
     * @param carService the car service to which the new car will be added.
     */
    public void addCarsAsAdmin(RentalCarService carService) {
        // Handles input for car details.
        String brand = bff.input("Enter car brand:");
        String model = bff.input("Enter car model:");
        double pricePerDay = bff.inputDouble("Enter price per day:");

        // Creates new car details and adds to the car service.
        CarDetails newCar = new CarDetails(brand, model, pricePerDay);
        carService.addCarToFile(brand, model, pricePerDay); // Assuming similar method in CarService.
    }

    /**
     * Adds a new hotel room to the hotel service as an admin.
     *
     * @param hotelService the hotel service to which the new room will be added.
     */
    public void addHotelsAsAdmin(HotelService hotelService) {
        // Handles input for room type.
        String roomTypeInput = bff.input("Enter room type (e.g., Standard, Deluxe, Suite) (ENTER IN ALL CAPS):");
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeInput.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room type. Please try again.");
            return;
        }

        // Handles input for hotel company, location, and price per night.
        String hotelCompany = bff.input("Enter hotel company name:");
        String location = bff.input("Enter location:");
        double pricePerNight = bff.inputDouble("Enter price per night:");

        RoomDetails newRoom = new RoomDetails(roomType, hotelCompany, location, pricePerNight);
        hotelService.addRoomToFile(roomType, hotelCompany, location, pricePerNight); // Update this method in HotelService
    }




}
