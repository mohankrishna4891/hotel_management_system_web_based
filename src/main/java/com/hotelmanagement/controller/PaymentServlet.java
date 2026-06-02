package com.hotelmanagement.controller;

import com.hotelmanagement.model.Bill;
import com.hotelmanagement.service.BillingService;
import com.hotelmanagement.service.impl.BillingServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customer/payment")
public class PaymentServlet
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

        Long billId =
                Long.parseLong(
                        request.getParameter(
                                "billId"
                        )
                );

        Bill bill =
                billingService
                        .findBillById(
                                billId
                        )
                        .orElseThrow();

        request.setAttribute(
                "bill",
                bill
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/customer/payment.jsp"
        ).forward(
                request,
                response
        );
    }
}
