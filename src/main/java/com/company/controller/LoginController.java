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

@Controller
public class LoginController {

    private UserService userService;
    private UserValidator userValidator;

    @Autowired
    public LoginController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "activationCode", required = false) String activation,
                              @RequestParam(value = "resetPassword", required = false) String resetPassword) {
        ModelAndView modelAndView = new ModelAndView();
        String message = null;

        if (error != null) {
            message = "Username or Password is incorrect";
        }
        if (logout != null) {
            message = "Logged out successfully";
        }
        if ("true".equals(activation)) {
            message = "Submit your email";
        }
        if ("false".equals(activation)) {
            message = "Email is submitted";
        }
        if ("true".equals(resetPassword)) {
            message = "Submit resetting password on email";
        }
        if ("false".equals(resetPassword)) {
            message = "Password has been reset";
        }

        modelAndView.setViewName("login");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.PUT, RequestMethod.GET})
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

        userService.saveUser(user);

        modelAndView.setViewName("redirect:/login?activationCode=true");
        return modelAndView;
    }

    @RequestMapping(value = "/email/activate/{code}", method = {RequestMethod.PUT, RequestMethod.GET})
    public ModelAndView activateEmail(@PathVariable("code") String code) {
        userService.activateUser(code);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login?activationCode=false");
        return modelAndView;
    }

    @RequestMapping(value = "/password/reset/form", method = RequestMethod.GET)
    public ModelAndView resetPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resetPassword");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/password/reset", method = {RequestMethod.PUT, RequestMethod.GET})
    public ModelAndView resetPassword(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();

        User tempUser = userService.getById(userService.findUserIdByEmail(user.getEmail()));
        if (tempUser == null) {
            modelAndView.setViewName("redirect:/password/reset/form");
        } else {
            userService.resetPassword(tempUser);
            modelAndView.setViewName("redirect:/login?resetPassword=true");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/password/activate/{code}", method = RequestMethod.GET)
    public ModelAndView activatePassword(@PathVariable("code") String code) {
        User user = userService.getById(userService.findUserIdByCode(code));
        userService.activateUser(code);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("newPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/password/setNew", method = {RequestMethod.PUT, RequestMethod.POST})
    public ModelAndView setNewPassword(@ModelAttribute("user") User user) {
        User tempUser = userService.getById(userService.findUserIdByEmail(user.getEmail()));
        tempUser.setPassword(user.getPassword());
        tempUser.setConfirmPassword(user.getConfirmPassword());

        userService.updateUser(tempUser);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login?resetPassword=false");
        return modelAndView;
    }
}
