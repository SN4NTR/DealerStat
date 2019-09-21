package com.company.controller;

import com.company.model.GameObject;
import com.company.service.GameObjectService;
import com.company.service.GameObjectServiceImpl;
import com.company.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameObjectController {

    private GameObjectService gameObjectService;
    private PostService postService;

    @Autowired
    public GameObjectController(GameObjectService gameObjectService, PostService postService) {
        this.gameObjectService = gameObjectService;
        this.postService = postService;
    }

    @RequestMapping(value = "/gameObject/add/{id}", method = RequestMethod.GET)
    public ModelAndView addGameObject(@PathVariable("id") int id) {
        GameObjectServiceImpl.postIdBuffer = id;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/gameObject/create");
        modelAndView.addObject("gameObject", new GameObject());
        return modelAndView;
    }

    @RequestMapping(value = "/gameObject/add", method = RequestMethod.POST)
    public ModelAndView addGameObject(@ModelAttribute("gameObject") GameObject gameObject) {
        gameObjectService.addGameObject(gameObject);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("gameObject", gameObject);
        modelAndView.setViewName("post/game/add");
        return modelAndView;
    }

    @RequestMapping(value = "/gameObject/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteGameObject(@PathVariable("id") int id) {
        int postId = postService.findPostIdByGameObjectId(id);
        gameObjectService.deleteGameObject(gameObjectService.getById(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" + postId);
        return modelAndView;
    }

    @RequestMapping(value = "/gameObject/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGameObject(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/gameObject/edit");
        modelAndView.addObject("gameObject", gameObjectService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/gameObject/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public ModelAndView editGameObject(@ModelAttribute("gameObject") GameObject gameObject) {
        int postId = postService.findPostIdByGameObjectId(gameObject.getId());
        gameObjectService.updateGameObject(gameObject);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" + postId);
        return modelAndView;
    }
}
