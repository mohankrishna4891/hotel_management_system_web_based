package com.hotelmanagement.repository;

import com.hotelmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void saveUser(User user);

    Optional<User> findUserById(Long userId);

    Optional<User> findUserByEmail(String email);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(Long userId);
}
