package com.hotelmanagement.service.impl;

import com.hotelmanagement.repository.UserDAO;
import com.hotelmanagement.repository.impl.UserDAOImpl;
import com.hotelmanagement.model.User;
import com.hotelmanagement.exception.AuthenticationException;
import com.hotelmanagement.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void registerUser(User user) {

        if (user == null) {
            throw new AuthenticationException("User cannot be null");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new AuthenticationException(
                    "Email cannot be empty"
            );
        }

        userDAO.saveUser(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {

        return userDAO.findUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {

        return userDAO.findAllUsers();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public Optional<User> login(String email, String password) {

        if (email == null || email.isBlank()) {
            throw new AuthenticationException(
                    "Email cannot be empty"
            );
        }

        if (password == null || password.isBlank()) {
            throw new AuthenticationException("Password cannot be empty");
        }

        Optional<User> optionalUser = userDAO.findUserByEmail(email);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
