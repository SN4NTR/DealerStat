package com.company.service;

import com.company.model.GameObject;

public interface GameObjectService {

    GameObject getById(int id);

    void addGameObject(GameObject gameObject);

    void updateGameObject(GameObject gameObject);

    void deleteGameObject(GameObject gameObject);
}
