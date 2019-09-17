package com.company.controller;

import com.company.model.Comment;
import com.company.model.Post;
import com.company.model.User;
import com.company.service.CommentService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CommentController {

    private CommentService commentService;
    private UserService userService;
    private static int userIdBuffer;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/addComment/{id}", method = RequestMethod.GET)
    public ModelAndView addComment(@PathVariable("id") int id) {
        userIdBuffer = id;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/comment/create");
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("comment") Comment comment) {
        User user = userService.getById(userIdBuffer);
        comment.setUser(user);
        commentService.addComment(comment);
        Set<Comment> comments = new HashSet<>();
        comments.add(comment);
        user.setComments(comments);

        int userId = user.getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/" + userId);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteComment/{id}", method = RequestMethod.GET)
    public ModelAndView deleteComment(@PathVariable("id") int id) {
        int userId = commentService.getById(id).getUser().getId();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/" + userId);
        commentService.deleteComment(commentService.getById(id));
        return modelAndView;
    }
}
