package dk.dtu.compute.se.pisd.roborally.model.Maps;

import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.view.SpaceView;
import org.jetbrains.annotations.NotNull;

/**
 * This is the class which controls the WhirlWind Map using the array below
 * as well as some references to height, width and amount of checkpoints.
 * @Author UffeBC
 */
public class WhirlWind extends TileCreator {
    public WhirlWind(@NotNull Space space) { super(space); }

    public static final String[][] WhirlWind = {
//        0     1     2     3     4     5     6     7     8     9     10    11

         // WhirlWind
        {"6A","4A","2E","4G","3C","4G","4C","2E","3G","3G","3E","4A"},
        {"2E","3E","2F","3F","4C","0C","2K","1F","2E","5H","3F","4C"},
        {"0H","1G","5N","4A","0D","5G","4B","2E","4I","3F","4A","4C"},
        {"2F","3F","6A","5B","5B","5B","1D","2F","2G","2G","2G","4C"},
        {"4G","1C","1N","4A","5A","5A","5A","4A","4A","5A","4N","4C"},
        {"4C","4B","5A","4A","5A","3B","5L","4A","5G","5A","6B","4C"},
        {"4C","4B","5A","5G","4A","2B","6K","5A","4A","5A","4C","4C"},
        {"0D","4G","5A","4A","4A","5A","5A","5A","4A","4A","4C","4G"},
        {"4A","2E","3E","4A","2E","3E","6B","4A","2C","3C","4C","4A"},
        {"4C","2F","3I","1E","2F","3F","2E","3E","4B","5G","4C","3N"},
        {"0D","4G","0F","1F","0E","1E","2F","3F","4B","5F","4C","6A"},
        {"4A","2D","5C","4A","0F","1F","2N","4A","2D","5C","3D","4A"},

    };

    //     URL https://tartarus.org/gareth/roborally/images/Circuit_Maze.board.2005.png
    public static int lengthOfBoard = 12;
    public static int heightOfBoard = 12;

    public static int nrCheckPoints = 5;
    public static int getLengthOfBoard() { return lengthOfBoard; }
    public static int getHeightOfBoard() { return heightOfBoard; }

    public static void executeWhirlWind(SpaceView spaceV, Space space){
        BoardCreatorBOA.CreateMap(WhirlWind, lengthOfBoard, space, spaceV);
    }
}
