public class HotelServiceHelper extends Service {
    private RoomDetails roomDetails;

    public HotelServiceHelper(RoomDetails roomDetails) {
        super(roomDetails.getHotelCompany() + " - " + roomDetails.getRoomType() + " in " + roomDetails.getLocation(), roomDetails.getPricePerNight());
        this.roomDetails = roomDetails;
    }

    @Override
    public void bookService(User user, String serviceName) {
        user.addBookedService(this);
        System.out.println("Hotel room booked successfully for " + user.getUsername());
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    @Override
    public String toString() {
        return "Hotel: " + roomDetails.getHotelCompany() + ", Room Type: " + roomDetails.getRoomType() + ", Location: " + roomDetails.getLocation() + ", Price per Night: " + getPrice();
    }
}
