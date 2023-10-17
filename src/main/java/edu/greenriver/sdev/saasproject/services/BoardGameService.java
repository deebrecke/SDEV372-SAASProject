package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BoardGameService {
    private List<BoardGame> boardGames = new ArrayList<>(List.of(
            new BoardGame("family", "Parcheesi", 2, 4),
            new BoardGame("logic", "Terraforming Mars", 1, 5),
            new BoardGame("party", "Cards Against Humanity", 4, 20),
            new BoardGame("other", "Hive", 2, 2)
    ));

    public List<BoardGame> getAllBoardGames(){
        return boardGames;
    }

    public BoardGame getBoardGameById(int id){
        Optional<BoardGame> found = boardGames.stream()
                .filter(boardGame -> boardGame.getId()==id)
                .findFirst();
        return found.orElse(null);
    }

    public BoardGame random(){
        Random rand = new Random();
        return boardGames.get(rand.nextInt(boardGames.size()));

    }

    public void addBoardGame(BoardGame boardGame){
        boardGames.add(boardGame);
    }

    //this is because the example only has one other field
    //TODO: either write new methods to update individual fields
    //OR modify this to update all fields
    public void updateBoardGameName(BoardGame updatedBoardGame){
        BoardGame savedBoardGame = getBoardGameById(updatedBoardGame.getId());
        savedBoardGame.setName(updatedBoardGame.getName());
    }

    public void deleteBoardGame(int id){
        for (int i = 0; i < boardGames.size(); i++) {
            if(boardGames.get(i).getId()==id){
                boardGames.remove(i);
                break;
            }
        }

    }

}//end of BoardGameService class
