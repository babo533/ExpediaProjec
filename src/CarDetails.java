/**
 * The {@code CarDetails} class represents the details of a car.
 * It encapsulates the model, type, and price per day of the car.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class CarDetails {
    private String model; // Model of the car.
    private String type;  // Type of the car.
    private double pricePerDay; // Rental price per day for the car.

    /**
     * Constructs a new {@code CarDetails} instance.
     *
     * @param model       The model of the car.
     * @param type        The type of the car.
     * @param pricePerDay The rental price per day of the car.
     */
    public CarDetails(String model, String type, double pricePerDay) {
        this.model = model;
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    /**
     * Gets the model of the car.
     *
     * @return The model of the car.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model The model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the type of the car.
     *
     * @return The type of the car.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the car.
     *
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the rental price per day of the car.
     *
     * @return The rental price per day.
     */
    public double getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Sets the rental price per day of the car.
     *
     * @param pricePerDay The price per day to set.
     */
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Returns a string representation of the {@code CarDetails} instance.
     *
     * @return A string representation of this {@code CarDetails} instance.
     */
    @Override
    public String toString() {
        return "Type: " + type + ", Model: " + model + ", Price per Day: " + pricePerDay;
    }
}
