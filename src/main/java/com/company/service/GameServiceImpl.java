package com.company.service;

import com.company.dao.GameDao;
import com.company.model.Game;
import com.company.model.GameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameDao gameDao;
    private GameObjectService gameObjectService;

    public static int gameObjectIdBuffer;

    @Autowired
    public GameServiceImpl(GameDao gameDao, GameObjectService gameObjectService) {
        this.gameDao = gameDao;
        this.gameObjectService = gameObjectService;
    }

    @Override
    public Game getById(int id) {
        return gameDao.getById(id);
    }

    @Override
    public List<GameObject> getGameObjectsByGameId(int id) {
        return gameDao.getGameObjectsByGameId(id);
    }

    @Override
    public GameObject getGameObjectWithGame(Game game) {
        GameObject gameObject = gameObjectService.getById(gameObjectIdBuffer);
        gameObject.setGame(gameDao.getById(game.getId()));
        return gameObject;
    }
}
