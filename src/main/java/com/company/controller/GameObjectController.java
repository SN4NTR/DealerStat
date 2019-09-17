package com.company.controller;

import com.company.model.GameObject;
import com.company.model.Post;
import com.company.service.GameObjectService;
import com.company.service.PostService;
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
public class GameObjectController {

    private GameObjectService gameObjectService;
    private PostService postService;
    private static int postIdBuffer;

    @Autowired
    public GameObjectController(GameObjectService gameObjectService, PostService postService) {
        this.gameObjectService = gameObjectService;
        this.postService = postService;
    }

    public GameObjectController() {
    }

    @RequestMapping(value = "/addGameObject/{id}", method = RequestMethod.GET)
    public ModelAndView addGameObject(@PathVariable("id") int id) {
        postIdBuffer = id;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/gameObject/create");
        modelAndView.addObject("gameObject", new GameObject());
        return modelAndView;
    }

    @RequestMapping(value = "/addGameObject", method = RequestMethod.POST)
    public ModelAndView addGameObject(@ModelAttribute("gameObject") GameObject gameObject) {
        Post post = postService.getById(postIdBuffer);
        Set<Post> posts = new HashSet<>();
        posts.add(post);
        gameObject.setPosts(posts);
        gameObjectService.addGameObject(gameObject);
        Set<GameObject> gameObjects = new HashSet<>();
        gameObjects.add(gameObject);
        post.setGameObjects(gameObjects);

        ModelAndView modelAndView = new ModelAndView();
        GameController.setGameObjectIdBuffer(gameObject.getId());
        modelAndView.addObject("gameObject", gameObject);
        modelAndView.setViewName("post/game/add");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteGameObject/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGameObject(@PathVariable("id") int id) {
        int postId = findPostId(id);

        gameObjectService.deleteGameObject(gameObjectService.getById(id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" + postId);
        return modelAndView;
    }

    private int findPostId(int gameObjectId) {
        int postId = 0;
        List<Post> posts = postService.getAllPosts();

        for (Post p : posts) {
            Set<GameObject> gameObjects = p.getGameObjects();

            for (GameObject go : gameObjects) {
                if (go.getId() == gameObjectId) {
                    postId = p.getId();
                }
            }
        }

        return postId;
    }

    @RequestMapping(value = "/editGameObject/{id}", method = RequestMethod.GET)
    public ModelAndView editGameObject(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/gameObject/edit");
        modelAndView.addObject("gameObject", gameObjectService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/editGameObject", method = RequestMethod.POST)
    public ModelAndView editGameObject(@ModelAttribute("gameObject") GameObject gameObject) {
        int postId = findPostId(gameObject.getId());

        gameObject.setGame(gameObjectService.getById(gameObject.getId()).getGame());
        gameObject.setPosts(gameObjectService.getById(gameObject.getId()).getPosts());
        gameObjectService.updateGameObject(gameObject);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" + postId);
        return modelAndView;
    }
}
