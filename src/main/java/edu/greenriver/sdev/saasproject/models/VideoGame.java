package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for video game objects
 * @author Dee Brecke
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {
    //auto-increment primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String consoleType;
    private String name;
    private boolean multiplayer;

    /**
     * Parameterized constructor without id (auto-generated as primary key)
     * @param consoleType String type of video game console
     * @param name String name of the game
     * @param multiplayer boolean is it multiplayer
     */
    public VideoGame(String consoleType, String name, boolean multiplayer) {
        this.consoleType = consoleType;
        this.name = name;
        this.multiplayer = multiplayer;
    }

}
