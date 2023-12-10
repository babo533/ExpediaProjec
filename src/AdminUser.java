import java.io.*;
import java.util.*;

public class AdminUser extends User {
    private static BFF bff = new BFF();
    public AdminUser(String username, String password, UserTypeStrategy userTypeStrategy) {
        super(username, password, new AdminUserStrategy());
    }

    @Override
    public void bookService(Service service) {
        userTypeStrategy.bookService(this, service);
    }

    public void viewAllBookedServices() {
        getUserTypeStrategy().viewBookedServices(this);
    }

    public void printAllUsers() {
        List<User> allUsers = DataManager.getAllUsers();
        for (User user : allUsers) {
            System.out.println("Username: " + user.getUsername() + ", Booked Services: " + user.getBookedServices().size());
            // Add more details as needed
        }
    }

    public void addFlightAsAdmin(FlightService flightService) {
        String flightTypeInput = bff.input("Enter flight type (Economy, Economy Plus, Business, Business Plus, First Class): (ENTER IN ALL CAPS)");
        FlightType flightType;
        try {
            flightType = FlightType.valueOf(flightTypeInput.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid flight type. Please try again.");
            return;
        }

        String departureLocation = bff.input("Enter departure location:");
        String arrivalLocation = bff.input("Enter arrival location:");
        double price = bff.inputDouble("Enter price:");

        FlightDetails newFlight = new FlightDetails(flightType, departureLocation, arrivalLocation, price);
        flightService.addFlightToFile(flightType, departureLocation, arrivalLocation, price);
    }

    public void addCarsAsAdmin(RentalCarService carService) {
        String brand = bff.input("Enter car brand:");
        String model = bff.input("Enter car model:");
        double pricePerDay = bff.inputDouble("Enter price per day:");

        CarDetails newCar = new CarDetails(brand, model, pricePerDay);
        carService.addCarToFile(brand, model, pricePerDay); // Assuming similar method in CarService
    }

    public void addHotelsAsAdmin(HotelService hotelService) {
        // Assuming RoomType is an enum with values like STANDARD, DELUXE, SUITE, etc.
        String roomTypeInput = bff.input("Enter room type (e.g., Standard, Deluxe, Suite) (ENTER IN ALL CAPS):");
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeInput.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room type. Please try again.");
            return;
        }

        String hotelCompany = bff.input("Enter hotel company name:");
        String location = bff.input("Enter location:");
        double pricePerNight = bff.inputDouble("Enter price per night:");

        RoomDetails newRoom = new RoomDetails(roomType, hotelCompany, location, pricePerNight);
        hotelService.addRoomToFile(roomType, hotelCompany, location, pricePerNight); // Update this method in HotelService
    }




}