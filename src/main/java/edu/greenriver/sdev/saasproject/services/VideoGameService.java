package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.IVideoGamesRepository;
import edu.greenriver.sdev.saasproject.models.VideoGame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author Dee Brecke
 * @version 1.0
 */
@Service
public class VideoGameService {
    private IVideoGamesRepository vgrepo;


    public VideoGameService(IVideoGamesRepository vgrepo){
        this.vgrepo = vgrepo;
    }

    /**
     * Returns a list of all video game objects
     * @return List of all VideoGame objects
     */
    public List<VideoGame> getAllVideoGames(){
        return vgrepo.findAll();
    }

    /**
     * Retrieves VideoGame object by unique id
     * @param id int id primary key
     * @return VideoGame object containing the unique id
     */
    public VideoGame getVideoGameById(int id){
        Optional<VideoGame> found = vgrepo.findById(id)
                .filter(videoGame -> videoGame.getId()==id);
        return found.orElse(null);
    }

    /**
     * Checks to see if a primary key exists
     * @param id int id primary key
     * @return true if primary key exists
     */
    public boolean videoGameExistById(int id){
        return getVideoGameById(id) != null;
    }

    /**
     * Retrieves random VideoGame ojbect
     * @return VideoGame object retrieved
     */
    public VideoGame random(){
        Random rand = new Random();
        List<VideoGame> videoGames = getAllVideoGames();
        return videoGames.get(rand.nextInt(videoGames.size()));

    }

    /**
     * Adds a new video game to the list
     * @param videoGame VideoGame object to be added
     * @return new VideoGame object
     */
    public VideoGame addVideoGame(VideoGame videoGame){
        videoGame = vgrepo.save(videoGame);
        return videoGame;
    }

    /**
     * Changes fields in video game object
     * It is retrieved by id, each field is changed and then stored
     * in the original object and the object is returned
     * @param updatedVideoGame board game that is to be changed
     * @return VideoGame object with new data in the fields
     */
    public VideoGame updateVideoGame(VideoGame updatedVideoGame){
        VideoGame savedVideoGame = getVideoGameById(updatedVideoGame.getId());
        savedVideoGame.setName(updatedVideoGame.getName());
        savedVideoGame.setConsoleType(updatedVideoGame.getConsoleType());
        savedVideoGame.setMultiplayer(updatedVideoGame.isMultiplayer());
        savedVideoGame = vgrepo.save(savedVideoGame);
        return savedVideoGame;
    }

    /**
     * Deletes video game from list given its unique id
     * @param id  int id primary key
     */
    public void deleteVideoGame(int id){
        vgrepo.deleteById(id);
    }
}
