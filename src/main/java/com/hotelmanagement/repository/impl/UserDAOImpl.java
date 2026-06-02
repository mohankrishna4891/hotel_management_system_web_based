package com.hotelmanagement.repository.impl;

import com.hotelmanagement.config.HibernateUtil;
import com.hotelmanagement.repository.UserDAO;
import com.hotelmanagement.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public void saveUser(User user) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public Optional<User> findUserById(Long userId) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            User user = session.find(User.class, userId);

            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAllUsers() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            return session.createQuery(
                    "FROM User",
                    User.class
            ).list();
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            User user = session.createQuery(
                            "FROM User WHERE email = :email",
                            User.class
                    )
                    .setParameter("email", email)
                    .uniqueResult();

            return Optional.ofNullable(user);
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long userId) {

    }
}
