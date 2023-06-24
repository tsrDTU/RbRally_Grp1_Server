package dk.dtu.compute.se.pisd.roborally.model.Maps;

import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.view.SpaceView;
import org.jetbrains.annotations.NotNull;

/**
 * This is the class which controls the GoldStripe Map using the array below
 * as well as some references to height, width and amount of checkpoints.
 * @Author UffeBC
 */
public class GoldStripe extends TileCreator {

    public GoldStripe(@NotNull Space space) { super(space); }

//    This class makes the first map "GoldStripe".
    public static final String[][] GoldStripeArray = {
//        0     1     2     3     4     5     6     7     8     9     10    11

            // GoldenStripe
        {"5B", "1J", "5B", "5B", "5H", "3F", "5A", "4A", "4A", "6K", "4A", "4C"},
        {"4A", "4A", "4A", "4A", "1G", "0E", "1E", "4A", "4A", "4A", "6B", "2J"},
        {"4A", "1N", "6L", "4A", "1G", "5A", "0G", "4A", "4L", "2N", "4A", "4C"},
        {"4A", "4A", "4A", "4A", "1G", "4E", "0G", "4A", "4A", "4F", "4A", "4C"},
        {"4A", "4A", "4F", "4A", "1G", "3G", "0H", "4A", "4A", "4A", "4A", "4C"},
        {"4A", "4F", "4E", "6A", "2H", "2G", "0I", "5C", "5C", "5C", "5C", "4K"},
        {"5J", "5B", "5B", "5B", "2I", "3G", "0H", "4A", "5B", "3C", "4A", "2E"},
        {"4B", "4A", "4A", "4A", "1G", "2G", "0G", "4A", "4A", "4C", "4A", "0G"},
        {"4B", "4A", "4A", "5E", "1G", "6A", "0G", "4A", "4A", "5A", "4A", "0I"},
        {"4B", "4A", "4A", "4A", "1G", "4A", "0G", "4A", "3A", "6K", "1A", "0G"},
        {"0J", "6B", "4A", "4A", "1G", "3A", "0G", "4A", "4A", "6A", "3N", "0G"},
        {"4B", "4A", "4A", "4A", "0F", "3E", "0I", "5C", "5C", "5C", "3J", "5C"},
    };

    //    URL https://tartarus.org/gareth/roborally/images/Cannery_Row.board.2005.png

    public static int lengthOfBoard = 12;
    public static int heightOfBoard = 12;

    public static int nrCheckPoints = 3;
    public static int getLengthOfBoard() { return lengthOfBoard; }
    public static int getHeightOfBoard() { return heightOfBoard; }

    public static void executeGoldStripe(SpaceView spaceV, Space space){
        BoardCreatorBOA.CreateMap(GoldStripeArray, lengthOfBoard, space, spaceV);
    }

//    public static void CreateMap(String[][] array, int Length, Space space, SpaceView spaceV) {
//        CreateMap(GoldStripeArray, Length, space, spaceV); // creates the wanted map
//    }
    
}
