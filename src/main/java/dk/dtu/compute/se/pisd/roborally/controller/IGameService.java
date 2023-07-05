package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.fileaccess.Game;

import java.util.List;

public interface IGameService {

    List<Game> findAll();
    public Game getGameById(int id);
    int addGame(Game p);
    public boolean updateGame(int id, Game p);
    public boolean deleteGameById(int id);
}
