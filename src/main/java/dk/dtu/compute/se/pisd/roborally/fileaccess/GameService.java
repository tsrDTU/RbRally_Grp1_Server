package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.controller.IGameService;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.springframework.stereotype.Service;


@Service
public  class GameService implements IGameService
{
    ArrayList<Game> games = new ArrayList<Game>();


    public GameService()
    {
        Board board;

        board= LoadBoard.loadBoard("ShareIn");
        games.add(new Game(1,board));
        System.out.println("GameService game"+games.get(0).getId());


        board= LoadBoard.loadBoard("WallsT");
        games.add(new Game(2,board));
        System.out.println("GameService game"+games.get(1).getId());


        board= LoadBoard.loadBoard("Collision");
        games.add(new Game(3,board));
        System.out.println("GameService game"+games.get(2).getId());


        board= LoadBoard.loadBoard("PushPanel");
        games.add(new Game(4,board));
        System.out.println("GameService game"+games.get(3).getId());



    }


@Override
    public List<Game> findAll()
    {
        //returns a list of games
        return games;
    }

@Override
    public int addGame(Game p) {
        System.out.println("Server addGame");
        games.add(p);
        return games.size();
    }

@Override
    public Game getGameById(int id) {
        for(Game p : games) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }

@Override
    public boolean updateGame(int id, Game p) {
    System.out.println("Server/GameService/updateGame startet");
        for(Game currProd : games) {
            if(currProd.getId() == id) {

                currProd.setId(p.getId());
                currProd.setBoard(p.getBoard());

                return true;
            }
        }
        return false;
    }

@Override
    public boolean deleteGameById(int id) {
        ArrayList<Game> newGames = new ArrayList<Game>();
        int oldSize = games.size();
        games.forEach((game -> {
            if(game.getId() == id)
                newGames.add(game);
        }));
        games = newGames;
        return oldSize < games.size() ? true : false;
    }
}
