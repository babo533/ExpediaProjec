import java.io.*;
import java.util.*;

public class FlightService extends Service {
    private Map<String, FlightDetails> flightInventory;

//    public FlightService(String name, double price) {
//        super(name, price);
//        this.flightInventory = new HashMap<>();
//    }

    public FlightService(String name, double price) {
        super(name, price);
    }

    public void loadFlightData(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String key = parts[1] + " to " + parts[2]; // Departing to Arrival as key
                    double price = Double.parseDouble(parts[3]);
                    flightInventory.put(key, new FlightDetails(FlightType.valueOf(parts[0]), parts[1], parts[2], price));
                }
            }
        }
    }

    public void addFlightToFile(FlightType flightType, String departureLocation, String arrivalLocation, double price) {
        // Logic to add flight details to the file
        try (FileWriter fw = new FileWriter("flights.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(flightType + "," + departureLocation + "," + arrivalLocation + "," + price);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    @Override
    public void bookService() {
        // Logic to book a flight
    }

    // Flight-specific methods
    public void addFlight(String flightKey, FlightDetails flightDetails) {
        flightInventory.put(flightKey, flightDetails);
        // Additional logic if necessary
    }

    public Map<String, FlightDetails> getFlightInventory() {
        return flightInventory;
    }

    public void setFlightInventory(Map<String, FlightDetails> flightInventory) {
        this.flightInventory = flightInventory;
    }
}
