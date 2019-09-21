package com.company.service;

import com.company.model.Game;
import com.company.model.GameObject;

import java.util.List;

public interface GameService {

    Game getById(int id);

    List<GameObject> getGameObjectsByGameId(int id);

    GameObject getGameObjectWithGame(Game game);
}
