/**
 * The PremiumUser class represents a user with premium privileges.
 * It extends the RegularUser class, providing functionalities tailored to premium users.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class PremiumUser extends RegularUser {
    /**
     * Constructs a new PremiumUser instance with the provided username, password, and user type strategy.
     * The user type strategy is specifically set for premium users.
     *
     * @param username            The username of the premium user.
     * @param password            The password of the premium user.
     * @param userTypeStrategy    The strategy for user type, specifically for premium users.
     */
    public PremiumUser(String username, String password, UserTypeStrategy userTypeStrategy) {
        super(username, password, createPremiumUserStrategy());
    }

    /**
     * Creates and returns a PremiumUserStrategy instance.
     * This method initializes and provides the specific strategy used for premium users.
     *
     * @return A new instance of PremiumUserStrategy
     */
    private static UserTypeStrategy createPremiumUserStrategy() {
        // Initialize and return a new PremiumUserStrategy.
        return new PremiumUserStrategy();
    }

    // Additional methods
}
