package com.hotelmanagement.controller;

import com.hotelmanagement.model.Room;
import com.hotelmanagement.service.RoomService;
import com.hotelmanagement.service.impl.RoomServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/rooms")
public class AdminRoomServlet
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

        request.setAttribute(
                "rooms",
                rooms
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/rooms.jsp"
        ).forward(request, response);
    }
}
