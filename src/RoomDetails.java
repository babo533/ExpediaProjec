import java.io.*;
import java.util.*;

public class RoomDetails {
    private RoomType roomType; // Assuming RoomType is an enum you've defined
    private int capacity;
    private List<String> amenities;

    public RoomDetails(RoomType roomType, int capacity, List<String> amenities) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.amenities = amenities;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}
