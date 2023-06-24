package dk.dtu.compute.se.pisd.roborally.fileaccess.model;

import dk.dtu.compute.se.pisd.roborally.model.CommandCard;
import dk.dtu.compute.se.pisd.roborally.model.CommandCardField;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;


import java.util.ArrayList;
import java.util.List;

/**
 * Template used for serialization and deserialization
 */
public class PlayerTemplate {

    public String name;
    public String color;

    public int checkToken;

    public int number;
    public CommandCard[] program;
    //public CommandCardField[] cards;

    public CommandCard[] cards;
    //public String[] cardStringArr;



    public Space space;
    public Heading heading;

    public int x, y;
}
