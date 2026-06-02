package com.hotelmanagement.controller;

import com.hotelmanagement.model.Room;
import com.hotelmanagement.model.enums.RoomStatus;

import com.hotelmanagement.service.RoomService;
import com.hotelmanagement.service.impl.RoomServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/rooms")
public class CustomerRoomServlet
        extends HttpServlet {

    private RoomService roomService;

    @Override
    public void init() {

        roomService =
                new RoomServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Room> rooms =
                roomService.getAllRooms();

        List<Room> availableRooms =
                rooms.stream()
                        .filter(room ->
                                room.getStatus()
                                        ==
                                        RoomStatus.AVAILABLE)
                        .toList();

        request.setAttribute(
                "rooms",
                availableRooms
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/customer/available-rooms.jsp"
        ).forward(request, response);
    }
}
