package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {

    private static int videoIds = 0;

    private int id;
    private String consoleType;
    private String name;
    private boolean multiplayer;

    public VideoGame(String consoleType, String name, boolean multiplayer) {
        generateId();
        this.consoleType = consoleType;
        this.name = name;
        this.multiplayer = multiplayer;
    }

    public void generateId(){
        id = videoIds;
        videoIds ++;
    }
}
