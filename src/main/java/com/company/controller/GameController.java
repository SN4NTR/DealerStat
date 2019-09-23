package com.company.controller;

import com.company.model.Game;
import com.company.service.GameObjectService;
import com.company.service.GameService;
import com.company.service.GameServiceImpl;
import com.company.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    private GameService gameService;
    private GameObjectService gameObjectService;
    private PostService postService;

    @Autowired
    public GameController(GameService gameService, GameObjectService gameObjectService, PostService postService) {
        this.gameService = gameService;
        this.gameObjectService = gameObjectService;
        this.postService = postService;
    }

    @RequestMapping(value = "/game/add", method = RequestMethod.POST)
    public ModelAndView addGame(@ModelAttribute("game") Game game) {
        gameObjectService.updateGameObject(gameService.getGameObjectWithGame(game));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" +
                postService.findPostIdByGameObjectId(GameServiceImpl.getGameObjectIdBuffer()));
        return modelAndView;
    }

    @RequestMapping(value = "/game/gameObjects/{id}", method = RequestMethod.GET)
    public ModelAndView showGameObjectsByGameId(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/gameObject/showByGameId");
        modelAndView.addObject("gameObjects", gameService.getGameObjectsByGameId(id));
        return modelAndView;
    }
}
