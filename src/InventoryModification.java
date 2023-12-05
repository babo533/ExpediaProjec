public interface InventoryModification {
    void addItem(String flightId, Object flightDetails);
    void updateItem(String flightId, Object newFlightDetails);

}

