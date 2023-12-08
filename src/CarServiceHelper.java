public class CarServiceHelper extends Service {
    private CarDetails carDetails;

    public CarServiceHelper(CarDetails carDetails) {
        super(carDetails.getModel() + " (" + carDetails.getType() + ")", carDetails.getPricePerDay());
        this.carDetails = carDetails;
    }

    @Override
    public void bookService(User user, String serviceName) {
        user.addBookedService(this);
        System.out.println("Car rental booked successfully for " + user.getUsername());
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    @Override
    public String toString() {
        return "Car Model: " + carDetails.getModel() + ", Type: " + carDetails.getType() + ", Price per Day: " + getPrice();
    }
}
