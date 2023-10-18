package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.IVideoGamesRepository;
import edu.greenriver.sdev.saasproject.models.VideoGame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class VideoGameService {
    private IVideoGamesRepository vgrepo;

    public VideoGameService(IVideoGamesRepository vgrepo){
        this.vgrepo = vgrepo;
    }

    public List<VideoGame> getAllVideoGames(){
        return vgrepo.findAll();
    }

    public VideoGame getVideoGameById(int id){
        Optional<VideoGame> found = vgrepo.findById(id)
                .filter(videoGame -> videoGame.getId()==id);
        return found.orElse(null);
    }

    public VideoGame random(){
        Random rand = new Random();
        List<VideoGame> videoGames = getAllVideoGames();
        return videoGames.get(rand.nextInt(videoGames.size()));

    }

    public VideoGame addVideoGame(VideoGame videoGame){
        videoGame = vgrepo.save(videoGame);
        return videoGame;
    }

    public VideoGame updateVideoGame(VideoGame updatedVideoGame){
        VideoGame savedVideoGame = getVideoGameById(updatedVideoGame.getId());
        savedVideoGame.setName(updatedVideoGame.getName());
        savedVideoGame.setConsoleType(updatedVideoGame.getConsoleType());
        savedVideoGame.setMultiplayer(updatedVideoGame.isMultiplayer());
        savedVideoGame = vgrepo.save(savedVideoGame);
        return savedVideoGame;
    }

    public void deleteVideoGame(int id){
        vgrepo.deleteById(id);
    }
}
