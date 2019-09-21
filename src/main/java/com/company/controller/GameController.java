package com.company.controller;

import com.company.model.Game;
import com.company.model.GameObject;
import com.company.model.Post;
import com.company.service.GameObjectService;
import com.company.service.GameService;
import com.company.service.GameServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class GameController {

    private GameService gameService;
    private GameObjectService gameObjectService;

    @Autowired
    public GameController(GameService gameService, GameObjectService gameObjectService) {
        this.gameService = gameService;
        this.gameObjectService = gameObjectService;
    }

    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    public ModelAndView addGame(@ModelAttribute("game") Game game) {
        GameObject gameObject = gameObjectService.getById(GameServiceImpl.gameObjectIdBuffer);
        gameObject.setGame(gameService.getById(game.getId()));
        gameObjectService.updateGameObject(gameObject);

        int postId = 0;
        Set<Post> posts = gameObject.getPosts();
        for (Post p : posts) {
            Set<GameObject> gameObjects = p.getGameObjects();

            for (GameObject go : gameObjects) {
                if (go.getId() == GameServiceImpl.gameObjectIdBuffer) {
                    postId = p.getId();
                    break;
                }
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile/post/" + postId);
        return modelAndView;
    }

    @RequestMapping(value = "/showGameObjectsByGameId/{id}", method = RequestMethod.GET)
    public ModelAndView showGameObjectsByGameId(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/gameObject/showByGameId");
        modelAndView.addObject("gameObjects", gameService.getGameObjectsByGameId(id));
        return modelAndView;
    }
}
