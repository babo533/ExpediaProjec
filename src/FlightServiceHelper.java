import java.io.*;
import java.util.*;

/**
 * The FlightServiceHelper class extends the Service class and is used to represent a flight service.
 * It encapsulates the details of a flight and provides functionality to book the service.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class FlightServiceHelper extends Service {
    private FlightDetails flightDetails;

    /**
     * Constructs a new FlightService instance and loads flight data from a specified file.
     *
     * @throws IOException If there is an error in reading the flight data file.
     */
    public FlightServiceHelper(FlightDetails flightDetails) {
        super(flightDetails.getDepartureLocation() + " to " + flightDetails.getArrivalLocation(), flightDetails.getPrice());
        this.flightDetails = flightDetails;
    }

    /**
     * Books this flight service for a user.
     *
     * @param user        The user for whom the service is being booked.
     * @param serviceName The name of the service being booked (used if necessary for more complex logic).
     */
    @Override
    public void bookService(User user, String serviceName) {
        // Implement booking logic or delegate to FlightDetails
        // Example: Add the flight to the user's booked services
        user.addBookedService(this);
        System.out.println("Flight booked successfully for " + user.getUsername());
    }

    /**
     * Gets the flight details associated with this service.
     *
     * @return The FlightDetails instance associated with this service.
     */
    public FlightDetails getFlightDetails() {
        return flightDetails;
    }

    /**
     * Returns a string representation of the FlightServiceHelper instance.
     *
     * @return A string representation of this FlightServiceHelper instance.
     */
    @Override
    public String toString() {
        return "Flight from " + flightDetails.getDepartureLocation() + " to " + flightDetails.getArrivalLocation() +
                " (" + flightDetails.getFlightType() + "), Price: " + getPrice();
    }
}
