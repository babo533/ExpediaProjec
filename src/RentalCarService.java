import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class RentalCarService extends Service {
    private Map<String, CarDetails> carInventory;

    public RentalCarService() throws IOException {
        super("Rental Car Service", 0.0);
        this.carInventory = new HashMap<>();
        loadCarData("/Users/hoon/IdeaProjects/A11_FinalProject/src/cars.txt");
    }

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



    @Override
    public void bookService(User user, String carKey) {
        CarDetails car = carInventory.get(carKey);
        if (car != null) {
            user.addBookedService(car);
        } else {
            System.out.println("Car is not available.");
        }
    }

    public Map<String, CarDetails> getCarInventory() {
        return carInventory;
    }

    public void setCarInventory(Map<String, CarDetails> carInventory) {
        this.carInventory = carInventory;
    }
}
