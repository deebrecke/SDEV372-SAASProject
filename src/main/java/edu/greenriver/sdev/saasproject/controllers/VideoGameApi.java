package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.VideoGame;
import edu.greenriver.sdev.saasproject.services.VideoGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class VideoGameApi {
    private VideoGameService vgservice;

    public VideoGameApi(VideoGameService vgservice){
        this.vgservice = vgservice;
    }

    @GetMapping("videogames")
    public ResponseEntity<List<VideoGame>> allVideoGames(){
        return new ResponseEntity<>(vgservice.getAllVideoGames(), HttpStatus.OK);
    }

    @GetMapping("videogames/random")
    public ResponseEntity<VideoGame> random(){
        return new ResponseEntity<>(vgservice.random(), HttpStatus.OK);
    }

    @GetMapping("videogames/{vgId}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable int vgId){
        return new ResponseEntity<>(vgservice.getVideoGameById(vgId), HttpStatus.OK);
    }

    @PostMapping("videogames")
    public ResponseEntity<VideoGame> addVideoGame(@RequestBody VideoGame videoGame){
        return new ResponseEntity<>(vgservice.addVideoGame(videoGame), HttpStatus.CREATED);
    }

    @PutMapping("videogames")
    public VideoGame editVideoGame(@RequestBody VideoGame videoGame){
        return vgservice.updateVideoGame(videoGame);
    }

    @DeleteMapping("videogames")
    public void deleteVideoGame(@RequestBody VideoGame videoGame){
        vgservice.deleteVideoGame(videoGame.getId());
    }
}
