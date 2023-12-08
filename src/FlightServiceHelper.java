public class FlightServiceHelper extends Service {
    private FlightDetails flightDetails;

    public FlightServiceHelper(FlightDetails flightDetails) {
        super(flightDetails.getDepartureLocation() + " to " + flightDetails.getArrivalLocation(), flightDetails.getPrice());
        this.flightDetails = flightDetails;
    }

    // Implement or override methods as necessary
    @Override
    public void bookService(User user, String serviceName) {
        // Implement booking logic or delegate to FlightDetails
        // Example: Add the flight to the user's booked services
        user.addBookedService(this);
        System.out.println("Flight booked successfully for " + user.getUsername());
    }

    // Optionally, provide a method to access the wrapped FlightDetails
    public FlightDetails getFlightDetails() {
        return flightDetails;
    }

    // Override toString() if needed for displaying flight details
    @Override
    public String toString() {
        return "Flight from " + flightDetails.getDepartureLocation() + " to " + flightDetails.getArrivalLocation() +
                " (" + flightDetails.getFlightType() + "), Price: " + getPrice();
    }
}
