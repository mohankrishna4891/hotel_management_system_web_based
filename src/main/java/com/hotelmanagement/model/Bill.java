package com.hotelmanagement.model;

import com.hotelmanagement.model.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(name = "total_amount",
            nullable = false,
            precision = 10,
            scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false,
            precision = 10,
            scale = 2)
    private BigDecimal tax;

    @Column(nullable = false,
            precision = 10,
            scale = 2)
    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    public Bill() {
    }

    public Bill(Booking booking,
                BigDecimal totalAmount,
                BigDecimal tax,
                BigDecimal discount,
                PaymentStatus paymentStatus) {

        this.booking = booking;
        this.totalAmount = totalAmount;
        this.tax = tax;
        this.discount = discount;
        this.paymentStatus = paymentStatus;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", booking=" + booking +
                ", totalAmount=" + totalAmount +
                ", tax=" + tax +
                ", discount=" + discount +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
