package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import edu.greenriver.sdev.saasproject.services.BoardGameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardGameApi {
    private BoardGameService bgservice;

    public BoardGameApi(BoardGameService bgservice){
        this.bgservice = bgservice;
    }

    @GetMapping("boardgames")
    public List<BoardGame> allBoardGames(){
        return bgservice.getAllBoardGames();
    }

    @GetMapping("boardgames/random")
    public BoardGame random(){
        return bgservice.random();
    }

    @GetMapping("boardgames/{bgId}")
    public BoardGame getBoardGameById(@PathVariable int bgId){
        return bgservice.getBoardGameById(bgId);
    }

    @PostMapping("boardgames")
    public BoardGame addBoardGame(@RequestBody BoardGame boardGame){
        return bgservice.addBoardGame(boardGame);
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
