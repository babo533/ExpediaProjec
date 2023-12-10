import java.io.*;
import java.util.*;

import java.util.*;

/**
 * The RoomDetails class represents the details of a hotel room.
 * It includes information such as the type of room, the hotel company, the location of the hotel, and the price per night
 *  * @author Seung Hoon Lee
 *  * ITP 265, tea
 *  * Email: slee3471@usc.edu.
 */
public class RoomDetails {
    private RoomType roomType; // The type of the room (e.g., STANDARD, DELUXE).
    private String hotelCompany; // The name of the hotel company.
    private String location; // The location of the hotel.
    private double pricePerNight; // The price per night for the room.

    /**
     * Constructs a new {@code RoomDetails} instance with specified room type, hotel company, location, and price per night.
     *
     * @param roomType      The type of the hotel room.
     * @param hotelCompany  The company that owns the hotel.
     * @param location      The location of the hotel.
     * @param pricePerNight The price per night of the room.
     */
    public RoomDetails(RoomType roomType, String hotelCompany, String location, double pricePerNight) {
        this.roomType = roomType;
        this.hotelCompany = hotelCompany;
        this.location = location;
        this.pricePerNight = pricePerNight;
    }

    // Getters and Setters
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getHotelCompany() {
        return hotelCompany;
    }

    public void setHotelCompany(String hotelCompany) {
        this.hotelCompany = hotelCompany;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     *
     * @return A string representation of this RoomDetails instance.
     */
    @Override
    public String toString() {
        return "RoomDetails{" +
                "roomType=" + roomType +
                ", hotelCompany='" + hotelCompany + '\'' +
                ", location='" + location + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
