package dk.dtu.compute.se.pisd.roborally.model.Core;

import dk.dtu.compute.se.pisd.roborally.model.Player;

/**
 * This Class contains values referenced in multiple classes,
 * like map name and amount of players
 * @Author tsrDTU og UffeBC
 *
 */
public class Value {
    public static String map;

    public static int amountOfPlayers;

    // USED IN BOARD_VIEW
    public static boolean MovePlayer;
    public static int selectedPLayer;


    public static int clickCounter = 0;

    public int getAmountOfPlayers()
    {
        return amountOfPlayers;
    }
    public String getMap()
    {
        return map;
    }

    public boolean getMovePlayer()
    {
        return MovePlayer;
    }

    public int getSelectedPLayer()
    {
        return selectedPLayer;
    }
}
