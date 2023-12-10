import java.io.*;
import java.util.*;

/**
 * The FlightService class extends the {@code Service} class to manage flight-related services.
 * It handles operations like loading flight data, adding flights, and booking flights for users.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class FlightService extends Service {
    private Map<String, FlightDetails> flightInventory;

    /**
     * Constructs a new {@code FlightService} instance and loads flight data from a specified file.
     *
     * @throws IOException If there is an error in reading the flight data file.
     */
    public FlightService() throws IOException {
        super("Flight Service", 0.0);
        flightInventory = new HashMap<>();
        loadFlightData("/Users/hoon/IdeaProjects/A11_FinalProject/src/flights.txt");
    }

    /**
     * Loads flight data from a specified file into the flight inventory.
     *
     * @param filePath The path to the file containing flight data.
     */
    public void loadFlightData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        FlightType flightType = FlightType.valueOf(parts[0].trim().toUpperCase());
                        String departureLocation = parts[1].trim();
                        String arrivalLocation = parts[2].trim();
                        double price = Double.parseDouble(parts[3].trim());
                        String key = departureLocation + " to " + arrivalLocation;
                        flightInventory.put(key, new FlightDetails(flightType, departureLocation, arrivalLocation, price));
                    }
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * Adds a new flight to the flight data file.
     *
     * @param flightType        The type of the flight (e.g., ECONOMY, BUSINESS).
     * @param departureLocation The departure location of the flight.
     * @param arrivalLocation   The arrival location of the flight.
     * @param price             The price of the flight.
     */

    public void addFlightToFile(FlightType flightType, String departureLocation, String arrivalLocation, double price) {
        String filePath = "/Users/hoon/IdeaProjects/A11_FinalProject/src/flights.txt"; // File path
        try (FileWriter fw = new FileWriter(filePath, true); // Append mode
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            File file = new File(filePath);
            // Check if the file already has content and prepend a newline character if it does
            if (file.length() > 0) {
                out.println(); // Add a newline character
            }

            out.println(flightType.name() + "," + departureLocation + "," + arrivalLocation + "," + price);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Books a flight service for a user.
     *
     * @param user      The user who is booking the flight.
     * @param flightKey The key representing the specific flight in the flight inventory.
     */
    @Override
    public void bookService(User user, String flightKey) {
        FlightDetails flight = flightInventory.get(flightKey);
        if (flight != null) {
            user.addBookedService(flight);
        } else {
            System.out.println("Flight is not available.");
        }
    }

    /**
     * Retrieves the flight inventory map.
     *
     * @return The map containing all flights in the inventory.
     */
    public Map<String, FlightDetails> getFlightInventory() {
        return flightInventory;
    }

    /**
     * Converts a string input to the corresponding {@code FlightType} enum.
     *
     * @param input The string input representing a flight type.
     * @return The corresponding {@code FlightType} enum.
     * @throws IllegalArgumentException If the input string does not correspond to a valid {@code FlightType}.
     */
    public FlightType getFlightTypeFromString(String input) {
        switch (input.trim().toLowerCase()) {
            case "economy":
                return FlightType.ECONOMY;
            case "economy plus":
                return FlightType.ECONOMY_PLUS;
            case "business":
                return FlightType.BUSINESS;
            case "business plus":
                return FlightType.BUSINESS_PLUS;
            case "first class":
                return FlightType.FIRST_CLASS;
            default:
                throw new IllegalArgumentException("Invalid flight type: " + input);
        }
    }



}
