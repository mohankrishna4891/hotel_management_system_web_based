package com.hotelmanagement.model;

import com.hotelmanagement.model.enums.RoomStatus;
import com.hotelmanagement.model.enums.RoomType;
import jakarta.persistence.*;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @OneToMany(mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Room() {
    }

    public Room(String roomNumber,
                RoomType roomType,
                BigDecimal price,
                RoomStatus status) {

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public void addBooking(Booking booking) {
        List<Booking> bookingList = List.of(booking);
        setBookings(bookingList);
    }

    public void removeBooking(Booking booking) {

        bookings.remove(booking);

        booking.setRoom(null);
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomType=" + roomType +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
