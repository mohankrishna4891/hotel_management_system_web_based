package com.hotelmanagement.service.impl;

import com.hotelmanagement.repository.BookingDAO;
import com.hotelmanagement.repository.RoomDAO;
import com.hotelmanagement.repository.UserDAO;
import com.hotelmanagement.repository.impl.BookingDAOImpl;
import com.hotelmanagement.repository.impl.RoomDAOImpl;
import com.hotelmanagement.repository.impl.UserDAOImpl;
import com.hotelmanagement.model.Booking;
import com.hotelmanagement.model.Room;
import com.hotelmanagement.model.User;
import com.hotelmanagement.model.enums.BookingStatus;
import com.hotelmanagement.model.enums.RoomStatus;
import com.hotelmanagement.exception.ResourceNotFoundException;
import com.hotelmanagement.service.BookingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {

    private final BookingDAO bookingDAO =
            new BookingDAOImpl();

    private final UserDAO userDAO =
            new UserDAOImpl();

    private final RoomDAO roomDAO =
            new RoomDAOImpl();

    @Override
    public synchronized Booking bookRoom(Long userId,
                            Long roomId,
                            LocalDate checkIn,
                            LocalDate checkOut) {

        User user = userDAO.findUserById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        System.out.println("user: " + user);

        Room room = roomDAO.findRoomById(roomId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));
        System.out.println("room: " + room);

        if (room.getStatus() != RoomStatus.AVAILABLE) {

            throw new ResourceNotFoundException(
                    "Room is not available for booking"
            );
        }
        System.out.println("booking room: " + room);

        room.setStatus(RoomStatus.OCCUPIED);
        System.out.println("Status set done");

        Booking booking = new Booking(
                user,
                room,
                checkIn,
                checkOut,
                BookingStatus.CONFIRMED
        );
        System.out.println("booking: " + booking);

        user.setBookings(List.of(booking));
        System.out.println("user booking added");

        room.addBooking(booking);
        System.out.println("room booking added");

        bookingDAO.saveBooking(booking);
        System.out.println("booking added");

        roomDAO.updateRoom(room);
        System.out.println("room updated");

        return booking;
    }

    @Override
    public Optional<Booking> getBookingById(Long bookingId) {
        return Optional.empty();
    }

    @Override
    public List<Booking> getAllBookings() {

        return bookingDAO.getAllBookings();
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {

        return bookingDAO.getBookingsByUserId(userId);
    }

    @Override
    public Booking checkIn(Long bookingId) {

        return bookingDAO.checkIn(
                bookingId
        );
    }

    @Override
    public Booking checkOut(Long bookingId) {

        return bookingDAO.checkOut(
                bookingId
        );
    }
}
