package com.hotelmanagement.service;

import com.hotelmanagement.model.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    Booking bookRoom(Long userId,
                     Long roomId,
                     LocalDate checkIn,
                     LocalDate checkOut);

    Optional<Booking> getBookingById(Long bookingId);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByUserId(Long userId);

    Booking checkIn(Long bookingId);

    Booking checkOut(Long bookingId);
}
