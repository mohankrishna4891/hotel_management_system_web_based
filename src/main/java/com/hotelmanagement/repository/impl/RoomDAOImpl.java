package com.hotelmanagement.repository.impl;

import com.hotelmanagement.config.HibernateUtil;
import com.hotelmanagement.repository.RoomDAO;
import com.hotelmanagement.model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public void saveRoom(Room room) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            session.persist(room);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException("Failed to save room", e);
        }
    }

    @Override
    public Optional<Room> findRoomById(Long roomId) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            Room room = session.find(Room.class, roomId);

            return Optional.ofNullable(room);
        }
    }

    @Override
    public List<Room> findAllRooms() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            return session.createQuery(
                    "FROM Room",
                    Room.class
            ).list();
        }
    }

    @Override
    public void updateRoom(Room room) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            session.merge(room);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Failed to update room",
                    e
            );
        }
    }

    @Override
    public void deleteRoom(Long roomId) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            transaction = session.beginTransaction();

            Room room =
                    session.find(
                            Room.class,
                            roomId
                    );

            if (room != null) {

                session.remove(room);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }

            throw new RuntimeException(
                    "Failed to delete room",
                    e
            );
        }
    }
}
