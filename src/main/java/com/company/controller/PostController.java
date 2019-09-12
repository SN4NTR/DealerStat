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

import java.util.List;

@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getAllPosts() {
        List<Post> postList = postService.getAllPosts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/posts");
        modelAndView.addObject("postList", postList);
        return modelAndView;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView getPost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/post");
        modelAndView.addObject("post", postService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.GET)
    public ModelAndView deletePost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        postService.deletePost(postService.getById(id));
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public ModelAndView editPost() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/edit");
        modelAndView.addObject("post", postService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute("post") Post post) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:post/posts");
        postService.updatePost(post);
        return modelAndView;
    }
}
