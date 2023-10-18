package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import edu.greenriver.sdev.saasproject.services.BoardGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardGameApi {
    private BoardGameService bgservice;

    public BoardGameApi(BoardGameService bgservice){
        this.bgservice = bgservice;
    }

    @GetMapping("boardgames")
    public ResponseEntity<List<BoardGame>> allBoardGames(){
        return new ResponseEntity<>(bgservice.getAllBoardGames(), HttpStatus.OK);
    }

    @GetMapping("boardgames/random")
    public ResponseEntity<BoardGame> random(){
        return new ResponseEntity<>(bgservice.random(), HttpStatus.OK);
    }

    @GetMapping("boardgames/{bgId}")
    public ResponseEntity<BoardGame> getBoardGameById(@PathVariable int bgId){
        return new ResponseEntity<>(bgservice.getBoardGameById(bgId),HttpStatus.OK);
    }

    @PostMapping("boardgames")
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGame boardGame){
        return new ResponseEntity<>(bgservice.addBoardGame(boardGame), HttpStatus.CREATED);
    }

    @PutMapping("boardgames")
    public BoardGame editBoardGame(@RequestBody BoardGame boardGame){
        return bgservice.updateBoardGame(boardGame);
    }

    @DeleteMapping("boardgames")
    public void  deleteBoardGame(@RequestBody BoardGame boardGame){
        bgservice.deleteBoardGame(boardGame.getId());
    }
}
