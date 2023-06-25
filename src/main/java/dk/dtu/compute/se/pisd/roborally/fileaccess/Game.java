package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;

public class Game {
    private int id;
    private BoardTemplate board;


    public Game (int id, Board board)
    {
        this.id=id;
        this.board=LoadBoard.fillInBoardTemplate(board);
    }

    public int getId()
    {
        return id;
    }

    public BoardTemplate getBoard()
    {
        return board;
    }

}