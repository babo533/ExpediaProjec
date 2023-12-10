/**
 * The {@code FlightDetails} class represents the details of a flight.
 * It includes the type of the flight, departure location, arrival location, and the price of the flight.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class FlightDetails {
    private FlightType flightType; // The type of the flight (e.g., Economy, Business).
    private String departureLocation; // The departure location of the flight.
    private String arrivalLocation; // The arrival location of the flight.
    private double price; // The price of the flight.

    /**
     * Constructs a new {@code FlightDetails} instance with specified flight type, departure location, arrival location, and price.
     *
     * @param flightType        The type of the flight.
     * @param departureLocation The departure location of the flight.
     * @param arrivalLocation   The arrival location of the flight.
     * @param price             The price of the flight.
     */
    public FlightDetails(FlightType flightType, String departureLocation, String arrivalLocation, double price) {
        this.flightType = flightType;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.price = price;
    }

    /**
     * Gets the flight type.
     *
     * @return The type of the flight.
     */
    public FlightType getFlightType() {
        return flightType;
    }

    /**
     * Sets the flight type.
     *
     * @param flightType The type of the flight to set.
     */
    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    /**
     * Gets the departure location of the flight.
     *
     * @return The departure location of the flight.
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     * Sets the departure location of the flight.
     *
     * @param departureLocation The departure location to set.
     */
    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    /**
     * Gets the arrival location of the flight.
     *
     * @return The arrival location of the flight.
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * Sets the arrival location of the flight.
     *
     * @param arrivalLocation The arrival location to set.
     */
    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    /**
     * Gets the price of the flight.
     *
     * @return The price of the flight.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the flight.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the {@code FlightDetails} instance.
     *
     * @return A string representation of this {@code FlightDetails} instance.
     */
    @Override
    public String toString() {
        return "FlightDetails{" +
                "flightType=" + flightType +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", price=" + price +
                '}';
    }
}
