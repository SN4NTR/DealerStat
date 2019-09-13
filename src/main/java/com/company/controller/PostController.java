package com.company.controller;

import com.company.model.Post;
import com.company.model.User;
import com.company.service.PostService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PostController {

    private PostService postService;
    private UserService userService;
    private static int userIdBuffer;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
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
        modelAndView.setViewName("redirect:/posts");
        post.setUser(postService.getById(post.getId()).getUser());
        post.setGameObjects(postService.getById(post.getId()).getGameObjects());
        postService.updatePost(post);
        return modelAndView;
    }

    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.GET)
    public ModelAndView addPost(@PathVariable("id") int id) {
        userIdBuffer = id;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/create");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public ModelAndView addPost(@ModelAttribute("post") Post post) {
        User user = userService.getById(userIdBuffer);
        post.setUser(user);
        postService.addPost(post);
        Set<Post> posts = new HashSet<>();
        posts.add(post);
        user.setPosts(posts);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/posts");
        return modelAndView;
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.GET)
    public ModelAndView deletePost(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        postService.deletePost(postService.getById(id));
        modelAndView.setViewName("redirect:/posts");
        return modelAndView;
    }
}
