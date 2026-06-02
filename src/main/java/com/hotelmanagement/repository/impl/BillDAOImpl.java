package com.hotelmanagement.repository.impl;

import com.hotelmanagement.config.HibernateUtil;
import com.hotelmanagement.model.Bill;
import com.hotelmanagement.repository.BillDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class BillDAOImpl implements BillDAO {

    @Override
    public void saveBill(Bill bill) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            session.persist(bill);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Failed to save bill",
                    e
            );
        }
    }

    @Override
    public Optional<Bill> findBillById(Long billId) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            Bill bill = session.find(Bill.class, billId);

            return Optional.ofNullable(bill);
        }
    }

    @Override
    public Optional<Bill> findBillByBookingId(
            Long bookingId
    ) {

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            Bill bill =
                    session.createQuery(
                                    """
                                    FROM Bill b
                                    WHERE b.booking.bookingId
                                          = :bookingId
                                    """,
                                    Bill.class
                            )
                            .setParameter(
                                    "bookingId",
                                    bookingId
                            )
                            .uniqueResult();

            return Optional.ofNullable(
                    bill
            );
        }
    }

    @Override
    public List<Bill> findAllBills() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            return session.createQuery(
                    "FROM Bill",
                    Bill.class
            ).list();
        }
    }

    @Override
    public Bill updateBill(Bill bill) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            Bill updatedBill =
                    session.merge(bill);

            transaction.commit();

            return updatedBill;

        } catch (Exception e) {

            if (transaction != null &&
                    transaction.isActive()) {

                transaction.rollback();
            }

            throw new RuntimeException(
                    "Failed to update bill",
                    e
            );
        }
    }


}
