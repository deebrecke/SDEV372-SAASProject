package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public VideoGame(String consoleType, String name, boolean multiplayer) {
        this.consoleType = consoleType;
        this.name = name;
        this.multiplayer = multiplayer;
    }

}
