package dk.dtu.compute.se.pisd.roborally.model.Maps;

import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.view.SpaceView;
import org.jetbrains.annotations.NotNull;

/**
 * This is the class which controls the RingOfDeath Map using the array below
 * as well as some references to height, width and amount of checkpoints.
 * @Author UffeBC
 */
public class RingOfDeath extends TileCreator {
    private RingOfDeath(@NotNull Space space) { super(space); }

    public static final String[][] RingOfDeath = {
//        0     1     2     3     4     5     6     7     8     9     10    11
            // RingOfDeath
        {"4A", "1G", "6A", "4A", "4F", "1G", "4A", "4A", "4A", "4A", "0G", "4A"},
        {"2G", "5A", "5C", "5C", "5C", "4K", "5C", "5C", "5C", "3J", "5A", "2G"},
        {"4A", "2J", "3D", "5A", "5A", "5A", "1G", "5A", "5A", "2D", "4B", "6B"},
        {"4A", "4C", "5A", "4G", "4A", "4A", "0F", "4F", "4G", "5A", "4B", "4A"},
        {"4A", "4C", "5A", "5E", "4A", "4A", "4A", "4A", "3N", "5A", "4B", "4A"},
        {"4A", "4C", "3G", "1F", "4A", "6A", "4A", "4A", "4A", "5A", "4J", "4A"},
        {"3G", "5K", "5A", "4A", "4A", "4A", "6A", "4A", "0E", "2G", "4B", "4A"},
        {"4A", "4C", "5A", "4A", "2N", "4A", "4A", "4A", "5F", "5A", "4B", "4A"},
        {"4A", "4C", "5A", "4G", "4E", "1E", "4A", "4A", "4G", "5A", "4B", "4A"},
        {"6B", "4C", "3C", "5A", "5A", "0G", "5A", "5A", "5A", "2C", "0J", "4A"},
        {"3G", "5A", "1J", "5B", "5B", "5B", "5J", "5B", "5B", "5B", "5A", "3G"},
        {"4A", "0G", "4A", "1N", "4A", "4A", "0G", "4E", "4A", "6A", "0G", "4A"}

    };

    //    URL https://tartarus.org/gareth/roborally/images/Fortress.board.2005.png

    public static int lengthOfBoard = 12;
    public static int heightOfBoard = 12;

    public static int nrCheckPoints = 3;
    public static int getLengthOfBoard() { return lengthOfBoard; }
    public static int getHeightOfBoard() { return heightOfBoard; }

    public static void executeRingOfDeath(SpaceView spaceV, Space space){
        BoardCreatorBOA.CreateMap(RingOfDeath, lengthOfBoard, space, spaceV);
    }
}
