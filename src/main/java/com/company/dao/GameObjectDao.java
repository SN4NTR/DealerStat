package com.company.dao;

import com.company.model.GameObject;

public interface GameObjectDao {

    GameObject getById(int id);

    void addGameObject(GameObject gameObject);

    void updateGameObject(GameObject gameObject);

    void deleteGameObject(GameObject gameObject);
}
