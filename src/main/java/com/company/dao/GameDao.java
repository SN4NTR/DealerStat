package com.company.dao;

import com.company.model.Game;

import java.util.List;

public interface GameDao {

    Game getById(int id);

    List<Game> getAllGames();
}
