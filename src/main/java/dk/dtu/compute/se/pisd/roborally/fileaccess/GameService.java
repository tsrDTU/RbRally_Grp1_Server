package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;



public class GameService
{
    ArrayList<BoardTemplate> games = new ArrayList<BoardTemplate>();
    private List<dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate> BoardTemplate;

    public GameService()
    {

    }


    public List<BoardTemplate> findAll()
    {
        //returns a list of games
        return BoardTemplate;
    }


    public boolean addProduct(BoardTemplate p) {
        games.add(p);
        return true;
    }


    public BoardTemplate getProductById(int id) {
        for(BoardTemplate p : games) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public boolean updateProduct(int id, BoardTemplate p) {
        for(BoardTemplate currProd : games) {
            if(currProd.getId() == id) {
                currProd.setId(p.getId());
                currProd.setPname(p.getPname());
                currProd.setPrice(p.getPrice());
                return true;
            }
        }
        return false;
    }


    public boolean deleteProductById(int id) {
        ArrayList<BoardTemplate> newProducts = new ArrayList<BoardTemplate>();
        int oldSize = games.size();
        games.forEach((product -> {
            if(product.getId() == id)
                newProducts.add(product);
        }));
        games = newProducts;
        return oldSize < games.size() ? true : false;
    }
}
