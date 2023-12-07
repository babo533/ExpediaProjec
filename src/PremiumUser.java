public class PremiumUser extends RegularUser {
    public PremiumUser(String username, String password) {
        super(username, password, new PremiumUserStrategy());
    }

    // Additional methods
}
