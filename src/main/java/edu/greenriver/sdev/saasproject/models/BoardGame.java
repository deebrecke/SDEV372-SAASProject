package edu.greenriver.sdev.saasproject.models;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGame {
    private int id;
    private String category;
    private String name;
    private int minPlayers;
    private int maxPlayers;


}
