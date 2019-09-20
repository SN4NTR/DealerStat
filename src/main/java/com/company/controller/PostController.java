package com.company.controller;

import com.company.model.Post;
import com.company.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getAllPosts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/posts");
        modelAndView.addObject("postList", postService.getAllPosts());
        return modelAndView;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView getPost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/post");
        modelAndView.addObject("post", postService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/edit");
        modelAndView.addObject("post", postService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/post/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public ModelAndView editPost(@ModelAttribute("post") Post post) {
        postService.updatePost(post);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/" + post.getUser().getId());
        return modelAndView;
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.GET)
    public ModelAndView savePost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/create");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public ModelAndView savePost(@ModelAttribute("post") Post post) {
        postService.savePost(post);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" + post.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/post/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePost(@PathVariable("id") int id) {
        int userId = postService.getById(id).getUser().getId();
        postService.deletePost(postService.getById(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/" + userId);
        return modelAndView;
    }
}
