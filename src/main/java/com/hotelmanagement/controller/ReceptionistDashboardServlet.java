package com.hotelmanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receptionist/dashboard")
public class ReceptionistDashboardServlet
        extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/receptionist/dashboard.jsp"
        ).forward(
                request,
                response
        );
    }
}
