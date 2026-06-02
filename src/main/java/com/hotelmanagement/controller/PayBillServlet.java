package com.hotelmanagement.controller;

import com.hotelmanagement.model.Bill;

import com.hotelmanagement.service.BillingService;
import com.hotelmanagement.service.impl.BillingServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/customer/pay-bill")
public class PayBillServlet
        extends HttpServlet {

    private BillingService billingService;

    @Override
    public void init() {

        billingService =
                new BillingServiceImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Long billId =
                    Long.parseLong(
                            request.getParameter(
                                    "billId"
                            )
                    );

            String paymentMethod =
                    request.getParameter(
                            "paymentMethod"
                    );

            Bill bill =
                    billingService
                            .payBill(
                                    billId, paymentMethod
                            );

            request.setAttribute(
                    "bill",
                    bill
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/customer/payment-success.jsp"
            ).forward(
                    request,
                    response
            );

        } catch (Exception e) {

            throw new ServletException(
                    e
            );
        }
    }
}
