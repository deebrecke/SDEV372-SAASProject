package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGame {

    private static int boardIds = 0;

    private int id;
    private String category;
    private String name;
    private int minPlayers;
    private int maxPlayers;


    public BoardGame(String category, String name, int minPlayers, int maxPlayers) {
        generateId();
        this.category = category;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public void generateId(){
        id = boardIds;
        boardIds ++;
    }

}
