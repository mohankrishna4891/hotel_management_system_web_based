package com.hotelmanagement.controller;

import com.hotelmanagement.model.Room;
import com.hotelmanagement.model.enums.RoomStatus;
import com.hotelmanagement.model.enums.RoomType;

import com.hotelmanagement.service.RoomService;
import com.hotelmanagement.service.impl.RoomServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/edit-room")
public class EditRoomServlet
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

        Long roomId =
                Long.parseLong(
                        request.getParameter("id")
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
                "/WEB-INF/views/admin/edit-room.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        Long roomId =
                Long.parseLong(
                        request.getParameter("roomId")
                );

        Room room =
                roomService
                        .getRoomById(roomId)
                        .orElseThrow();

        room.setRoomNumber(
                request.getParameter(
                        "roomNumber"
                )
        );

        room.setRoomType(
                RoomType.valueOf(
                        request.getParameter(
                                "roomType"
                        )
                )
        );

        room.setPrice(
                new BigDecimal(
                        request.getParameter(
                                "price"
                        )
                )
        );

        room.setStatus(
                RoomStatus.valueOf(
                        request.getParameter(
                                "status"
                        )
                )
        );

        System.out.println("ROOM ID: " + room.getRoomId());
        System.out.println("ROOM NUMBER: " + room.getRoomNumber());
        System.out.println("PRICE: " + room.getPrice());
        System.out.println("STATUS: " + room.getStatus());

        roomService.updateRoom(room);

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/rooms"
        );
    }
}
