package com.hotelmanagement.controller;

import com.hotelmanagement.model.Booking;
import com.hotelmanagement.model.User;

import com.hotelmanagement.service.BookingService;
import com.hotelmanagement.service.impl.BookingServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/bookings")
public class CustomerBookingsServlet
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
            throws ServletException, IOException {

        User user =
                (User) request
                        .getSession()
                        .getAttribute(
                                "loggedInUser"
                        );

        List<Booking> bookings =
                bookingService
                        .getBookingsByUserId(
                                user.getUserId()
                        );

        request.setAttribute(
                "bookings",
                bookings
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/customer/bookings.jsp"
        ).forward(request, response);
    }
}
