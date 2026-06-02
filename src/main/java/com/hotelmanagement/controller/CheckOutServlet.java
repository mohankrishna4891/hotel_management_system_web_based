package com.hotelmanagement.controller;

import com.hotelmanagement.service.BookingService;
import com.hotelmanagement.service.impl.BookingServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receptionist/checkout")
public class CheckOutServlet
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
            throws IOException {

        Long bookingId =
                Long.parseLong(
                        request.getParameter(
                                "bookingId"
                        )
                );

        bookingService.checkOut(
                bookingId
        );

        response.sendRedirect(
                request.getContextPath()
                        + "/receptionist/bookings"
        );
    }
}
