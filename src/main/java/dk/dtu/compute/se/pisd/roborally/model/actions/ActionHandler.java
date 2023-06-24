package dk.dtu.compute.se.pisd.roborally.model.actions;

import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.model.Core.Value;
import dk.dtu.compute.se.pisd.roborally.model.Maps.*;

import java.util.Objects;

/**
 * This class has methods for movements of conveyor-belts and push panels,
 * as well as checkpoint tokens required to win the game and walls.
 * @Author Anders and UffeBC
 */
public class ActionHandler {
    public static void exePushPanel(String space, Player player, int push) {

        if ((space.equals("0B")&&push%2==0)||(space.equals("0A")&&push%2!=0))
            player.setSpace(player.board.getSpace(player.getSpace().x,player.getSpace().y+1)); //down
        if ((space.equals("1B")&&push%2==0)||(space.equals("1A")&&push%2!=0))
            player.setSpace(player.board.getSpace(player.getSpace().x-1,player.getSpace().y)); //left
        if ((space.equals("2B")&&push%2==0)||(space.equals("2A")&&push%2!=0))
            player.setSpace(player.board.getSpace(player.getSpace().x,player.getSpace().y-1)); //up
        if ((space.equals("3B")&&push%2==0)||(space.equals("3A")&&push%2!=0))
            player.setSpace(player.board.getSpace(player.getSpace().x+1,player.getSpace().y)); //right

    }

    public enum actions {
        CONVEYOR, REPAIR, REBOOT, WALL, LASER, PIT

    }

    public static void exeConveyer(String space, Player player) {
        // CORNERS
        for (int j = 0; j < 4; j++) {
            if (space.equals("0"+(char)(67+j))||space.equals("1"+(char)(67+j)))
                player.setHeading(player.getHeading().prev()); //turn left
            if (space.equals("2"+(char)(67+j))||space.equals("3"+(char)(67+j)))
                player.setHeading(player.getHeading().next()); //turn right
        }

        // BLUE Junk
        if (space.equals("4B")||space.equals("0J")||space.equals("3K")||space.equals("4J")) {
            player.setSpace(player.board.getSpace(player.getSpace().x, player.getSpace().y-2)); // up
        }
        if (space.equals("4C")||space.equals("1K")||space.equals("2J")||space.equals("5K")){
            player.setSpace(player.board.getSpace(player.getSpace().x,player.getSpace().y+2)); // down
        }
        if (space.equals("5B")||space.equals("0K")||space.equals("1J")||space.equals("5J")){
            player.setSpace(player.board.getSpace(player.getSpace().x+2,player.getSpace().y)); // right
        }
        if (space.equals("5C")||space.equals("2K")||space.equals("3J")||space.equals("4K")){
            player.setSpace(player.board.getSpace(player.getSpace().x-2,player.getSpace().y)); // left
        }

        // Yellow Junk
        if (space.equals("0G")||space.equals("0H")||space.equals("0I")||space.equals("4I")||
                space.equals("1D")||space.equals("2D")||space.equals("1F")||space.equals("2F")) {
            player.setSpace(player.board.getSpace(player.getSpace().x, player.getSpace().y-1)); // up
        }
        if (space.equals("1G")||space.equals("2H")||space.equals("2I")||space.equals("5H")||
                space.equals("0C")||space.equals("3C")||space.equals("0E")||space.equals("3E")){
            player.setSpace(player.board.getSpace(player.getSpace().x,player.getSpace().y+1)); // down
        }
        if (space.equals("3G")||space.equals("1H")||space.equals("1I")||space.equals("4H")||
                space.equals("0D")||space.equals("2C")||space.equals("0F")||space.equals("2E")){
            player.setSpace(player.board.getSpace(player.getSpace().x+1,player.getSpace().y)); // right
        }
        if (space.equals("2G")||space.equals("3H")||space.equals("3I")||space.equals("5I")||
                space.equals("1C")||space.equals("3D")||space.equals("1E")||space.equals("3F")){
            player.setSpace(player.board.getSpace(player.getSpace().x-1,player.getSpace().y)); // left
        }



        // pit
        if (space.equals("5A"))
            for (int i = player.getSpace().y; i < 12; i++) {
                for (int j = player.getSpace().x; j < 15; j++) {
                    if (space.equals("6A")) {
                        player.setSpace(player.board.getSpace(j, i));
                        break;
                    }
                    if (space.equals("6A")) break;
                }

            }

    }

