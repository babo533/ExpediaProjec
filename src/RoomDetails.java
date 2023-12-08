import java.io.*;
import java.util.*;

import java.util.*;

public class RoomDetails {
    private RoomType roomType; // Assuming RoomType is an enum you've defined
    private String hotelCompany;
    private String location;
    private double pricePerNight;

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

    // Optional: Override toString() for easy printing of RoomDetails
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
