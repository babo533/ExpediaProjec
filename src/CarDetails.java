public class CarDetails {
    private String model;
    private String type;
    private double pricePerDay;

    public CarDetails(String model, String type, double pricePerDay) {
        this.model = model;
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Model: " + model + ", Price per Day: " + pricePerDay;
    }
}
