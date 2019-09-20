package com.company.service;

import com.company.dao.RoleDao;
import com.company.dao.UserDao;
import com.company.model.Role;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@PropertySource(value = "classpath:mail.properties")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final Environment env;

    public static int userIdBuffer;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           RoleDao roleDao,
                           PasswordEncoder passwordEncoder,
                           MailService mailService,
                           Environment env) {

        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.env = env;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        user.setCreatedAt(new Date(new java.util.Date().getTime()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(2));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format(
                "%s, welcome to our platform!\n" +
                        "To activate your profile, visit next link:\n" +
                        "%s/activateEmail/%s",
                user.getFirstName(),
                env.getRequiredProperty("dealer.stat.url"),
                user.getActivationCode()
        );

        mailService.sendMessage(user.getEmail(), "Activation code", message);

        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        user.setRoles(userDao.getById(user.getId()).getRoles());
        user.setPosts(userDao.getById(user.getId()).getPosts());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public void activateUser(String code) {
        User user = userDao.getById(findUserIdByCode(code));
        user.setActivationCode(null);
        userDao.updateUser(user);
    }

    @Override
    public void sendMessage(User user) {
        user.setActivationCode(UUID.randomUUID().toString());
        userDao.updateUser(user);

        String message = String.format(
                "Hello, %s!\n" +
                        "To reset your password, visit next link:\n" +
                        "%s/activatePassword/%s",
                user.getFirstName(),
                env.getRequiredProperty("dealer.stat.url"),
                user.getActivationCode()
        );

        mailService.sendMessage(user.getEmail(), "Resetting Password", message);
    }

    @Override
    public List<User> getUserListWithoutAdmin() {
        List<User> userList = userDao.getAllUsers();
        List<User> result = new ArrayList<>();

        for (User u : userList) {
            if ("admin".equals(u.getEmail()) || "guest".equals(u.getEmail())) {
                continue;
            }

            result.add(u);
        }

        return result;
    }

    @Override
    public int findUserIdByCode(String code) {
        int id = 0;

        List<User> userList = userDao.getAllUsers();
        for (User user : userList) {
            if (code.equals(user.getActivationCode())) {
                id = user.getId();
                break;
            }
        }

        return id;
    }

    @Override
    public int findCurrentUserIdByEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = 0;

        List<User> userList = userDao.getAllUsers();
        for (User u : userList) {
            if (auth.getName().equals(u.getEmail())) {
                id = u.getId();
                break;
            }
        }

        return id;
    }
}
