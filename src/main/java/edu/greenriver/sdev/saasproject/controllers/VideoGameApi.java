package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.VideoGame;
import edu.greenriver.sdev.saasproject.services.VideoGameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoGameApi {
    private VideoGameService vgservice;

    public VideoGameApi(VideoGameService vgservice){
        this.vgservice = vgservice;
    }

    @GetMapping("videogames")
    public List<VideoGame> allVideoGames(){
        return vgservice.getAllVideoGames();
    }

    @GetMapping("videogames/random")
    public VideoGame random(){
        return vgservice.random();
    }

    @GetMapping("videogames/{vgId}")
    public VideoGame getVideoGameById(@PathVariable int vgId){
        return vgservice.getVideoGameById(vgId);
    }

    @PostMapping("videogames")
    public VideoGame addVideoGame(@RequestBody VideoGame videoGame){
        return vgservice.addVideoGame(videoGame);
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
