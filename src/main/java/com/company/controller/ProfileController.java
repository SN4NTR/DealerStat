package com.company.controller;

import com.company.model.Post;
import com.company.service.PostService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView modelAndView = new ModelAndView();

        int userId = userService.findCurrentUserIdByEmail();
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

        int currentUserId = userService.findCurrentUserIdByEmail();
        if (currentUserId != id && !"admin".equals(userService.getById(currentUserId).getEmail())) {
            modelAndView.setViewName("redirect:/profile/" + currentUserId);
        } else {
            modelAndView.setViewName("user/profile/profile");
            modelAndView.addObject("user", userService.getById(id));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profile/post/{id}", method = RequestMethod.GET)
    public ModelAndView profilePost(@PathVariable("id") int id) {
        int currentUserId = userService.findCurrentUserIdByEmail();
        Post post = postService.getById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (post.getUser().getId() != currentUserId && !"admin".equals(userService.getById(currentUserId).getEmail())) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("user/profile/post");
            modelAndView.addObject("post", post);
        }

        return modelAndView;
    }
}