    public static void exePit(String space) {
        if (space.equals("5A"))
            System.out.println("HitPit");

    }

    /**
     * @deprecated
     */
    public static void exeReboot(Player currentplayer) {

//        currentplayer.setSpace();
    }


    public static void exeAction(String space, Player player) {
//        System.out.println(space);
        exeConveyer(space, player);
    }

    public static void pushPanel(String space, Player player, int push){
        exePushPanel(space, player, push);
    }

    private static int getNumberOfCheckPoints(){
        // Get the number of checkpoints for each of our 3 maps and returns it.
        if (Objects.equals(Value.map, "GoldenStripe"))
            return GoldStripe.nrCheckPoints;
        else if (Objects.equals(Value.map, "WhirlWind"))
            return WhirlWind.nrCheckPoints;
        else if (Objects.equals(Value.map, "RingOfDeath"))
            return RingOfDeath.nrCheckPoints;
        else if (Objects.equals(Value.map, "Testing"))
            return Testing.nrCheckPoints;
        return 0;
    }

    /**
     * Method that gives checkpoint Tokens then a robot lands on them, is called in the gameController at the
     * of each programming phase.
     * @author Anders Jensen
     * @param space The Space in the course array, which is defined as a String here to match the one we find
     *              in the arrays for each course
     * @param player The Player which lands on the checkpoint field.
     */
    public static void exeGiveToken(String space, Player player) {
        int token = player.getCheckToken() + 1;
        String nextCheckPoint = Integer.toString(token) +"N";

        if(space.equals(nextCheckPoint) ){
            player.setCheckToken();
            if(getNumberOfCheckPoints() == token){
                player.board.setPhase(Phase.GAME_WON);
            }
        }
    }


    /**
     * Checks if the space the player is on contains a wall in the heading the player are, if true, then sets the player
     * back to the spot he tried moving from. used in the moveForward method in gameController
     * @author Anders Jensen, Alexander Sønder
     * @param player The players turn to moveForward
     * @param space The Space in the course array, which is defined as a String here to match the one we find
     *      *              in the arrays for each course, is the same space as reSpace
     * @param reSpace The space the player was on before the move in the method moveForward
     */
    public static void exeWall(Player player, String space, Space reSpace){
        if(space.equals("6L") || space.equals("5F")){
            if(player.getHeading() == Heading.EAST){
                player.setSpace(reSpace);
            }
        }
        if(space.equals("4L") || space.equals("5E")){
            if(player.getHeading() == Heading.WEST){
                player.setSpace(reSpace);
            }
        }
        if(space.equals("5L") || space.equals("4F")){
            if(player.getHeading() == Heading.NORTH){
                player.setSpace(reSpace);
            }
        }
        if(space.equals("6K") || space.equals("4E")){
            if(player.getHeading() == Heading.SOUTH){
                player.setSpace(reSpace);
            }
        }
    }
    /**
     * Checks if the space you move to are the field which contains a wall, and if the heading you came in to the space
     * with are true, then sets the player back to the old space.
     * @author Anders Jensen, Alexander Sønder
     * @param player The players turn to moveForward
     * @param space The Space in the course array, which is defined as a String here to match the one we find
     *      *              in the arrays for each course, here it is the space the player is moved to.
     * @param reSpace The space the player was on before the move in the method moveForward
     */
    public static void exWall(Player player, String space, Space reSpace){
        if(space.equals("6L") || space.equals("5F")){
            if(player.getHeading() == Heading.WEST){
                player.setSpace(reSpace);
            }
        }
        if(space.equals("4L") || space.equals("5E")){
            if(player.getHeading() == Heading.EAST){
                player.setSpace(reSpace);
            }
        }
        if(space.equals("5L") || space.equals("4F")){
            if(player.getHeading() == Heading.SOUTH){
                player.setSpace(reSpace);
            }
        }
        if(space.equals("6K") || space.equals("4E")){
            if(player.getHeading() == Heading.NORTH){
                player.setSpace(reSpace);
            }
        }
    }

}