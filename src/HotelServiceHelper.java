/**
 * The HotelServiceHelper class extends Service and is specifically designed for managing hotel room services.
 * It encapsulates the details of a hotel room and provides functionality to book the room service.
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu
 */
public class HotelServiceHelper extends Service {
    private RoomDetails roomDetails;

    /**
     * Constructs a new {@code HotelServiceHelper} instance with the provided room details.
     *
     * @param roomDetails The details of the hotel room to be handled by this service helper.
     */
    public HotelServiceHelper(RoomDetails roomDetails) {
        super(roomDetails.getHotelCompany() + " - " + roomDetails.getRoomType() + " in " + roomDetails.getLocation(), roomDetails.getPricePerNight());
        this.roomDetails = roomDetails;
    }

    /**
     * Books this hotel room service for a user.
     *
     * @param user        The user for whom the service is being booked.
     * @param serviceName The name of the service being booked. This can be used for more complex booking logic if required.
     */
    @Override
    public void bookService(User user, String serviceName) {
        user.addBookedService(this);
        System.out.println("Hotel room booked successfully for " + user.getUsername());
    }

    /**
     * Gets the room details associated with this service.
     *
     * @return The {@code RoomDetails} instance associated with this service.
     */
    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    /**
     * Returns a string representation of the {@code HotelServiceHelper} instance.
     *
     * @return A string representation of this {@code HotelServiceHelper} instance.
     */
    @Override
    public String toString() {
        return "Hotel: " + roomDetails.getHotelCompany() + ", Room Type: " + roomDetails.getRoomType() + ", Location: " + roomDetails.getLocation() + ", Price per Night: " + getPrice();
    }
}
