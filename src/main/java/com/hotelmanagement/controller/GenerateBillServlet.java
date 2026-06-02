package com.hotelmanagement.controller;

import com.hotelmanagement.model.Bill;

import com.hotelmanagement.service.BillingService;
import com.hotelmanagement.service.impl.BillingServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/customer/generate-bill")
public class GenerateBillServlet
        extends HttpServlet {

    private BillingService billingService;

    @Override
    public void init() {

        billingService =
                new BillingServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Long bookingId =
                    Long.parseLong(
                            request.getParameter(
                                    "bookingId"
                            )
                    );

            Bill bill =
                    billingService
                            .findBillByBookingId(
                                    bookingId
                            )
                            .orElseGet(() ->
                                    billingService
                                            .generateBill(
                                                    bookingId,
                                                    new BigDecimal("18"),
                                                    new BigDecimal("10")
                                            )
                            );;

            request.setAttribute(
                    "bill",
                    bill
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/customer/bill.jsp"
            ).forward(
                    request,
                    response
            );

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}
