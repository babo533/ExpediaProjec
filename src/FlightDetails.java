public class FlightDetails {
    private FlightType flightType;
    private String departureLocation;
    private String arrivalLocation;
    private double price;

    public FlightDetails(FlightType flightType, String departureLocation, String arrivalLocation, double price) {
        this.flightType = flightType;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.price = price;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void bookService(User user, String serviceName) {

    }

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
