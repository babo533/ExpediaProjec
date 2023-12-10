import java.io.*;
import java.util.*;

/**
 * The HotelService class extends the Service class to manage hotel room-related services.
 * It includes functionalities for loading room data from a file, adding new rooms to the service, and booking rooms for users.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class HotelService extends Service {
    private Map<String, RoomDetails> roomInventory;

    /**
     * Constructs a new {@code HotelService} instance and loads room data from a specified file.
     *
     * @throws IOException If there is an error in reading the room data file.
     */
    public HotelService() throws IOException {
        super("Hotel Service", 0.0);
        this.roomInventory = new HashMap<>();
        loadRoomData("/Users/hoon/IdeaProjects/A11_FinalProject/src/hotels.txt"); // Adjust the file path as needed
    }

    /**
     * Loads room data from a specified file into the room inventory.
     *
     * @param filePath The path to the file containing room data.
     */
    public void loadRoomData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        RoomType roomType = RoomType.valueOf(parts[0].trim().toUpperCase());
                        String hotelCompany = parts[1].trim();
                        String location = parts[2].trim();
                        double pricePerNight = Double.parseDouble(parts[3].trim());
                        // Create RoomDetails object and add it to the inventory
                    }
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * Adds a new room to the room data file.
     *
     * @param roomType       The type of the room (e.g., STANDARD, DELUXE).
     * @param hotelCompany   The name of the hotel company.
     * @param location       The location of the hotel.
     * @param pricePerNight  The price per night for the room.
     */
    public void addRoomToFile(RoomType roomType, String hotelCompany, String location, double pricePerNight) {
        try (FileWriter fw = new FileWriter("/Users/hoon/IdeaProjects/A11_FinalProject/src/hotels.txt", true); // Make sure the file path is correct
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(roomType.name() + "," + hotelCompany + "," + location + "," + pricePerNight);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Books a hotel room service for a user.
     *
     * @param user    The user who is booking the room.
     * @param roomKey The key representing the specific room in the room inventory.
     */
    @Override
    public void bookService(User user, String roomKey) {
        RoomDetails room = roomInventory.get(roomKey);
        if (room != null) {
            user.addBookedService(room);
        } else {
            System.out.println("Room is not available.");
        }
    }

    /**
     * Retrieves the room inventory map.
     *
     * @return The map containing all rooms in the inventory.
     */
    public Map<String, RoomDetails> getRoomInventory() {
        return roomInventory;
    }

    /**
     * Sets the room inventory map.
     *
     * @param roomInventory The map of room details to set as the inventory.
     */
    public void setRoomInventory(Map<String, RoomDetails> roomInventory) {
        this.roomInventory = roomInventory;
    }
}
