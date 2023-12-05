public interface InventoryManagement {
    void addFlight(String startLocation, FlightType flightType, String endLocation);
    void addCar(String carType, String carModel, double pricePerDay);
    void addHotelRoom(String location, RoomType roomType, double pricePerNight);
}

