import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

/**
 * The RentalCarService class extends the Service class and is used for managing rental car services.
 * It includes functionalities for loading car data from a file, adding new cars to the service, and booking cars for users.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class RentalCarService extends Service {
    private Map<String, CarDetails> carInventory;

    /**
     * Constructs a new RentalCarService instance and loads car data from a specified file.
     *
     * @throws IOException If there is an error in reading the car data file.
     */
    public RentalCarService() throws IOException {
        super("Rental Car Service", 0.0);
        this.carInventory = new HashMap<>();
        loadCarData("/Users/hoon/IdeaProjects/A11_FinalProject/src/cars.txt");
    }

    /**
     * Loads car data from a specified file into the car inventory.
     *
     * @param filePath The path to the file containing car data.
     */
    public void loadCarData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String type = parts[0].trim();
                        String model = parts[1].trim();
                        double pricePerDay = Double.parseDouble(parts[2].trim());
                        String key = type + " " + model; // Unique key for each car
                        carInventory.put(key, new CarDetails(model, type, pricePerDay));
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
     * Adds a new car to the car data file.
     *
     * @param brand       The brand of the car.
     * @param model       The model of the car.
     * @param pricePerDay The rental price per day for the car.
     */
    public void addCarToFile(String brand, String model, double pricePerDay) {
        String filePath = "/Users/hoon/IdeaProjects/A11_FinalProject/src/cars.txt"; // Adjust the file path as needed
        try (FileWriter fw = new FileWriter(filePath, true); // Append mode
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            File file = new File(filePath);
            // Check if the file already has content and prepend a newline character if it does
            if (file.length() > 0) {
                out.println(); // Add a newline character
            }

            out.println(brand + "," + model + "," + pricePerDay);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Books a rental car service for a user.
     *
     * @param user   The user who is booking the car.
     * @param carKey The key representing the specific car in the car inventory.
     */
    @Override
    public void bookService(User user, String carKey) {
        CarDetails car = carInventory.get(carKey);
        if (car != null) {
            user.addBookedService(car);
        } else {
            System.out.println("Car is not available.");
        }
    }

    /**
     * Retrieves the car inventory map.
     *
     * @return The map containing all cars in the inventory.
     */
    public Map<String, CarDetails> getCarInventory() {
        return carInventory;
    }

    /**
     * Sets the car inventory map.
     *
     * @param carInventory The map of car details to set as the inventory.
     */
    public void setCarInventory(Map<String, CarDetails> carInventory) {
        this.carInventory = carInventory;
    }
}
