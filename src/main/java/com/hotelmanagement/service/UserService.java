package com.hotelmanagement.service;

import com.hotelmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(User user);

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long userId);

    Optional<User> login(String email, String password);
}
