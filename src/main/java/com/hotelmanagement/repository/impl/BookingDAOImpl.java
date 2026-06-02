package com.hotelmanagement.repository.impl;

import com.hotelmanagement.config.HibernateUtil;
import com.hotelmanagement.model.enums.BookingStatus;
import com.hotelmanagement.repository.BookingDAO;
import com.hotelmanagement.model.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public void saveBooking(Booking booking) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            session.persist(booking);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException("Failed to save booking", e);
        }
    }

    @Override
    public Optional<Booking> findBookingById(Long bookingId) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            Booking booking = session.createQuery(
                            """
                            
                                    SELECT b
                            FROM Booking b
                            JOIN FETCH b.room
                            JOIN FETCH b.customer
                            WHERE b.bookingId = :bookingId
                            """,
                            Booking.class
                    )
                    .setParameter("bookingId", bookingId)
                    .uniqueResult();

            return Optional.ofNullable(booking);
        }
    }

    @Override
    public List<Booking> findAllBookings() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            return session.createQuery(
                    "FROM Booking",
                    Booking.class
            ).list();
        }
    }

    @Override
    public void updateBooking(Booking booking) {

    }

    @Override
    public void deleteBooking(Long bookingId) {

    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            return session.createQuery(
                                    """
                            FROM Booking b
                            WHERE b.customer.userId = :userId
                            """,
                            Booking.class
                    )
                    .setParameter(
                            "userId",
                            userId
                    )
                    .getResultList();
        }
    }

    @Override
    public List<Booking> getAllBookings() {

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            return session.createQuery(
                    """
                    SELECT b
                    FROM Booking b
                    JOIN FETCH b.customer
                    JOIN FETCH b.room
                    """,
                    Booking.class
            ).getResultList();
        }
    }

    @Override
    public Booking checkIn(Long bookingId) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            transaction =
                    session.beginTransaction();

            Booking booking =
                    session.find(
                            Booking.class,
                            bookingId
                    );

            if (booking == null) {

                throw new RuntimeException(
                        "Booking not found"
                );
            }

            booking.setBookingStatus(
                    BookingStatus.CONFIRMED
            );

            Booking updatedBooking =
                    session.merge(
                            booking
                    );

            transaction.commit();

            return updatedBooking;

        } catch (Exception e) {

            if (transaction != null &&
                    transaction.isActive()) {

                transaction.rollback();
            }

            throw new RuntimeException(
                    "Check-In failed",
                    e
            );
        }
    }

    @Override
    public Booking checkOut(Long bookingId) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            transaction =
                    session.beginTransaction();

            Booking booking =
                    session.get(
                            Booking.class,
                            bookingId
                    );

            if (booking == null) {

                throw new RuntimeException(
                        "Booking not found"
                );
            }

            booking.setBookingStatus(
                    BookingStatus.CHECKED_OUT
            );

            Booking updatedBooking =
                    session.merge(
                            booking
                    );

            transaction.commit();

            return updatedBooking;

        } catch (Exception e) {

            if (transaction != null &&
                    transaction.isActive()) {

                transaction.rollback();
            }

            throw new RuntimeException(
                    "Check-Out failed",
                    e
            );
        }
    }
    }
