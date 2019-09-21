package com.company.service;

import com.company.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getById(int id);

    void activateUser(String code);

    void sendMessage(User user);

    List<User> getUserListWithoutAdmin();

    int findUserIdByEmail(String email);

    int findUserIdByCode(String code);

    int findCurrentUserIdByEmail();
}
