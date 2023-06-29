package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Game {
    private int id;
    private BoardTemplate board;


    public Game (int id, Board board)
    {
        this.id=id;
        this.board=LoadBoard.fillInBoardTemplate(board);
        System.out.println("Game board"+this.board);
    }

    public int getId()
    {
        return id;
    }

    public BoardTemplate getBoard()
    {
        return board;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public void setBoard(BoardTemplate board)
    {
        this.board=board;
    }

}
