package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<User> withoutAdminUserList = new ArrayList<>();
        for (User u : userList) {
            if ("admin".equals(u.getEmail()) || "guest".equals(u.getEmail())) {
                continue;
            }

            withoutAdminUserList.add(u);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/users");
        modelAndView.addObject("userList", withoutAdminUserList);
        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profile/edit");
        modelAndView.addObject("user", userService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/" + user.getId());
        user.setRoles(userService.getById(user.getId()).getRoles());
        user.setPosts(userService.getById(user.getId()).getPosts());
        userService.updateUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.deleteUser(userService.getById(id));
        return modelAndView;
    }
}
