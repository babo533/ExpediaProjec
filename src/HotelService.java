import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HotelService extends Service implements InventoryModification {
    private final Map<String, Set<RoomType>> serviceRoomMapping = new HashMap<>();
    private final Map<String, Map<RoomType, Double>> roomInventory = new HashMap<>();


    public HotelService(String serviceID, String serviceName) {
        super(serviceID, serviceName);
        initializeInventory();
        // Initialize the mapping for this serviceID
        serviceRoomMapping.put(serviceID, new HashSet<>());
    }

    public void addRoomTypeToService(String serviceID, RoomType roomType) {
        Set<RoomType> roomTypes = serviceRoomMapping.getOrDefault(serviceID, new HashSet<>());
        roomTypes.add(roomType);
        serviceRoomMapping.put(serviceID, roomTypes);
    }

    public Set<RoomType> getRoomTypesForService(String serviceID) {
        return serviceRoomMapping.getOrDefault(serviceID, new HashSet<>());
    }

    private void initializeInventory() {
        // Sample initialization - this could be replaced with dynamic data
        Map<RoomType, Double> roomsInNY = new HashMap<>();
        roomsInNY.put(RoomType.SUITE, 200.0);
        roomsInNY.put(RoomType.STANDARD_SUITE, 150.0);

        Map<RoomType, Double> roomsInLA = new HashMap<>();
        roomsInLA.put(RoomType.PENTHOUSE, 500.0);
        roomsInLA.put(RoomType.FAMILY_ROOM, 250.0);

        roomInventory.put("New York", roomsInNY);
        roomInventory.put("Los Angeles", roomsInLA);
    }

    // Method to display available rooms
    public void displayAvailableRooms() {
        for (String location : roomInventory.keySet()) {
            System.out.println("Rooms in " + location + ":");
            Map<RoomType, Double> rooms = roomInventory.get(location);
            for (RoomType type : rooms.keySet()) {
                System.out.println("  - " + type + " at $" + rooms.get(type) + " per night");
            }
        }
    }

    @Override
    public void getServiceDetails() {
        System.out.println("Service ID: " + getServiceID());
        System.out.println("Service Name: " + getServiceName());
    }

    public void bookService() {

    }
    // Additional methods for booking rooms, updating inventory, etc., can be added here

    @Override
    public void addItem(String location, Object roomDetails) {
        if (roomDetails instanceof Map) {
            roomInventory.put(location, (Map<RoomType, Double>) roomDetails);
        }
    }

    @Override
    public void updateItem(String location, Object newRoomDetails) {
        if (newRoomDetails instanceof Map) {
            roomInventory.replace(location, (Map<RoomType, Double>) newRoomDetails);
        }
    }
}