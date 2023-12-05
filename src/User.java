import java.util.*;
import java.io.*;
import java.util.logging.FileHandler;

public abstract class User {
    protected String username;
    protected String password;
    protected boolean isAuthenticated;
    protected String userType;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAuthenticated = false;
    }

    // Getters and setters for isAuthenticated
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    protected void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public boolean login(String username, String password, FileHandler fileHandler) {
        if (authenticate(username, password, fileHandler)) {
            this.isAuthenticated = true;
            // Additional session initialization logic can go here
            return true;
        }
        return false;
    }

    public boolean authenticate(String username, String password, FileHandler fileHandler) {
        try {
            List<String> lines = fileHandler.readLines();
            for (String line : lines) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 3 && userDetails[1].equals(username) && userDetails[2].equals(password)) {
                    return isCorrectUserType(userDetails[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract boolean isCorrectUserType(String userType);
}
