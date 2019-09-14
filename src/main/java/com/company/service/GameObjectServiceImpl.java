package com.company.service;

import com.company.dao.GameObjectDao;
import com.company.model.GameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class GameObjectServiceImpl implements GameObjectService {

    private GameObjectDao gameObjectDao;

    @Autowired
    public GameObjectServiceImpl(GameObjectDao gameObjectDao) {
        this.gameObjectDao = gameObjectDao;
    }

    @Override
    public GameObject getById(int id) {
        return gameObjectDao.getById(id);
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        gameObject.setCreatedAt(new Date(new java.util.Date().getTime()));
        gameObject.setUpdatedAt(gameObject.getCreatedAt());
        gameObject.setStatus(true);
        gameObjectDao.addGameObject(gameObject);
    }

    @Override
    public void updateGameObject(GameObject gameObject) {
        gameObject.setUpdatedAt(new Date(new java.util.Date().getTime()));
        gameObject.setStatus(true);
        gameObjectDao.updateGameObject(gameObject);
    }

    @Override
    public void deleteGameObject(GameObject gameObject) {
        gameObjectDao.deleteGameObject(gameObject);
    }
}
