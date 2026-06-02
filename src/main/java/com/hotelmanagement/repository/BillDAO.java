package com.hotelmanagement.repository;

import com.hotelmanagement.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillDAO {

    void saveBill(Bill bill);

    Optional<Bill> findBillById(Long billId);

    Optional<Bill> findBillByBookingId(
            Long bookingId
    );

    List<Bill> findAllBills();

    Bill updateBill(Bill bill);
}
