package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.VideoGame;
import edu.greenriver.sdev.saasproject.services.VideoGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * This class is the controller for the video game collection
 * It contains routes to use the CRUD functions
 * @author Dee Brecke
 * @version 1.0
 */
@RestController
public class VideoGameApi {
    private VideoGameService vgservice;

    /**
     * constructor for board game service object
     * @param vgservice service object for video game
     */
    public VideoGameApi(VideoGameService vgservice){
        this.vgservice = vgservice;
    }

    /**
     * Retrieves all video games in order of their id's
     * @return the entire http response with status code 200 (status code, headers & body)
     */
    @GetMapping("videogames")
    public ResponseEntity<List<VideoGame>> allVideoGames(){
        return new ResponseEntity<>(vgservice.getAllVideoGames(), HttpStatus.OK);
    }

    /**
     * Retrieves a video game using a randomly generated id.
     * @return the entire http response with status code 200 (status code, headers & body)
     */
    @GetMapping("videogames/random")
    public ResponseEntity<VideoGame> random(){
        return new ResponseEntity<>(vgservice.random(), HttpStatus.OK);
    }

    /**
     * Retrieves the video game with the id entered into the path variable.
     * @param vgId id number of board game to retrieve
     * @return the entire http response with status code 200 or 404 (status code, headers & body)
     */
    @GetMapping("videogames/{vgId}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable int vgId){
        if(!vgservice.videoGameExistById(vgId)){
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(vgservice.getVideoGameById(vgId), HttpStatus.OK);
    }

    /**
     * Adds a new video game object to the collection and assigns a unique id.
     * @param videoGame VideoGame object
     * @return the entire http response with status code 201 (status code, headers & body)
     */
    @PostMapping("videogames")
    public ResponseEntity<VideoGame> addVideoGame(@RequestBody VideoGame videoGame){
        return new ResponseEntity<>(vgservice.addVideoGame(videoGame), HttpStatus.CREATED);
    }

    /**
     * Changes the fields of an existing video game object, as identified by id #.
     * @param videoGame VideoGame object
     * @return the entire http response with status code 200 or 404 (status code, headers & body)
     */
    @PutMapping("videogames")
    public ResponseEntity<VideoGame> editVideoGame(@RequestBody VideoGame videoGame){
        if(!vgservice.videoGameExistById(videoGame.getId())){
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(vgservice.updateVideoGame(videoGame), HttpStatus.OK);
    }

    /**
     * Given an id #, video game object is removed.
     * @param videoGame VideoGame object
     * @return the entire http response with status code 200 (status code, headers & body)
     */
    @DeleteMapping("videogames")
    public ResponseEntity deleteVideoGame(@RequestBody VideoGame videoGame){
        if(!vgservice.videoGameExistById(videoGame.getId())){
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
        vgservice.deleteVideoGame(videoGame.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
