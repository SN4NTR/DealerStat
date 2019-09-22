package com.company.dao;

import com.company.model.Game;
import com.company.model.GameObject;

import java.util.List;

public interface GameDao {

    Game getById(int id);

    List<GameObject> getGameObjectsByGameId(int id);
}
