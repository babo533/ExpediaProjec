public class Main {
    public static void main(String[] args) {
        BFF bff = new BFF(); // Assuming BFF class is used for input handling
        RentalCarService rentalCarService = new RentalCarService();

        while (true) {
            System.out.println("\\nWelcome to the Car Rental Service!");
            System.out.println("1. View Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Exit");
            int choice = bff.inputInt("Choose an option", 1, 3);

            switch (choice) {
                case 1:
                    rentalCarService.displayAvailableCars();
                    break;
                case 2:
                    rentalCarService.bookService();
                    break;
                case 3:
                    System.out.println("Thank you for using our service!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
