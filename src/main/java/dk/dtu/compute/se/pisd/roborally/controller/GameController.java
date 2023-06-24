/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.fileaccess.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.model.Core.Value;
import dk.dtu.compute.se.pisd.roborally.model.Maps.FindSpace;
import dk.dtu.compute.se.pisd.roborally.model.actions.ActionHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import java.lang.*;


/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class GameController {
    final public AppController appController;
    final public Board board;
//    private RESTController flicntr;
//    private WebClient webController;


    public GameController(@NotNull Board board, @NotNull AppController appController) {
        this.board = board;
        this.appController = appController;

 //       flicntr=new RESTController();
 //       System.out.println("FileUploadController started");

   //     webController=new WebClient();
    }

    /**
     * This is just some dummy controller operation to make a simple move to see something
     * happening on the board. This method should eventually be deleted!
     *
     * @param space the space to which the current player should move
     */
    public void moveCurrentPlayerToSpace(@NotNull Space space)  {
        // TODO Assignment V1: method should be implemented by the students:
        //   - the current player should be moved to the given space
        //     (if it is free()
        //   - and the current player should be set to the player
        //     following the current player
        //   - the counter of moves in the game should be increased by one
        //     if the player is moved

        if (space != null && space.board == board) {
            Player currentPlayer = board.getCurrentPlayer();
            if (currentPlayer != null && space.getPlayer() == null)
            {
                currentPlayer.setSpace(space);
                if (appController.role != AppController.Roles.WEBPLAYER)
                {
                    int playerNumber = (board.getPlayerNumber(currentPlayer) + 1) % board.getPlayersNumber();
                    board.setCurrentPlayer(board.getPlayer(playerNumber));
                }
            }
        }


    }

    // XXX: V2
    public void startProgrammingPhase()
    {
        if (appController.role== AppController.Roles.WEBPLAYER)
        {
            /*
 //           flicntr.downloadFile(appController.webCon.hostIp);

            board.setPhase(Phase.PROGRAMMING);
 //           board.setCurrentPlayer(board.getPlayer(appController.webCon.getPlayerNr()));
            board.setStep(0);

 //           Player player = board.getPlayer(appController.webCon.getPlayerNr());
            if (player != null) {
                for (int j = 0; j < Player.NO_REGISTERS; j++) {
                    CommandCardField field = player.getProgramField(j);
                    field.setCard(null);
                    field.setVisible(true);
                }
                for (int j = 0; j < Player.NO_CARDS; j++) {
                    CommandCardField field = player.getCardField(j);
                    field.setCard(generateRandomCommandCard());
                    field.setVisible(true);
                }
            }


             */
        }
        else {
            board.setPhase(Phase.PROGRAMMING);
            board.setCurrentPlayer(board.getPlayer(0));
            board.setStep(0);

            for (int i = 0; i < board.getPlayersNumber(); i++) {
                Player player = board.getPlayer(i);
                if (player != null) {
                    for (int j = 0; j < Player.NO_REGISTERS; j++) {
                        CommandCardField field = player.getProgramField(j);
                        field.setCard(null);
                        field.setVisible(true);
                    }
                    for (int j = 0; j < Player.NO_CARDS; j++) {
                        CommandCardField field = player.getCardField(j);
                        field.setCard(generateRandomCommandCard());
                        field.setVisible(true);
                    }
                }
            }
        }

        /**
         * Modified by Torben Rasmussen
         */

        LoadBoard.saveBoard(board,"Share");
  //      flicntr.uploadFile();
  //      webController.addGameToWeb(board);
        if (appController.role == AppController.Roles.HOST)
        {


            for (int i = 1; i < board.getPlayersNumber(); i++)
            {
                // Get the cards entered by the Web players
  //              flicntr.downloadFile(appController.webHost.getIpWebPlyayer(i-1));
 //               LoadBoard.loadCardAndProg(board,"ShareIn", board.getPlayer(i));

            }


        }
    }

    // XXX: V2
    private CommandCard generateRandomCommandCard() {
        Command[] commands = Command.values();
        int random = (int) (Math.random() * commands.length);
        return new CommandCard(commands[random]);
    }

    // XXX: V2
    public void finishProgrammingPhase() {
        makeProgramFieldsInvisible();
        makeProgramFieldsVisible(0);
        board.setPhase(Phase.ACTIVATION);
        // the Web player only uses 1 current player
        if (appController.role== AppController.Roles.LOCAL || appController.role== AppController.Roles.HOST)
           board.setCurrentPlayer(board.getPlayer(0));
 //       else board.setCurrentPlayer(board.getPlayer(appController.webCon.getPlayerNr()));
        board.setStep(0);
    }

    // XXX: V2
    private void makeProgramFieldsVisible(int register) {
        if (register >= 0 && register < Player.NO_REGISTERS )
        {
            if (appController.role== AppController.Roles.LOCAL || appController.role== AppController.Roles.HOST)
            {
                for (int i = 0; i < board.getPlayersNumber(); i++) {
                    Player player = board.getPlayer(i);
                    CommandCardField field = player.getProgramField(register);
                    field.setVisible(true);
                }
            }
            else
            {
     //           Player player = board.getPlayer(appController.webCon.getPlayerNr());
    //            CommandCardField field = player.getProgramField(register);
      //          field.setVisible(true);
            }
        }
    }

    // XXX: V2
    private void makeProgramFieldsInvisible() {
        if (appController.role== AppController.Roles.LOCAL || appController.role== AppController.Roles.HOST)
        {
        for (int i = 0; i < board.getPlayersNumber(); i++)
        {
            Player player = board.getPlayer(i);
            for (int j = 0; j < Player.NO_REGISTERS; j++)
            {
                CommandCardField field = player.getProgramField(j);
                field.setVisible(false);
            }
        }
        }
        else
        {
     //       Player player = board.getPlayer(appController.webCon.getPlayerNr());
            for (int j = 0; j < Player.NO_REGISTERS; j++)
            {
        //        CommandCardField field = player.getProgramField(j);
         //       field.setVisible(false);
            }


        }
    }

    // XXX: V2
    public void executePrograms() {
        board.setStepMode(false);

        if (appController.role == AppController.Roles.WEBPLAYER)
        {
            LoadBoard.saveBoard(board, "Share");

 //           flicntr.uploadFile();
        }

        if (appController.role == AppController.Roles.HOST)
        {

            for (int i = 1; i < board.getPlayersNumber(); i++)
            {
                // Get the cards entered by the Web players
  //              flicntr.downloadFile(appController.webHost.getIpWebPlyayer(i-1));
                LoadBoard.loadCardAndProg(board,"ShareIn", board.getPlayer(i));

            }


        }



        continuePrograms();
    }

    // XXX: V2
    public void executeStep() {
        board.setStepMode(true);
        continuePrograms();
    }

    // XXX: V2
    private void continuePrograms() {
        do {
            executeNextStep();
        } while (board.getPhase() == Phase.ACTIVATION && !board.isStepMode());
        if(board.getPhase() == Phase.GAME_WON){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME Finished");
            alert.setContentText(board.getCurrentPlayer().getName() + " has won the game");
            Optional<ButtonType> result = alert.showAndWait();
            appController.stopGame();
        }

    }


    // XXX: V2
    private void executeNextStep() {
        Player currentPlayer = board.getCurrentPlayer();
        if (board.getPhase() == Phase.ACTIVATION && currentPlayer != null) {
            int step = board.getStep();
            if (step >= 0 && step < Player.NO_REGISTERS) {
                CommandCard card = currentPlayer.getProgramField(step).getCard();
                if (card != null) {
                    Command command = card.command;
                    executeCommand(currentPlayer, command);
                }
                int nextPlayerNumber = board.getPlayerNumber(currentPlayer) + 1;
                //
                try {
                    if (card.command == Command.OPTION_LEFT_RIGHT || card.command == Command.OPTION_FWD_FAST_FORWARD)
                        nextPlayerNumber-=1;
                }
                catch (Exception ignored) {}
                //
                //
                ActionHandler.exePushPanel(FindSpace.ofPlayer(board.getCurrentPlayer()), currentPlayer, step);
                //
                if (appController.role!= AppController.Roles.WEBPLAYER) {
                    if (nextPlayerNumber < board.getPlayersNumber()) {
                        board.setCurrentPlayer(board.getPlayer(nextPlayerNumber));
                    } else {
                        step++;

                        if (step < Player.NO_REGISTERS) {
                            makeProgramFieldsVisible(step);
                            board.setStep(step);
                            board.setCurrentPlayer(board.getPlayer(0));

                        } else {

                            for (int i = 0; i < Value.amountOfPlayers; i++) {
                                ActionHandler.exeAction(FindSpace.ofPlayer(board.getPlayer(i)), board.getPlayer(i));
                                ActionHandler.exeGiveToken(FindSpace.ofPlayer(board.getPlayer(i)), board.getPlayer(i));
                                if (board.getPhase() == Phase.GAME_WON) {
                                    board.setCurrentPlayer(board.getPlayer(i));
                                    return;

                                }
                            }
                            startProgrammingPhase();
                        }
                    }//xx
                }
                else
                {
                    if (step < Player.NO_REGISTERS)
                    {
                        makeProgramFieldsVisible(step);
                        board.setStep(step);
      //                  board.setCurrentPlayer(board.getPlayer(appController.webCon.getPlayerNr()));

                    } else
                    {
                        startProgrammingPhase();
                    }

                }
            } else {
                // this should not happen
                assert false;
            }
        } else {
            // this should not happen // PLAYER_INTERACTION phase
            board.setPhase(Phase.ACTIVATION);
            int nextPlayerNumber = board.getPlayerNumber(currentPlayer)+1;
            int step = board.getStep();
            if (nextPlayerNumber < board.getPlayersNumber()) {
                board.setCurrentPlayer(board.getPlayer(nextPlayerNumber));
            } else {
                step++;
                if (step < Player.NO_REGISTERS) {
                    makeProgramFieldsVisible(step);
                    board.setStep(step);
                    board.setCurrentPlayer(board.getPlayer(0));
                } else {
                    startProgrammingPhase();
                }
            }
//            assert false;
        }
    }

    // XXX: V2
    private void executeCommand(@NotNull Player player, Command command) {
        if (player != null && player.board == board && command != null) {
            // XXX This is a very simplistic way of dealing with some basic cards and
            //     their execution. This should eventually be done in a more elegant way
            //     (this concerns the way cards are modelled as well as the way they are executed).

            switch (command) {
                case FORWARD:
                    this.moveForward(player);
                    break;
                case RIGHT:
                    this.turnRight(player);
                    break;
                case LEFT:
                    this.turnLeft(player);
                    break;
                case FAST_FORWARD:
                    this.fastForward(player);
                    break;
                case OPTION_LEFT_RIGHT:
                case OPTION_FWD_FAST_FORWARD:
                    this.optionCard(player);
                    break;
                default:
                    // DO NOTHING (for now)
            }
        }
    }

    // TODO: V2

    /**
     * Moves the player 1 forward
     * @Author UffeBC
     */
    public void moveForward(@NotNull Player player) {
        Space space = player.getSpace();
        if (player != null && player.board == board && space != null) {
            Heading heading = player.getHeading();
            Space target = board.getNeighbour(space, heading);
            String oldSpace = FindSpace.ofPlayer(player);
            if (target != null) {
                hitPlayer(space, heading);
                target.setPlayer(player);
                ActionHandler.exeWall(player, oldSpace, space);
            }
            ActionHandler.exWall(player, FindSpace.ofPlayer(player), space);
        }
        if (player.board.getPhase()==Phase.PLAYER_INTERACTION){
            executeStep();
        }
    }

    // TODO: V2
    /**
     * Moves the player 1 forward twice
     * @Author UffeBC
     */
    public void fastForward(@NotNull Player player) {
        moveForward(player);
        moveForward(player);
    }

    // TODO: V2

    /**
     * changes the players heading, (turning left)
     * @Author UffeBC
     */
    public void turnRight(@NotNull Player player) {
        if (player != null && player.board == board) {
            player.setHeading(player.getHeading().next());
        }
        if (player.board.getPhase()==Phase.PLAYER_INTERACTION){
            executeStep();;}
    }

    // TODO: V2

    /**
     * changes the players heading, (turning left)
     * @Author UffeBC
     */
    public void turnLeft(@NotNull Player player) {
        if (player != null && player.board == board) {
            player.setHeading(player.getHeading().prev());
        }
        if (player.board.getPhase()==Phase.PLAYER_INTERACTION){
            executeStep();}
    }

    public boolean moveCards(@NotNull CommandCardField source, @NotNull CommandCardField target) {
        CommandCard sourceCard = source.getCard();
        CommandCard targetCard = target.getCard();
        if (sourceCard != null && targetCard == null) {
            target.setCard(sourceCard);
            source.setCard(null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method takes a player, with the heading and checks the neighboring space
     * to see if a player is there and then moves them one space forward in the first
     * players heading. called recursive if there are more than two players after each other
     * with moveForward command.
     * @author Anders Jensen
     * @param space the space the player is occupying.
     * @param heading the heading which the player is moving
     */
    public void hitPlayer(Space space, Heading heading){
        Space target = board.getNeighbour(space, heading);
        if (target.getPlayer() != null ){
          Player  move = target.getPlayer();
          Heading headMove = move.getHeading();
          move.setHeading(heading);
          moveForward(move);
          move.setHeading(headMove);
        }
    }

    /**
     * A method called when no corresponding controller operation is implemented yet. This
     * should eventually be removed.
     * @deprecated
     */
    public void notImplemented() {
        // XXX just for now to indicate that the actual method is not yet implemented
        assert false;
    }
    public void optionCard(Player player) {

//        board.setCurrentPlayer(player);
        board.setPhase(Phase.PLAYER_INTERACTION);
//        board.setPhase(Phase.ACTIVATION);

    }

    /**
     * Send boards to the web for the host to collect.
     * Author Torben Rasmussen
     */
    public void webPlayerSendProgram()//
    {
        LoadBoard.saveBoard(board,"Share");
//        flicntr.uploadFile();

    }

    /**
     * Get Web players board from the web and insert the cards sent into the existing board
     * Author Torben Rasmussen
     */
    public void webPlayerUpdateBoard()
    {
 //       flicntr.downloadFile(appController.webCon.hostIp);
        LoadBoard.insertInBoard(board, "ShareIn");

        startProgrammingPhase();

        board.setPhase(Phase.PROGRAMMING);
      //  board.setCurrentPlayer(board.getPlayer(appController.webCon.playerNr));
        board.setStep(0);
    }

}
