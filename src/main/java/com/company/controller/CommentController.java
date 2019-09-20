package com.company.controller;

import com.company.model.Comment;
import com.company.service.CommentService;
import com.company.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView getAllComments() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/comments");
        modelAndView.addObject("comments", commentService.getNotApprovedCommentList());
        return modelAndView;
    }

    @RequestMapping(value = "/comment/add/{id}", method = RequestMethod.GET)
    public ModelAndView addComment(@PathVariable("id") int id) {
        UserServiceImpl.userIdBuffer = id;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/comment/create");
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("comment") Comment comment) {
        commentService.saveComment(comment);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/" + UserServiceImpl.userIdBuffer);
        return modelAndView;
    }

    @RequestMapping(value = "/comment/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteComment(@PathVariable("id") int id) {
        int userId = commentService.getById(id).getUser().getId();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/" + userId);
        commentService.deleteComment(commentService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/comment/approve/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public ModelAndView approveComment(@PathVariable("id") int id) {
        commentService.updateComment(commentService.getById(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/comments");
        return modelAndView;
    }

    @RequestMapping(value = "/comment/decline/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public ModelAndView declineComment(@PathVariable("id") int id) {
        commentService.deleteComment(commentService.getById(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/comments");
        return modelAndView;
    }
}
