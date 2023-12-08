public class RegularUser extends User {

    public RegularUser(String username, String password, UserTypeStrategy userTypeStrategy) {
        super(username, password, userTypeStrategy);
        // Additional initialization for RegularUser
    }


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

    // Additional methods
}
