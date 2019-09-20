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

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/users", "/"}, method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/users");
        modelAndView.addObject("userList", userService.getUserListWithoutAdmin());
        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user");
        modelAndView.addObject("user", userService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profile/edit");
        modelAndView.addObject("user", userService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/user/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/" + user.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/user/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(userService.getById(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login?logout=true");
        return modelAndView;
    }
}
