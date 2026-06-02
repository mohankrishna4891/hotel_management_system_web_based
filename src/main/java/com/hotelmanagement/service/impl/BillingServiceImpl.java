package com.hotelmanagement.service.impl;

import com.hotelmanagement.repository.BillDAO;
import com.hotelmanagement.repository.BookingDAO;
import com.hotelmanagement.repository.RoomDAO;
import com.hotelmanagement.repository.impl.BillDAOImpl;
import com.hotelmanagement.repository.impl.BookingDAOImpl;
import com.hotelmanagement.repository.impl.RoomDAOImpl;
import com.hotelmanagement.model.Bill;
import com.hotelmanagement.model.Booking;
import com.hotelmanagement.model.Room;
import com.hotelmanagement.model.enums.PaymentStatus;
import com.hotelmanagement.model.enums.RoomStatus;
import com.hotelmanagement.exception.ResourceNotFoundException;
import com.hotelmanagement.service.BillingService;
import java.time.temporal.ChronoUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.Scanner;

public class BillingServiceImpl implements BillingService {

    private final BillDAO billDAO =
            new BillDAOImpl();
    private final RoomDAO roomdao = new RoomDAOImpl();

    private final BookingDAO bookingDAO =
            new BookingDAOImpl();

    @Override
    public Bill payBill(
            long billId,
            String paymentMethod
    ) {

        Bill bill =
                billDAO
                        .findBillById(billId)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Bill not found"
                                        )
                        );

        if (bill.getPaymentStatus()
                == PaymentStatus.PAID) {

            return bill;
        }

        System.out.println(
                "Paid using "
                        + paymentMethod
        );

        bill.setPaymentStatus(
                PaymentStatus.PAID
        );

        Room room =
                bill.getBooking()
                        .getRoom();

        room.setStatus(
                RoomStatus.AVAILABLE
        );

        roomdao.updateRoom(room);

        return billDAO.updateBill(
                bill
        );
    }

    @Override
    public Bill generateBill(Long bookingId,
                             BigDecimal taxPercentage,
                             BigDecimal discountPercentage) {

        Booking booking = bookingDAO
                .findBookingById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found"));

        long stayDays =
                ChronoUnit.DAYS.between(
                        booking.getCheckIn(),
                        booking.getCheckOut()
                );

        BigDecimal basePrice =
                booking.getRoom()
                        .getPrice()
                        .multiply(
                                BigDecimal.valueOf(
                                        stayDays
                                )
                        );

        BigDecimal taxAmount =
                basePrice.multiply(taxPercentage)
                        .divide(new BigDecimal("100"),
                                2,
                                RoundingMode.HALF_UP);

        BigDecimal discountAmount =
                basePrice.multiply(discountPercentage)
                        .divide(new BigDecimal("100"),
                                2,
                                RoundingMode.HALF_UP);

        BigDecimal finalAmount =
                basePrice
                        .add(taxAmount)
                        .subtract(discountAmount);

        Bill bill = new Bill(
                booking,
                finalAmount,
                taxAmount,
                discountAmount,
                PaymentStatus.PENDING
        );

        billDAO.saveBill(bill);

        return bill;
    }

    @Override
    public Optional<Bill> findBillByBookingId(
            Long bookingId
    ) {

        return billDAO.findBillByBookingId(
                bookingId
        );
    }

    @Override
    public Optional<Bill> findBillById(
            Long billId
    ) {

        return billDAO.findBillById(
                billId
        );
    }

}
