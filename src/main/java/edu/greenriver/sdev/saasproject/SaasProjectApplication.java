package edu.greenriver.sdev.saasproject;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import edu.greenriver.sdev.saasproject.models.VideoGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for Software as a service Class
 * @author Dee Brecke
 * @version 1.0
 */
@SpringBootApplication
public class SaasProjectApplication {
    /**
     * entry point for program
     * @param args main method
     */
    public static void main(String[] args) {
        SpringApplication.run(SaasProjectApplication.class, args);

        List<BoardGame> boardGames = new ArrayList<>(List.of(
                new BoardGame("family", "Parcheesi", 2, 4),
                new BoardGame("logic", "Terraforming Mars", 1, 5),
                new BoardGame("party", "Cards Against Humanity", 4, 20),
                new BoardGame("other", "Hive", 2, 2)
        ));

        List<VideoGame> videoGames = new ArrayList<>(List.of(
                new VideoGame("Wii", "Wii Fit", false),
                new VideoGame("Oculus", "In Death: Unchained", false),
                new VideoGame("Switch", "Mario Party", true)
        ));
    }
}
