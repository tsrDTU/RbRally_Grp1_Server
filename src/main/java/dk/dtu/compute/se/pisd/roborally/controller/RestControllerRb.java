package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.fileaccess.Game;
import dk.dtu.compute.se.pisd.roborally.fileaccess.GameService;
import dk.dtu.compute.se.pisd.roborally.fileaccess.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.GameTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestControllerRb
{


    @Autowired
    private IGameService gameService;



    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> getGame()
    {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok().body(games);
    }

    @PostMapping("/games")
    public ResponseEntity<String > addGame(@RequestBody GameTemplate p) {
        System.out.println("Server RestControllerRb PostMapping");
        int added = gameService.addGame(LoadBoard.createGameFromTemplate(p));

        return  ResponseEntity.ok().body(String.valueOf(added));
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game p = gameService.getGameById(id);
        return ResponseEntity.ok().body(p);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<String> updateGame(@PathVariable int id, @RequestBody GameTemplate p)
    {
        System.out.println("Server Update Game");
        boolean added = gameService.updateGame(id, LoadBoard.createGameFromTemplate(p));
        return ResponseEntity.ok().body("Updated");
    }


/*
    @DeleteMapping("/games/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleted = gameService.deleteGamesById(id);
        return ResponseEntity.ok().body("deleted");
    }

 */


}
