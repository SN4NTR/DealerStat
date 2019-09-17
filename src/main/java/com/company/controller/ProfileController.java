package com.company.controller;

import com.company.model.Post;
import com.company.model.User;
import com.company.service.PostService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfileController {

    private UserService userService;
    private PostService postService;

    @Autowired
    public ProfileController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        int userId = findUserId(userService.getAllUsers());
        ModelAndView modelAndView = new ModelAndView();

        if ("guest".equals(userService.getById(userId).getEmail())) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/profile/" + userId);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();

        int currentUserId = findUserId(userService.getAllUsers());
        if (currentUserId != id && !"admin".equals(userService.getById(currentUserId).getEmail())) {
            modelAndView.setViewName("redirect:/profile/" + currentUserId);
        } else {
            User user = userService.getById(id);
            modelAndView.setViewName("user/profile/profile");
            modelAndView.addObject("user", user);
        }

        return modelAndView;
    }

    private int findUserId(List<User> users) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        int userId = 0;
        for (User u : users) {
            if (auth.getName().equals(u.getEmail())) {
                userId = u.getId();
                return userId;
            }
        }

        return userId;
    }

    @RequestMapping(value = "/profile/post/{id}", method = RequestMethod.GET)
    public ModelAndView profilePost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();

        int currentUserId = findUserId(userService.getAllUsers());
        List<Post> posts = postService.getAllPosts();
        for (Post p : posts) {
            modelAndView.setViewName("user/profile/post");

            if (p.getUser().getId() != currentUserId && !"admin".equals(userService.getById(currentUserId).getEmail())) {
                modelAndView.setViewName("redirect:/");
            }
        }

        modelAndView.addObject("post", postService.getById(id));
        return modelAndView;
    }
}
