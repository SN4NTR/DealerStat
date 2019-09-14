package com.company.controller;

import com.company.model.Game;
import com.company.model.GameObject;
import com.company.service.GameObjectService;
import com.company.service.GameService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    private GameService gameService;
    private GameObjectService gameObjectService;

    @Getter
    @Setter
    private static int gameObjectIdBuffer;

    @Autowired
    public GameController(GameService gameService, GameObjectService gameObjectService) {
        this.gameService = gameService;
        this.gameObjectService = gameObjectService;
    }

    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    public ModelAndView addGame(@ModelAttribute("game") Game game) {
        GameObject gameObject = gameObjectService.getById(gameObjectIdBuffer);
        gameObject.setPosts(gameObjectService.getById(gameObjectIdBuffer).getPosts());
        gameObject.setGame(gameService.getById(game.getId()));
        gameObjectService.updateGameObject(gameObject);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/posts");
        return modelAndView;
    }
}
