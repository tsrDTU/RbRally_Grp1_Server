package dk.dtu.compute.se.pisd.roborally.fileaccess;
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


import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.PlayerTemplate;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.SpaceTemplate;

import dk.dtu.compute.se.pisd.roborally.controller.FieldAction;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.ValueTemplate;
import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.model.Core.*;

import javafx.scene.control.*;
import org.springframework.context.annotation.Configuration;


import java.io.*;
import java.util.Optional;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 * Modified by Torben Rasmussen
 */
//@Configuration
public class LoadBoard
{
    private static final String BOARDSFOLDER = "boards";

    private static final String DEFAULTBOARD = "defaultboard";
    private static final String JSON_EXT = "json";

    /**
     * Loads a json file containing a board. The data are deserialized and a board is returned
     * @param boardname: Name of the json file to be deserialized.
     * @return a Board
     */

    public static Board loadBoard(String boardname)
    {
        if (boardname == null) {
            boardname = DEFAULTBOARD;
        }



        ClassLoader classLoader = LoadBoard.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(BOARDSFOLDER + "/" + boardname + "." + JSON_EXT);

        if (inputStream == null) {
            // TODO these constants should be defined somewhere
            return new Board(8,8);
        }

        // In simple cases, we can create a Gson object with new Gson():
        GsonBuilder simpleBuilder = new GsonBuilder().
                registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>());
        Gson gson = simpleBuilder.create();

        Board result;
        FileReader fileReader = null;
        JsonReader reader = null;
        try {
            System.out.println("LoadBoard");

            reader = gson.newJsonReader(new InputStreamReader(inputStream));

            BoardTemplate template = gson.fromJson(reader, BoardTemplate.class);
            result=createBoardFromTemplate(template);


            reader.close();
            return result;
        } catch (IOException e1) {
            if (reader != null) {
                try {
                    reader.close();
                    inputStream = null;
                } catch (IOException e2) {}
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {}
            }
        }

