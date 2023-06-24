package dk.dtu.compute.se.pisd.roborally.model.Maps;

import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.view.SpaceView;
import dk.dtu.compute.se.pisd.roborally.view.ViewObserver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author UffeBC
 * BoardCreator places the pictures on each field by calling the TileCreator class
 */
public class BoardCreatorBOA extends SpaceView implements ViewObserver {



    // AppController line 65 - names for new maps
    // SpaceView - activation of the Game
    // AppController line 90 - EXTRA for different tile length deviating from 12

    //      This class is used to simplify the creation of boards
    public BoardCreatorBOA(@NotNull Space space) {super(space);}

    public static void CreateMap(String[][] array, int Length,Space space, SpaceView spaceV) {
        if (Length==3) {
            for (int a = 0; a < Length; a++) {
                for (int b = 0; b <12/*HEIGHT*/; b++) {
                    int locationGetX = Integer.parseInt(array[b][a].substring(0, 1));
                    int locationGetY = (int) array[b][a].charAt(1) - 65;


                    // String for testing
//                if (a==0 && b==0) {
//                    System.out.println(Integer.parseInt(array[b][a].substring(0,1)));
//                    System.out.println((int) array[b][a].charAt(1)-65);
//                }
                    if (space.x == (a) && space.y == (b))
                        TileCreator.Creator((locationGetX), (locationGetY), spaceV);
                }
            }
        }
        // UNDERNEATH 3 IS ADDED CAUSE OF THE START BOARD
        else{
            for (int a = 0; a < Length; a++) {              // These for loops go through all tiles on the board
                for (int b = 0; b < Length; b++) {
                    int locationGetX = Integer.parseInt(array[b][a].substring(0, 1));
                    int locationGetY = (int) array[b][a].charAt(1) - 65;
                    // String for testing
//                if (a==0 && b==0) {
//                    System.out.println(Integer.parseInt(array[b][a].substring(0,1)));
//                    System.out.println((int) array[b][a].charAt(1)-65);
//                }
                    if (space.x == (a+3) && space.y == (b))
                        TileCreator.Creator((locationGetX), (locationGetY), spaceV); // sets tile using creator method
                }
            }
        }

        // Example:
        // imported value 0B in a field
        // 0 determines the x value of the wanted tile from the (root) below
        // B determines the y value of the wanted tile from the (root) below

        // The (Root) = src/main/resources/board_tiles_with_gridlines.jpg


    }

}
