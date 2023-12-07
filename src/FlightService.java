import java.io.*;
import java.util.*;

public class FlightService extends Service {
    private Map<String, FlightDetails> flightInventory;

    public FlightService(String name, double price, double rating) {
        super(name, price, rating);
        this.flightInventory = new HashMap<>();
    }

    @Override
    public void bookService() {
        // Logic to book a flight
    }

    // Flight-specific methods
    public void addFlight(String flightId, FlightDetails details) {
        flightInventory.put(flightId, details);
    }

    public Map<String, FlightDetails> getFlightInventory() {
        return flightInventory;
    }

    public void setFlightInventory(Map<String, FlightDetails> flightInventory) {
        this.flightInventory = flightInventory;
    }
}
