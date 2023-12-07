import java.io.*;
import java.util.*;

public class HotelService extends Service {
    private Map<String, RoomDetails> roomInventory;

    public HotelService(String name, double price) {
        super(name, price);
        this.roomInventory = new HashMap<>();
    }

    @Override
    public void bookService() {
        // Logic to book a hotel room
    }

    // Hotel-specific methods
    public void addRoom(String roomId, RoomDetails details) {
        roomInventory.put(roomId, details);
    }

    public Map<String, RoomDetails> getRoomInventory() {
        return roomInventory;
    }

    public void setRoomInventory(Map<String, RoomDetails> roomInventory) {
        this.roomInventory = roomInventory;
    }
}
