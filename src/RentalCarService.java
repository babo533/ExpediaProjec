import java.io.*;
import java.util.*;

public class RentalCarService extends Service implements InventoryModification{
    private static final Map<String, Map<String, Double>> carInventory = new HashMap<>();
    private static final String CAR_FILE_NAME = "/Users/hoon/IdeaProjects/A11_FinalProject/src/cars.txt";  // File name constant
    private final BFF bff = new BFF(); // Instance of BFF for input handling

    public RentalCarService() {
        loadCarsFromFile();
    }

    private void loadCarsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(CAR_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCarData(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + CAR_FILE_NAME);
            e.printStackTrace();
        }
    }

    private void processCarData(String line) {
        try (Scanner lineScanner = new Scanner(line)) {
            lineScanner.useDelimiter(",");
            String carType = lineScanner.next();
            String model = lineScanner.next();
            double price = lineScanner.nextDouble();

            // Manually check if the car type exists in the inventory
            if (!carInventory.containsKey(carType)) {
                carInventory.put(carType, new HashMap<String, Double>());
            }
            // Add or update the model and price for the car type
            carInventory.get(carType).put(model, price);
        } catch (Exception e) {
            System.err.println("Error processing line: " + line);
            e.printStackTrace();
        }
    }

    public void displayAvailableCars() {
        System.out.println("Available Cars:");
        for (Map.Entry<String, Map<String, Double>> typeEntry : carInventory.entrySet()) {
            System.out.println(typeEntry.getKey() + ":");
            for (Map.Entry<String, Double> modelEntry : typeEntry.getValue().entrySet()) {
                System.out.println("  - " + modelEntry.getKey() + ": $" + modelEntry.getValue() + " per day");
            }
        }
    }

    @Override
    public void bookService() {
        String type = bff.inputWord("Enter car type");
        if (!carInventory.containsKey(type)) {
            System.out.println("Car type not available.");
            return;
        }
        String carModel = bff.inputWord("Enter car model");
        if (!carInventory.get(type).containsKey(carModel)) {
            System.out.println("Car model not available.");
            return;
        }
        int rentalDuration = bff.inputInt("Enter rental duration (days)", 1, Integer.MAX_VALUE);
        System.out.println("You have rented a " + carModel + " for " + rentalDuration + " days");
        // Additional logic can be added here for payment, updating inventory, etc.
    }

    @Override
    public void getServiceDetails() {
        // Implementation for service details
        // Providing information about the rental service
        System.out.println("Welcome to our Car Rental Service!");
        System.out.println("We offer a variety of car types including sedans and SUVs.");
        System.out.println("Our rates are competitive, with prices starting as low as $45 per day.");
    }

    @Override
    public void addItem(String carType, Object carDetails) {
        if (carDetails instanceof Map) {
            carInventory.put(carType, (Map<String, Double>) carDetails);
        }
    }

    @Override
    public void updateItem(String carType, Object newCarDetails) {
        if (newCarDetails instanceof Map) {
            carInventory.replace(carType, (Map<String, Double>) newCarDetails);
        }
    }


}