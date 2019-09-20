package com.company.dao;

import com.company.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getById(int id);
}
