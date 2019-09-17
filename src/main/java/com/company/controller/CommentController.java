package com.company.controller;

import com.company.model.Comment;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @RequestMapping(value = "/comment/add/{id}", method = RequestMethod.GET)
    public ModelAndView addComment(@PathVariable("id") int id) {
        userIdBuffer = id;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/comment/create");
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "/comment/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteComment(@PathVariable("id") int id) {
        int userId = commentService.getById(id).getUser().getId();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/" + userId);
        commentService.deleteComment(commentService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView getAllComments() {
        List<Comment> allComments = commentService.getAllComments();
        List<Comment> notApprovedComments = new ArrayList<>();

        for (Comment c : allComments) {
            if (!c.isApproved()) {
                notApprovedComments.add(c);
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/comments");
        modelAndView.addObject("comments", notApprovedComments);
        return modelAndView;
    }

    @RequestMapping(value = "/comment/approve/{id}", method = RequestMethod.GET)
    public ModelAndView approveComment(@PathVariable("id") int id) {
        Comment comment = commentService.getById(id);
        comment.setApproved(true);
        commentService.updateComment(comment);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/comments");
        return modelAndView;
    }

    @RequestMapping(value = "/comment/decline/{id}", method = RequestMethod.GET)
    public ModelAndView declineComment(@PathVariable("id") int id) {
        commentService.deleteComment(commentService.getById(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/comments");
        return modelAndView;
    }
}
