package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {

    private int id;
    private String consoleType;
    private String name;
    private boolean multiplayer;

}
