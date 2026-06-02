package com.hotelmanagement.repository;

import com.hotelmanagement.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingDAO {

    void saveBooking(Booking booking);

    Optional<Booking> findBookingById(Long bookingId);

    List<Booking> findAllBookings();

    void updateBooking(Booking booking);

    void deleteBooking(Long bookingId);

    List<Booking> getBookingsByUserId(Long userId);

    List<Booking> getAllBookings();

    Booking checkIn(Long bookingId);

    Booking checkOut(Long bookingId);
}