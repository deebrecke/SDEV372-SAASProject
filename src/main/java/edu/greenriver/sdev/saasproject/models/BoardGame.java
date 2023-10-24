package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for board game objects
 * @author Dee Brecke
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGame {
    //auto-increment primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;
    private String name;
    private int minPlayers;
    private int maxPlayers;

    /**
     * Parameterized constructor without id (auto-generated as primary key)
     * @param category String what type of game it is
     * @param name String name of the game
     * @param minPlayers int minimum number of players needed
     * @param maxPlayers int maximum number of players allowed
     */
    public BoardGame(String category, String name, int minPlayers, int maxPlayers) {
        this.category = category;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

}
