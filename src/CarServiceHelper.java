/**
 * The CarServiceHelper class extends Service to specifically handle car rental services.
 * It includes details about the car and implements methods for booking the service.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class CarServiceHelper extends Service {
    private CarDetails carDetails; // Details of the car for the service.

    /**
     * Constructs a new CarServiceHelper instance with given car details.
     *
     * @param carDetails The details of the car for the service.
     */
    public CarServiceHelper(CarDetails carDetails) {
        // Calls the constructor of the superclass (Service) with the model and type of the car and its price per day.
        super(carDetails.getModel() + " (" + carDetails.getType() + ")", carDetails.getPricePerDay());
        this.carDetails = carDetails;
    }

    /**
     * Books the car service for a user.
     *
     * @param user        The user for whom the service is being booked.
     * @param serviceName The name of the service being booked.
     */
    @Override
    public void bookService(User user, String serviceName) {
        user.addBookedService(this); // Adds this service to the user's list of booked services.
        System.out.println("Car rental booked successfully for " + user.getUsername()); // Confirmation message.
    }

    /**
     * Gets the car details associated with this service.
     *
     * @return The CarDetails instance associated with this service.
     */
    public CarDetails getCarDetails() {
        return carDetails;
    }

    /**
     * Returns a string representation of the CarServiceHelper instance.
     *
     * @return A string representation of this CarServiceHelper instance.
     */
    @Override
    public String toString() {
        return "Car Model: " + carDetails.getModel() + ", Type: " + carDetails.getType() + ", Price per Day: " + getPrice();
    }
}

