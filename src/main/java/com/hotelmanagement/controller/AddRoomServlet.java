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

@WebServlet("/admin/add-room")
public class AddRoomServlet
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

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/add-room.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String roomNumber =
                request.getParameter(
                        "roomNumber"
                );

        RoomType roomType =
                RoomType.valueOf(
                        request.getParameter(
                                "roomType"
                        )
                );

        BigDecimal price =
                new BigDecimal(
                        request.getParameter(
                                "price"
                        )
                );

        Room room = new Room();

        room.setRoomNumber(roomNumber);

        room.setRoomType(roomType);

        room.setPrice(price);

        room.setStatus(
                RoomStatus.AVAILABLE
        );

        roomService.addRoom(room);

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/rooms"
        );

        if(price.compareTo(
                BigDecimal.ZERO
        ) <= 0){

            throw new IllegalArgumentException(
                    "Price must be greater than zero"
            );
        }
    }
}
