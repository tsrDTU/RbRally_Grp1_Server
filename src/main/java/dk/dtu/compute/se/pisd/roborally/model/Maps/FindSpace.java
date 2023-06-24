package dk.dtu.compute.se.pisd.roborally.model.Maps;

import dk.dtu.compute.se.pisd.roborally.model.Core.Value;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.util.Objects;

/**
 * This Class has methods to find a players location based on which map is used.
 * @Author UffeBC
 */
public class FindSpace {
    public static String ofPlayer(Player player) {
        int x = player.getSpace().x;
        int y = player.getSpace().y;
        //System.out.printf("playerx:%s playery:%s\r\n",x,y);
        //System.out.println(Value.map);
        if (Objects.equals(Value.map, "GoldenStripe") && x>2)
            return GoldStripe.GoldStripeArray[y][x-3];
        else if (Objects.equals(Value.map, "RingOfDeath") && x>2)
            return RingOfDeath.RingOfDeath[y][x-3];
        else if (Objects.equals(Value.map, "WhirlWind") && x>2)
            return WhirlWind.WhirlWind[y][x-3];
        else if (Objects.equals(Value.map, "Testing") && x>2)
            return  Testing.Testing[y][x-3];
        else return StartBoard.StartBoard[y][x];
    }

}
