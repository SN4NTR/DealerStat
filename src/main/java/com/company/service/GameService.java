package com.company.service;

import com.company.model.Game;

import java.util.List;

public interface GameService {

    Game getById(int id);

    List<Game> getAllGames();
}
