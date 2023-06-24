package dk.dtu.compute.se.pisd.roborally.model.Maps;

import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.view.SpaceView;
import org.jetbrains.annotations.NotNull;

public class Testing extends TileCreator {
    public Testing(@NotNull Space space) {super(space);}

    public static final String[][] Testing = {
//            0     1     2     3     4     5     6     7     8     9     10    11
            // Testing
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "0A", "1A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4C", "4A"},
            {"4A", "3A", "2A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4C", "4A"},
            {"4A", "0B", "1B", "4A", "4A", "4A", "4A", "4A", "4A", "0E", "3D", "4A"},
            {"4A", "3B", "2B", "4A", "4A", "4A", "4A", "4A", "4A", "1G", "4A", "4A"},
            {"4A", "4A", "4E", "4A", "4A", "4A", "4A", "4A", "4A", "2G", "4A", "4A"},
            {"5F", "4E", "5E", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "5F", "4F", "5E", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4F", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "1N", "2N", "3N", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},


            // used for resetting the testing map
            /*      ---     EMPTY MAP    ---...---...---...---...---...---...---...---...---...---
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
            {"4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A", "4A"},
             */

    };

    public static int lengthOfBoard = 12;
    public static int heightOfBoard = 12;

    public static int nrCheckPoints = 3;
    public static int getLengthOfBoard() { return lengthOfBoard; }
    public static int getHeightOfBoard() { return heightOfBoard; }

    public static void executeTesting(SpaceView spaceV, Space space){
        BoardCreatorBOA.CreateMap(Testing, lengthOfBoard, space, spaceV);
    }
}
