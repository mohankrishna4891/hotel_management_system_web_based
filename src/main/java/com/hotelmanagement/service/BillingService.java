package com.hotelmanagement.service;

import com.hotelmanagement.model.Bill;

import java.math.BigDecimal;
import java.util.Optional;

public interface BillingService {

    Bill payBill(
            long billId,
            String paymentMethod
    );

    Bill generateBill(Long bookingId,
                      BigDecimal taxPercentage,
                      BigDecimal discountPercentage);

    Optional<Bill> findBillByBookingId(
            Long bookingId
    );

    Optional<Bill> findBillById(
            Long billId
    );
}
