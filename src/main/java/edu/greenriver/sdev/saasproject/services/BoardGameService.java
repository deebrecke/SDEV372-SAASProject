package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.models.BoardGame;

import java.util.ArrayList;
import java.util.List;

public class BoardGameService {
    private List<BoardGame> boardGames = new ArrayList<>(List.of(
            new BoardGame("family", "Parcheesi", 2, 4),
            new BoardGame("logic", "Terraforming Mars", 1, 5),
            new BoardGame("party", "Cards Against Humanity", 4, 20),
            new BoardGame("other", "Hive", 2, 2)
    ));
}
