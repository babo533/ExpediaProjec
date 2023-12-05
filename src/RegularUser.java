import java.io.*;
import java.util.*;


public class RegularUser extends User {
    public RegularUser(String username, String password) {
        super(username, password);
        this.userType = "Regular";
    }

    public boolean login(String filePath) {
        boolean isAuthenticated = false;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] userDetails = scanner.nextLine().split(",");
                if (userDetails.length == 3) {
                    String userType = userDetails[0];
                    String username = userDetails[1];
                    String password = userDetails[2];
                    if (this.username.equals(username) && this.password.equals(password) && userType.equals("Regular")) {
                        isAuthenticated = true;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }

    public boolean authenticate(String filePath) {
        return false;
    }

    @Override
    protected boolean isCorrectUserType(String userType) {
        return "Regular".equals(userType);
    }
}