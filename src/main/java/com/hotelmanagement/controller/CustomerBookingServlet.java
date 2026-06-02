package com.hotelmanagement.controller;

import com.hotelmanagement.model.Room;
import com.hotelmanagement.model.User;

import com.hotelmanagement.service.BookingService;
import com.hotelmanagement.service.RoomService;

import com.hotelmanagement.service.impl.BookingServiceImpl;
import com.hotelmanagement.service.impl.RoomServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/customer/book-room")
public class CustomerBookingServlet
        extends HttpServlet {

    private BookingService bookingService;

    private RoomService roomService;

    @Override
    public void init() {

        bookingService =
                new BookingServiceImpl();

        roomService =
                new RoomServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Long roomId =
                Long.parseLong(
                        request.getParameter(
                                "roomId"
                        )
                );

        Room room =
                roomService
                        .getRoomById(roomId)
                        .orElseThrow();

        request.setAttribute(
                "room",
                room
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/customer/book-room.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        User user =
                (User) request
                        .getSession()
                        .getAttribute(
                                "loggedInUser"
                        );

        Long roomId =
                Long.parseLong(
                        request.getParameter(
                                "roomId"
                        )
                );

        LocalDate checkIn =
                LocalDate.parse(
                        request.getParameter(
                                "checkIn"
                        )
                );

        LocalDate checkOut =
                LocalDate.parse(
                        request.getParameter(
                                "checkOut"
                        )
                );

        bookingService.bookRoom(
                user.getUserId(),
                roomId,
                checkIn,
                checkOut
        );

        response.sendRedirect(
                request.getContextPath()
                        + "/customer/bookings"
        );
    }
}
