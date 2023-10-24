package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import edu.greenriver.sdev.saasproject.services.BoardGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class is the controller for the board game collection
 * It contains routes to use the CRUD functions
 * @author Dee Brecke
 * @version 1.0
 */
@RestController
public class BoardGameApi {
    private BoardGameService bgservice;

    /**
     * constructor for board game service object
     * @param bgservice service object for board game
     */
    public BoardGameApi(BoardGameService bgservice){
        this.bgservice = bgservice;
    }

    /**
     * Retrieves all board games in order of their id's
     * @return the entire http response with status code 200 (status code, headers & body)
     */
    @GetMapping("boardgames")
    public ResponseEntity<List<BoardGame>> allBoardGames(){
        return new ResponseEntity<>(bgservice.getAllBoardGames(), HttpStatus.OK);//200
    }

    /**
     * Retrieves a board game using a randomly generated id.
     * @return the entire http response with status code 200 (status code, headers & body)
     */
    @GetMapping("boardgames/random")
    public ResponseEntity<BoardGame> random(){
        return new ResponseEntity<>(bgservice.random(), HttpStatus.OK);//200
    }

    /**
     * Retrieves the board game with the id entered into the path variable.
     * @param bgId id number of board game to retrieve
     * @return the entire http response with status code 200 or 404 (status code, headers & body)
     */
    @GetMapping("boardgames/{bgId}")
    public ResponseEntity<BoardGame> getBoardGameById(@PathVariable int bgId){
        if(!bgservice.boardGameExistById(bgId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404 not there
        }
        return new ResponseEntity<>(bgservice.getBoardGameById(bgId),HttpStatus.OK);//200
    }

    /**
     * Adds a new board game object to the collection and assigns a unique id.
     * @param boardGame BoardGame object
     * @return the entire http response with status code 201 (status code, headers & body)
     */
    @PostMapping("boardgames")
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGame boardGame){
        return new ResponseEntity<>(bgservice.addBoardGame(boardGame), HttpStatus.CREATED);//201
    }

    /**
     * Changes the fields of an existing board game object, as identified by id #.
     * @param boardGame BoardGame object
     * @return the entire http response with status code 200 or 404 (status code, headers & body)
     */
    @PutMapping("boardgames")
    public ResponseEntity<BoardGame> editBoardGame(@RequestBody BoardGame boardGame){
        if(!bgservice.boardGameExistById(boardGame.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404 not there
        }
        return new ResponseEntity<>(bgservice.updateBoardGame(boardGame), HttpStatus.OK);//200
    }

    /**
     * Given an id #, board game object is removed.
     * @param boardGame BoardGame object
     * @return the entire http response with status code 200 (status code, headers & body)
     */
    @DeleteMapping("boardgames")
    public ResponseEntity deleteBoardGame(@RequestBody BoardGame boardGame){
        if(!bgservice.boardGameExistById(boardGame.getId())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404 not there
        }
        bgservice.deleteBoardGame(boardGame.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);//204 deleted
    }
}
