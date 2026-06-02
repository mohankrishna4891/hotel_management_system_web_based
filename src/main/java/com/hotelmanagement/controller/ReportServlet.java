package com.hotelmanagement.controller;

import com.hotelmanagement.service.ReportService;
import com.hotelmanagement.service.impl.ReportServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/reports")
public class ReportServlet
        extends HttpServlet {

    private ReportService reportService;

    @Override
    public void init() {

        reportService =
                new ReportServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String report =
                    reportService
                            .generateRevenueReport()
                            .get();

            request.setAttribute(
                    "report",
                    report
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/report.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}
