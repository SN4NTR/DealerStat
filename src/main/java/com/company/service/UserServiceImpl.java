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

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

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

    public void sendMessage(User user) {
        user.setActivationCode(UUID.randomUUID().toString());
        userDao.updateUser(user);

        String message = String.format(
                "Hello, %s!\n" +
                        "To reset your password, visit next link: http://localhost:8080/activate/%s",
                user.getFirstName(), user.getActivationCode()
        );
        mailService.sendMessage(user.getEmail(), "Resetting Password", message);
    }

    private int findUserIdByCode(String code) {
        int userId = 0;

        List<User> userList = userDao.getAllUsers();
        for (User user : userList) {
            if (code.equals(user.getActivationCode())) {
                userId = user.getId();
            }
        }

        return userId;
    }
}
