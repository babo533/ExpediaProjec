import java.io.*;
import java.util.*;

public class RentalCarService extends Service {
    private Map<String, CarDetails> carInventory;

    public RentalCarService(String name, double price) {
        super(name, price);
        this.carInventory = new HashMap<>();
    }

    @Override
    public void bookService() {
        // Logic to book a rental car
    }


    public void addCar(String carId, CarDetails details) {
        carInventory.put(carId, details);
    }

    public Map<String, CarDetails> getCarInventory() {
        return carInventory;
    }

    public void setCarInventory(Map<String, CarDetails> carInventory) {
        this.carInventory = carInventory;
    }
}
