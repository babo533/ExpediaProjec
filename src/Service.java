/**
 * The Service class is an abstract base class for different types of services.
 * It includes common properties such as name and price and defines an abstract method for booking the service.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */

public abstract class Service {
    protected String name; // The name of the service.
    protected double price; // The price of the service.

    /**
     * Constructs a new {@code Service} instance with the specified name and price.
     *
     * @param name  The name of the service.
     * @param price The price of the service.
     */
    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    /**
     * Gets the name of the service.
     *
     * @return The name of the service.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the service.
     *
     * @param name The new name of the service.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the service.
     *
     * @return The price of the service.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the service.
     *
     * @param price The new price of the service.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Applies a discount to the price of the service.
     *
     * @param discountRate The discount rate to be applied.
     */
    public void applyDiscount(double discountRate) {
        this.price *= (0.9);
    }

    /**
     * Abstract method to book the service.
     * This method needs to be implemented by subclasses to define specific booking behaviors.
     *
     * @param user        The user who is booking the service.
     * @param serviceName The name of the service being booked.
     */
    public abstract void bookService(User user, String serviceName);
}
