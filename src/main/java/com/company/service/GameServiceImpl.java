package com.company.service;

import com.company.dao.GameDao;
import com.company.model.Game;
import com.company.model.GameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameDao gameDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public Game getById(int id) {
        return gameDao.getById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public List<GameObject> getGameObjectsByGameId(int id) {
        return gameDao.getGameObjectsByGameId(id);
    }
}
