package com.hotelmanagement.controller;

import com.hotelmanagement.model.Booking;
import com.hotelmanagement.service.BookingService;
import com.hotelmanagement.service.impl.BookingServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/receptionist/bookings")
public class ReceptionistBookingsServlet
        extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() {

        bookingService =
                new BookingServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        List<Booking> bookings =
                bookingService.getAllBookings();

        request.setAttribute(
                "bookings",
                bookings
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/receptionist/bookings.jsp"
        ).forward(
                request,
                response
        );
    }


}
