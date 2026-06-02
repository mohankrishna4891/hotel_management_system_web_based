package com.hotelmanagement.controller;

import com.hotelmanagement.service.RoomService;
import com.hotelmanagement.service.impl.RoomServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/delete-room")
public class DeleteRoomServlet
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
            throws IOException {

        Long roomId =
                Long.parseLong(
                        request.getParameter("id")
                );

        roomService.deleteRoom(roomId);

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/rooms"
        );
    }
}
