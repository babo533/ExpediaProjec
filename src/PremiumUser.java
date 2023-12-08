public class PremiumUser extends RegularUser {
    public PremiumUser(String username, String password, UserTypeStrategy userTypeStrategy) {
        super(username, password, new PremiumUserStrategy());

    }

    private static UserTypeStrategy createPremiumUserStrategy() {
        // Initialize and return a new PremiumUserStrategy
        return new PremiumUserStrategy();
    }

    // Additional methods
}
