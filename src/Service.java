public abstract class Service {
    private String name;
    private double price;
    private double rating;

    public Service(String name, double price, double rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void applyDiscount(double discountRate) {
        this.price *= (1 - discountRate);
    }
    // Abstract method to be implemented by subclasses
    public abstract void bookService();
}
