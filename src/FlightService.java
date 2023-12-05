import java.util.HashMap;
import java.util.Map;

public class FlightService extends Service {
    private final Map<String, Map<FlightType, String>> flightInventory = new HashMap<>();

    public FlightService(String name, double price, double rating) {
        super(name, price, rating);
        initializeInventory();
    }

    private void initializeInventory() {
        // Sample initialization - this could be replaced with dynamic data
        Map<FlightType, String> flightsFromNY = new HashMap<>();
        flightsFromNY.put(FlightType.ECONOMY, "Los Angeles");
        flightsFromNY.put(FlightType.BUSINESS, "Chicago");

        Map<FlightType, String> flightsFromLA = new HashMap<>();
        flightsFromLA.put(FlightType.FIRST_CLASS, "New York");
        flightsFromLA.put(FlightType.ECONOMY_PLUS, "Miami");

        flightInventory.put("New York", flightsFromNY);
        flightInventory.put("Los Angeles", flightsFromLA);
    }

    // Method to display available flights
    public void displayAvailableFlights() {
        for (String startLocation : flightInventory.keySet()) {
            System.out.println("Flights from " + startLocation + ":");
            Map<FlightType, String> flights = flightInventory.get(startLocation);
            for (FlightType type : flights.keySet()) {
                System.out.println("  - " + type + " to " + flights.get(type));
            }
        }
    }

    @Override
    public void getServiceDetails() {
        System.out.print("poop");
    }

    @Override
    public void bookService() {
        System.out.print("poop");
    }

    // Additional methods

    @Override
    public void addItem(String flightId, Object flightDetails) {
        if (flightDetails instanceof FlightDetails) {
            flightInventory.put(flightId, (FlightDetails) flightDetails);
        }
    }

    @Override
    public void updateItem(String flightId, Object newFlightDetails) {
        if (newFlightDetails instanceof FlightDetails) {
            flightInventory.replace(flightId, (FlightDetails) newFlightDetails);
        }
    }
}
