public abstract class Service {
    private String name;
    private double price;


    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void applyDiscount(double discountRate) {
        this.price *= (1 - discountRate);
    }
    // Abstract method to be implemented by subclasses

    public abstract void bookService(User user, String serviceName);
}
