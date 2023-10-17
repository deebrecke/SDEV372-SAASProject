package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import edu.greenriver.sdev.saasproject.services.BoardGameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
