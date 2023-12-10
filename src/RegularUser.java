/**
 * The RegularUser class represents a regular user in the system.
 * It extends the User class and provides functionalities specific to regular users.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class RegularUser extends User {

    /**
     * Constructs a new {@code RegularUser} instance with the provided username, password, and user type strategy.
     * Additional initializations specific to a regular user can be performed in this constructor.
     *
     * @param username            The username of the regular user.
     * @param password            The password of the regular user.
     * @param userTypeStrategy    The strategy for user type, used for providing specific services to the user.
     */
    public RegularUser(String username, String password, UserTypeStrategy userTypeStrategy) {
        super(username, password, userTypeStrategy);
        // Additional initialization for RegularUser
    }

    /**
     * Checks and displays available services for the regular user.
     * This method checks the availability of flights, hotel rooms, and rental cars.
     *
     * @param flightService       The flight service to check for available flights.
     * @param hotelService        The hotel service to check for available hotel rooms.
     * @param rentalCarService    The rental car service to check for available rental cars.
     */
    public void checkAvailableServices(FlightService flightService, HotelService hotelService, RentalCarService rentalCarService) {
        System.out.println("Available Services for Regular User:");

        // Check available flights
        if (!flightService.getFlightInventory().isEmpty()) {
            System.out.println("Flight Booking is available.");
        }

        // Check available hotel rooms
        if (!hotelService.getRoomInventory().isEmpty()) {
            System.out.println("Hotel Reservation is available.");
        }

        // Check available rental cars
        if (!rentalCarService.getCarInventory().isEmpty()) {
            System.out.println("Car Rental is available.");
        }
    }

}
