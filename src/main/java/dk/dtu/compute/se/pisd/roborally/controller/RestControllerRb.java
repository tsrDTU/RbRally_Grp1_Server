package dk.dtu.compute.se.pisd.roborally.controller;

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
    public ResponseEntity<List<BoardTemplate>> getProduct()
    {
        List<BoardTemplate> products = gameService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/games")
    public ResponseEntity<String > addProduct(@RequestBody BoardTemplate p) {
        boolean added = gameService.addProduct(p);
        if(added)
            return ResponseEntity.ok().body("added");
        else
            return ResponseEntity.internalServerError().body("not added");

    }

    @GetMapping("/games/{id}")
    public ResponseEntity<BoardTemplate> getProductById(@PathVariable int id) {
        BoardTemplate p = gameService.getProductById(id);
        return ResponseEntity.ok().body(p);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody BoardTemplate p) {
        boolean added = gameService.updateProduct(id, p);
        return ResponseEntity.ok().body("updated");
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleted = gameService.deleteProductById(id);
        return ResponseEntity.ok().body("deleted");
    }


}
