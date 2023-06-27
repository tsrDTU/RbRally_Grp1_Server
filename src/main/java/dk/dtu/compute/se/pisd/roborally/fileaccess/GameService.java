package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;


public class GameService
{
    ArrayList<Game> games = new ArrayList<Game>();
 //   private List<dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate> BoardTemplate;

    public GameService()
    {
        Board board;

        board= LoadBoard.loadBoard("ShareIn");
        games.add(new Game(1,board));
        System.out.println("GameService game"+games.get(0).getId());


        board= LoadBoard.loadBoard("WallsT");
        games.add(new Game(2,board));
        System.out.println("GameService game"+games.get(0).getId());


    }


    public List<Game> findAll()
    {
        //returns a list of games
        return games;
    }


    public boolean addGame(Game p) {
        games.add(p);
        return true;
    }


    public Game getGameById(int id) {
        for(Game p : games) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public boolean updateGame(int id, Game p) {
        for(Game currProd : games) {
            if(currProd.getId() == id) {
                /*
                currProd.setId(p.getId());
                currProd.setPname(p.getPname());
                currProd.setPrice(p.getPrice());

                 */
                return true;
            }
        }
        return false;
    }


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
