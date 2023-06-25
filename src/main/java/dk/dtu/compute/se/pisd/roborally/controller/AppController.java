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

import dk.dtu.compute.se.pisd.designpatterns.observer.Observer;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

import dk.dtu.compute.se.pisd.roborally.RoboRally;

import dk.dtu.compute.se.pisd.roborally.fileaccess.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Core.Value;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import dk.dtu.compute.se.pisd.roborally.fileaccess.*;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 * Modified by Torben Rasmussen
 * Roles added.
 * Role LOCAL: when the web is not used
 * Role HOST: when the web is used and the user is the host
 * Role WEBPLAYER: when the web is used and the user is ta web player
 */
public class AppController implements Observer {

    final private List<Integer> PLAYER_NUMBER_OPTIONS = Arrays.asList(2, 3, 4, 5, 6);
    final private List<String> PLAYER_COLORS = Arrays.asList("red", "green", "blue", "orange", "grey", "magenta");

    public enum Roles {
        LOCAL, WEBPLAYER, HOST;
    }

    public Roles role=Roles.LOCAL;

    final private RoboRally roboRally;

 //   public WebPlayerController webCon;

 //   public WebHostController webHost;
    public RestControllerRb restControllerRb;

    private GameController gameController;

    public AppController(@NotNull RoboRally roboRally) {
        this.roboRally = roboRally;
    }

    public void newGame()
    {

        setUpGame();

        gameController.startProgrammingPhase();

        roboRally.createBoardView(gameController);

  //      restControllerRb=new RestControllerRb(gameController.board);

    }

    private void setUpGame()
    {
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(PLAYER_NUMBER_OPTIONS.get(0), PLAYER_NUMBER_OPTIONS);
        dialog.setTitle("Player number");
        dialog.setHeaderText("Select number of players");
        Optional<Integer> result = dialog.showAndWait();

        /**
         * This sets the global value of map to the selected String.
         * (used for finding out what space a player is on in FindSpace)
         * @Author UffeBC
         */
        ChoiceDialog<String> map = new ChoiceDialog<>("GoldenStripe", // default
                "GoldenStripe","RingOfDeath", "WhirlWind", "Testing");
        map.setTitle("Map");
        map.setHeaderText("Select map");
        Optional<String> mapresult = map.showAndWait();
        mapresult.ifPresent(s -> Value.map = s);

        mapresult.ifPresent(s -> Value.map = map.getSelectedItem());
        //

        if (result.isPresent()) {
            if (gameController != null) {
                // The UI should not allow this, but in case this happens anyway.
                // give the user the option to save the game or abort this operation!
                if (!stopGame()) {
                    return;
                }
            }

            int tileLength = 15; // Length including side board is +3
            int tileHeight = 12/*GoldStripe.getHeightOfBoard()*/;

            // XXX the board should eventually be created programmatically or loaded from a file
            //     here we just create an empty board with the required number of players.
            Board board = new Board(tileLength, tileHeight);
            gameController = new GameController(board, this);
            int no = result.get();

            //author Anders Jensen
            //Specific start positions for the robots. Goes from player 1 to the number of Players
            for (int i = 0; i < no; i++) {
                Player player = new Player(board, PLAYER_COLORS.get(i), "Player " + (i + 1));
                board.addPlayer(player);
                if (board.getPlayerNumber(player) == 0) {
                    player.setSpace(board.getSpace(0 % board.width, 0));
                } else if (board.getPlayerNumber(player) == 1) {
                    player.setSpace(board.getSpace(1 % board.width, 1));
                } else if (board.getPlayerNumber(player) == 2) {
                    player.setSpace(board.getSpace(0 % board.width, 4));
                } else if (board.getPlayerNumber(player) == 3) {
                    player.setSpace(board.getSpace(1 % board.width, 5));
                } else if (board.getPlayerNumber(player) == 4) {
                    player.setSpace(board.getSpace(1 % board.width, 6));
                } else if (board.getPlayerNumber(player) == 5) {
                    player.setSpace(board.getSpace(0 % board.width, 7));
                }
            }

            Value.amountOfPlayers = no;

        }
    }

    /**
     * Updated by Torben Rasmussen
     */
    public void saveGame()
    {

        String jsonFile=LoadBoard.jsonFileToSave();

        LoadBoard.saveBoard(gameController.board,jsonFile);

    }

    /**
     * Updated by Torben Rasmussen
     */
    public void loadGame() {


        int tileLength = 15; // Length including side board is +3
        int tileHeight = 12/*GoldStripe.getHeightOfBoard()*/;


        String jsonFile=LoadBoard.jsonFile();


        Board board= LoadBoard.loadBoard(jsonFile);


        gameController = new GameController(board,this);


        gameController.startProgrammingPhase();

        for (int i = 0; i < board.getPlayersNumber(); i++) {
            Player player = board.getPlayer(i);
            LoadBoard.loadCardAndProg(board, jsonFile, player);
        }

        roboRally.createBoardView(gameController);

    }

    /**
     * Used when joining a Web Game
     * @Author: torben Rasmussen
     */
    public void joinWebGame()
    {
  //      webCon = new WebPlayerController();
        role=Roles.WEBPLAYER;

        String jsonFile="ShareIn";


        Board board= LoadBoard.loadBoard(jsonFile);

        gameController = new GameController(board,this);


        gameController.startProgrammingPhase();

        for (int i = 0; i < board.getPlayersNumber(); i++) {
            Player player = board.getPlayer(i);
            LoadBoard.loadCardAndProg(board, jsonFile, player);
        }

        roboRally.createBoardView(gameController);


    }

    /**
     * Used when joining a Web Game
     * @Author: torben Rasmussen
     */
    public void hostWebGame()
    {

        setUpGame();

 //       webHost = new WebHostController(gameController.board);
        role=Roles.HOST;

        gameController.startProgrammingPhase();

        roboRally.createBoardView(gameController);

    }

    /**
     * Stop playing the current game, giving the user the option to save
     * the game or to cancel stopping the game. The method returns true
     * if the game was successfully stopped (with or without saving the
     * game); returns false, if the current game was not stopped. In case
     * there is no current game, false is returned.
     *
     * @return true if the current game was stopped, false otherwise
     */
    public boolean stopGame() {
        if (gameController != null) {

            // here we save the game (without asking the user).
            saveGame();

            gameController = null;
            roboRally.createBoardView(null);
            return true;
        }
        return false;
    }

    public void exit() {
        if (gameController != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit RoboRally?");
            alert.setContentText("Are you sure you want to exit RoboRally?");
            Optional<ButtonType> result = alert.showAndWait();

            if (!result.isPresent() || result.get() != ButtonType.OK) {
                return; // return without exiting the application
            }
        }

        // If the user did not cancel, the RoboRally application will exit
        // after the option to save the game
        if (gameController == null || stopGame()) {
            Platform.exit();
        }
    }



    public boolean isGameRunning() {
        return gameController != null;
    }


    @Override
    public void update(Subject subject) {
        // XXX do nothing for now
    }

}
