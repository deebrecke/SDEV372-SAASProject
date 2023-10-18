package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.models.VideoGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class VideoGameService {
    private List<VideoGame> videoGames = new ArrayList<>(List.of(
        new VideoGame("Wii", "Wii Fit", false),
        new VideoGame("Oculus", "In Death: Unchained", false),
        new VideoGame("Switch", "Mario Party", true)
    ));


    public List<VideoGame> getAllVideoGames(){
        return videoGames;
    }

    public VideoGame getVideoGameById(int id){
        Optional<VideoGame> found = videoGames.stream()
                .filter(videoGame -> videoGame.getId()==id)
                .findFirst();
        return found.orElse(null);
    }

    public VideoGame random(){
        Random rand = new Random();
        return videoGames.get(rand.nextInt(videoGames.size()));

    }

    public VideoGame addVideoGame(VideoGame videoGame){
        videoGame.generateId();
        videoGames.add(videoGame);
        return videoGame;
    }

    public VideoGame updateVideoGame(VideoGame updatedVideoGame){
        VideoGame savedVideoGame = getVideoGameById(updatedVideoGame.getId());
        savedVideoGame.setName(updatedVideoGame.getName());
        savedVideoGame.setConsoleType(updatedVideoGame.getConsoleType());
        //savedVideoGame.setMultiplayer(updatedVideoGame.getMultiplayer));
        return savedVideoGame;
    }

    public void deleteVideoGame(int id){
        for (int i = 0; i < videoGames.size(); i++) {
            if(videoGames.get(i).getId()==id){
                videoGames.remove(i);
                break;
            }
        }

    }
}

/*
    private int id;
    private String consoleType;
    private String name;
    private boolean multiplayer;
 */