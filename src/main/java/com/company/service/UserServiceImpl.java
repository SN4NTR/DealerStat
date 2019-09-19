package com.company.service;

import com.company.dao.RoleDao;
import com.company.dao.UserDao;
import com.company.model.Role;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        user.setCreatedAt(new Date(new java.util.Date().getTime()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRoleById(2));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format(
                "%s, welcome to our platform!\n" +
                        "To activate your profile, visit next link: http://localhost:8080/activate/%s",
                user.getFirstName(), user.getActivationCode()
        );
        mailService.sendMessage(user.getEmail(), "Activation code", message);

        userDao.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<Role> getRoles() {
        return userDao.getRoles();
    }

    @Override
    public void activateUser(String code) {
        User user = userDao.getById(findUserIdByCode(code));

        user.setActivationCode(null);
        userDao.updateUser(user);
    }

    private int findUserIdByCode(String code) {
        int userId = 0;

        List<User> userList = userDao.getAllUsers();
        for (User user : userList) {
            if (code.equals(user.getEmail())) {
                userId = user.getId();
            }
        }

        return userId;
    }
}
