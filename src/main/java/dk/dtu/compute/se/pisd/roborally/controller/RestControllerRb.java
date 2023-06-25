package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.fileaccess.Game;
import dk.dtu.compute.se.pisd.roborally.fileaccess.GameService;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class RestControllerRb
{
    @Autowired
    GameService gameService;


    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> getProduct()
    {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok().body(games);
    }

    @PostMapping("/games")
    public ResponseEntity<String > addProduct(@RequestBody Game p) {
        boolean added = gameService.addGame(p);
        if(added)
            return ResponseEntity.ok().body("added");
        else
            return ResponseEntity.internalServerError().body("not added");

    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getProductById(@PathVariable int id) {
        Game p = gameService.getGameById(id);
        return ResponseEntity.ok().body(p);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Game p) {
        boolean added = gameService.updateGame(id, p);
        return ResponseEntity.ok().body("updated");
    }
/*
    @DeleteMapping("/games/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleted = gameService.deleteGamesById(id);
        return ResponseEntity.ok().body("deleted");
    }

 */


}
