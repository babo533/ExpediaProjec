import java.io.*;
import java.util.*;

public class FlightService extends Service {
    private Map<String, FlightDetails> flightInventory;



    public FlightService() throws IOException {
        super("Flight Service", 0.0);
        flightInventory = new HashMap<>();
        loadFlightData("/Users/hoon/IdeaProjects/A11_FinalProject/src/flights.txt");
    }

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


    public void addFlightToFile(FlightType flightType, String departureLocation, String arrivalLocation, double price) {
        try (FileWriter fw = new FileWriter("flights.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             System.out.println(flightType.name() + "," + departureLocation + "," + arrivalLocation + "," + price);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }


    @Override
    public void bookService(User user, String flightKey) {
        FlightDetails flight = flightInventory.get(flightKey);
        if (flight != null) {
            user.addBookedService(flight);
        } else {
            System.out.println("Flight is not available.");
        }
    }


    public Map<String, FlightDetails> getFlightInventory() {
        return flightInventory;
    }

    public void setFlightInventory(Map<String, FlightDetails> flightInventory) {
        this.flightInventory = flightInventory;
    }

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
