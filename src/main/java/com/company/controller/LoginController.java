package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import com.company.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView();
        String errorMessage = null;

        if(error != null) {
            errorMessage = "Username or Password is incorrect";
        }
        if(logout != null) {
            errorMessage = "Logged out successfully";
        }

        modelAndView.setViewName("login");
        modelAndView.addObject("errorMessage", errorMessage);
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login?logout=true");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signUp");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(@ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signUp");
            return modelAndView;
        }

        userService.addUser(user);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public ModelAndView resetPassword() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resetPassword");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/resetPasswordForm", method = RequestMethod.GET)
    public ModelAndView resetPassword(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        User tempUser = new User();

        List<User> userList = userService.getAllUsers();
        for (User u : userList) {
            modelAndView.setViewName("redirect:/resetPassword");

            if (u.getEmail().equals(user.getEmail())) {
                modelAndView.setViewName("newPassword");
                tempUser = userService.getById(findUserIdByEmail(user.getEmail()));
                break;
            }
        }

        modelAndView.addObject("user", tempUser);
        return modelAndView;
    }

    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
    public ModelAndView setNewPassword(@ModelAttribute("user") User user) {
        User tempUser = userService.getById(findUserIdByEmail(user.getEmail()));
        tempUser.setPassword(user.getPassword());
        tempUser.setConfirmPassword(user.getConfirmPassword());
        userService.updateUser(tempUser);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    private int findUserIdByEmail(String email) {
        int userId = 0;

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                userId = user.getId();
            }
        }

        return userId;
    }
}
