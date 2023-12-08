import java.io.*;
import java.util.*;

public class HotelService extends Service {
    private Map<String, RoomDetails> roomInventory;

    public HotelService() throws IOException {
        super("Hotel Service", 0.0);
        this.roomInventory = new HashMap<>();
        loadRoomData("/Users/hoon/IdeaProjects/A11_FinalProject/src/hotels.txt"); // Adjust the file path as needed
    }

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


    public void addRoomToFile(RoomType roomType, String hotelCompany, String location, double pricePerNight) {
        try (FileWriter fw = new FileWriter("/Users/hoon/IdeaProjects/A11_FinalProject/src/hotels.txt", true); // Make sure the file path is correct
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(roomType.name() + "," + hotelCompany + "," + location + "," + pricePerNight);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }



    @Override
    public void bookService(User user, String roomKey) {
        RoomDetails room = roomInventory.get(roomKey);
        if (room != null) {
            user.addBookedService(room);
        } else {
            System.out.println("Room is not available.");
        }
    }


    public Map<String, RoomDetails> getRoomInventory() {
        return roomInventory;
    }

    public void setRoomInventory(Map<String, RoomDetails> roomInventory) {
        this.roomInventory = roomInventory;
    }
}
