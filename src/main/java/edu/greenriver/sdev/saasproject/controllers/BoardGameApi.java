package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import edu.greenriver.sdev.saasproject.services.BoardGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardGameApi {
    private BoardGameService bgservice;

    public BoardGameApi(BoardGameService bgservice){
        this.bgservice = bgservice;
    }

    @GetMapping("boardgames")
    public ResponseEntity<List<BoardGame>> allBoardGames(){
        return new ResponseEntity<>(bgservice.getAllBoardGames(), HttpStatus.OK);//200
    }

    @GetMapping("boardgames/random")
    public ResponseEntity<BoardGame> random(){
        return new ResponseEntity<>(bgservice.random(), HttpStatus.OK);//200
    }

    @GetMapping("boardgames/{bgId}")
    public ResponseEntity<BoardGame> getBoardGameById(@PathVariable int bgId){
        return new ResponseEntity<>(bgservice.getBoardGameById(bgId),HttpStatus.OK);//200
    }

    @PostMapping("boardgames")
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGame boardGame){
        return new ResponseEntity<>(bgservice.addBoardGame(boardGame), HttpStatus.CREATED);//201
    }

    @PutMapping("boardgames")
    public ResponseEntity<BoardGame> editBoardGame(@RequestBody BoardGame boardGame){
        if(!bgservice.boardGameExistById(boardGame.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404 not there
        }
        return new ResponseEntity<>(bgservice.updateBoardGame(boardGame), HttpStatus.OK);//200
    }

    @DeleteMapping("boardgames")
    public ResponseEntity deleteBoardGame(@RequestBody BoardGame boardGame){
        if(!bgservice.boardGameExistById(boardGame.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404 not there
        }
        bgservice.deleteBoardGame(boardGame.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);//204 deleted
    }
}