        return null;
    }
    @JsonCreator
    public static Board createBoardFromTemplate(BoardTemplate template)
    {
        Board result;

        result = new Board(template.width, template.height);
        for (SpaceTemplate spaceTemplate: template.spaces) {
            Space space = result.getSpace(spaceTemplate.x, spaceTemplate.y);
            System.out.println("x: "+space.x+" y: "+space.y);
            if (space != null) {
                space.getActions().addAll(spaceTemplate.actions);
                space.getWalls().addAll(spaceTemplate.walls);
            }
        }
        for (PlayerTemplate playerTemplate: template.players)
        {

            Player player=new Player(result,playerTemplate.color,playerTemplate.name);
            System.out.println(player.getName());
            result.addPlayer(player);
            player.setSpace(result.getSpace(playerTemplate.x, playerTemplate.y));
            player.setHeading(playerTemplate.heading);
            player.setTokenVal(playerTemplate.checkToken);

            for (int j = 0; j < playerTemplate.cards.length; j++) {
                player.getCardField(j).setCard(playerTemplate.cards[j]);
            }
            for (int j = 0; j < playerTemplate.program.length; j++) {
                player.getProgramField(j).setCard(playerTemplate.program[j]);
            }

        }


        Value.map=template.val.map;
        Value.amountOfPlayers=template.val.amountOfPlayers;
        Value.MovePlayer= template.val.MovePlayer;
        Value.selectedPLayer=template.val.selectedPLayer;
        Value.clickCounter= template.val.clickCounter;

        result.setCurrentPlayer(result.getPlayer(template.val.selectedPLayer));
        System.out.println("Current Player loaded: "+result.getCurrentPlayer().getName());

        return result;
    }




    /**
     * Loads a json file containing a board. The data are deserialized and inserted in the existing board
     * @param board pointer to the existing Board
     * @param boardname: Name of the file to loaded
     */

    public static void insertInBoard(Board board, String boardname)
    {




        ClassLoader classLoader = LoadBoard.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(BOARDSFOLDER + "/" + boardname + "." + JSON_EXT);
        //      InputStream inputStream = classLoader.getResourceAsStream(BOARDSFOLDER + "/" + flToLoad[0]);
        /*
        if (inputStream == null) {
            // TODO these constants should be defined somewhere
            return new Board(8,8);
        }

         */

        // In simple cases, we can create a Gson object with new Gson():
        GsonBuilder simpleBuilder = new GsonBuilder().
                registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>());
        Gson gson = simpleBuilder.create();

        FileReader fileReader = null;
        JsonReader reader = null;
        try {


            reader = gson.newJsonReader(new InputStreamReader(inputStream));

            BoardTemplate template = gson.fromJson(reader, BoardTemplate.class);



            for (PlayerTemplate playerTemplate: template.players)
            {



                board.getPlayer(playerTemplate.number).setSpace(board.getSpace(playerTemplate.x, playerTemplate.y));
                board.getPlayer(playerTemplate.number).setHeading(playerTemplate.heading);
                board.getPlayer(playerTemplate.number).setTokenVal(playerTemplate.checkToken);



                for (int j = 0; j < playerTemplate.cards.length; j++) {
                    board.getPlayer(playerTemplate.number).getCardField(j).setCard(playerTemplate.cards[j]);

                }
                for (int j = 0; j < playerTemplate.program.length; j++) {
                    board.getPlayer(playerTemplate.number).getProgramField(j).setCard(playerTemplate.program[j]);

                }




            }



            board.setCurrentPlayer(board.getPlayer(template.val.selectedPLayer));
            System.out.println("Current Player loaded: "+board.getCurrentPlayer().getName());

            reader.close();
  //          return result;
        } catch (IOException e1) {
            if (reader != null) {
                try {
                    reader.close();
                    inputStream = null;
                } catch (IOException e2) {}
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {}
            }
        }


    }

    /**
     * Loads a json file containing a board. The data are deserialized for a specific player
     * and inserted in the existing board.
     * @param board: existing board
     * @param boardname: json file to be imported
     * @param player: The player which card is updated.
     */

    public static void loadCardAndProg(Board board, String boardname, Player player)
    {
        if (boardname == null) {
            boardname = DEFAULTBOARD;
        }

        System.out.println("loadCardAndProg");


        ClassLoader classLoader = LoadBoard.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(BOARDSFOLDER + "/" + boardname + "." + JSON_EXT);
        if (inputStream == null) {
            // TODO these constants should be defined somewhere
 //           return new Board(8,8);
        }

        // In simple cases, we can create a Gson object with new Gson():
        GsonBuilder simpleBuilder = new GsonBuilder().
                registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>());
        Gson gson = simpleBuilder.create();


        JsonReader reader = null;
        try {


            reader = gson.newJsonReader(new InputStreamReader(inputStream));

            BoardTemplate template = gson.fromJson(reader, BoardTemplate.class);

            for (PlayerTemplate playerTemplate: template.players)
            {



                if (playerTemplate.name.equals(player.getName()))
                {
                    for (int j = 0; j < playerTemplate.cards.length; j++) {
                        player.getCardField(j).setCard(playerTemplate.cards[j]);



                    }
                    for (int j = 0; j < playerTemplate.program.length; j++) {
                       player.getProgramField(j).setCard(playerTemplate.program[j]);

                    }
                }

            }
            reader.close();
 //           return result;
        } catch (IOException e1) {
            if (reader != null) {
                try {
                    reader.close();
                    inputStream = null;
                } catch (IOException e2) {}
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {}
            }
        }


    }

    /**
     * Serialize and save the existing board to a json file
     * @param board: Existing board
     * @param name: Name of the json file
     */
    public static void saveBoard(Board board, String name) {

        BoardTemplate template;


        ClassLoader classLoader = LoadBoard.class.getClassLoader();
        // TODO: this is not very defensive, and will result in a NullPointerException
        //       when the folder "resources" does not exist! But, it does not need
        //       the file "simpleCards.json" to exist!
        System.out.println(classLoader.getResource(BOARDSFOLDER).getPath() + "/" + name + "." + JSON_EXT);
        String filename =
                classLoader.getResource(BOARDSFOLDER).getPath() + "/" + name + "." + JSON_EXT;

        // In simple cases, we can create a Gson object with new:
        //
        //   Gson gson = new Gson();
        //
        // But, if you need to configure it, it is better to create it from
        // a builder (here, we want to configure the JSON serialisation with
        // a pretty printer):
        GsonBuilder simpleBuilder = new GsonBuilder().
                registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>()).
                setPrettyPrinting();
        Gson gson = simpleBuilder.create();

        FileWriter fileWriter = null;
        JsonWriter writer = null;

        template = fillInBoardTemplate (board);

        try {
            fileWriter = new FileWriter(filename);

            writer = gson.newJsonWriter(fileWriter);

            gson.toJson(template, template.getClass(), writer);

            writer.close();
        } catch (IOException e1) {
            if (writer != null) {
                try {
                    writer.close();
                    fileWriter = null;
                } catch (IOException e2) {}
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e2) {}
            }
        }


    }

    public static BoardTemplate fillInBoardTemplate(Board board)
    {
        BoardTemplate template = new BoardTemplate();
        template.width = board.width;
        template.height = board.height;




 //       System.out.println(name);
        for (int i=0; i<board.width; i++) {
            for (int j=0; j<board.height; j++) {
                Space space = board.getSpace(i,j);

                if (!space.getWalls().isEmpty() || !space.getActions().isEmpty())
                {
                    SpaceTemplate spaceTemplate = new SpaceTemplate();
                    spaceTemplate.x = space.x;
                    spaceTemplate.y = space.y;
                    spaceTemplate.actions.addAll(space.getActions());
                    spaceTemplate.walls.addAll(space.getWalls());
                    template.spaces.add(spaceTemplate);


                }
            }

        }
        template.numberOfPlayers=board.getPlayersNumber();

        for (int i = 0; i < board.getPlayersNumber(); i++)
        {

            Player player=board.getPlayer(i);
            PlayerTemplate playerTemplate=new PlayerTemplate();
            playerTemplate.heading=player.getHeading();
            playerTemplate.number=player.board.getPlayerNumber(player);
            playerTemplate.x=player.getSpace().x;
            playerTemplate.y=player.getSpace().y;
            playerTemplate.checkToken=player.getCheckToken();


            playerTemplate.cards = new CommandCard[player.getCards().length];

            if (player.getCards().length > 0)
            {
                for (int j = 0; j < player.getCards().length; j++)
                {
                    if (player.getCardField(j) != null) {
                        playerTemplate.cards[j]=player.getCardField(j).getCard();

                    }

                }
            }

            playerTemplate.program = new CommandCard[player.getProgram().length];



            if (player.getProgram().length > 0)
            {
                for (int j = 0; j < player.getProgram().length; j++)
                {
                    if (player.getProgramField(j) != null) {
                        playerTemplate.program[j]=player.getProgramField(j).getCard();

                    }

                }
            }


            //           playerTemplate.space=player.getSpace();
//            System.out.println(player.getSpace());
            playerTemplate.name=player.getName();
            playerTemplate.color=player.getColor();

            template.players.add(playerTemplate);



        }


        ValueTemplate valueTemplate=new ValueTemplate();

        valueTemplate.map=Value.map;
        valueTemplate.amountOfPlayers= Value.amountOfPlayers;
        valueTemplate.clickCounter=Value.clickCounter;
        valueTemplate.MovePlayer=Value.MovePlayer;
        valueTemplate.selectedPLayer=board.getNrOfCurrentPlayer();

        template.val=valueTemplate;

        return template;

    }




    /**
     * Dialog with the user to get the json file name
     * @return: json file name
     */
    public static String jsonFile()
    {
        File fl = new File("target/classes/boards/");


        String[] filesInDir=fl.list();


        ChoiceDialog<String> dialog = new ChoiceDialog<String>(filesInDir[1],filesInDir);
        dialog.setTitle("Load a game");
        dialog.setHeaderText("Select file to load");
        Optional<String> filernr = dialog.showAndWait();


        String[] st = filernr.toString().split("\\[");
        String[] stJs = st[1].split("]");
        String[] flJs=stJs[0].split("\\.");


        return flJs[0];

    }

    /**
     * Dialog with the user to get the json file name for the file to save
     * @return: json file name
     */
    public static String jsonFileToSave() {
        File fl = new File("target/classes/boards/");


        String[] filesInDir = fl.list();

        for (int f = 0; f < filesInDir.length; f++) {
            System.out.println(filesInDir[f]);

        }


        TextInputDialog dialog = new TextInputDialog("TestSave");
        dialog.setTitle("Save to file");
        dialog.setHeaderText("Enter file name: ");
        dialog.showAndWait();
        TextField s = dialog.getEditor();


        System.out.println("Dialog result: "+s.getCharacters());


        return String.valueOf(s.getCharacters());
    }


}
