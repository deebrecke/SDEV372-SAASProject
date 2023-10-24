package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.IBoardGamesRepository;
import edu.greenriver.sdev.saasproject.models.BoardGame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author Dee Brecke
 * @version 1.0
 */
@Service
public class BoardGameService {
    private IBoardGamesRepository bgrepo;

    public BoardGameService(IBoardGamesRepository bgrepo){
        this.bgrepo = bgrepo;
    }

    /**
     * Returns a list of all board game objects
     * @return List of all Boardgame objects
     */
    public List<BoardGame> getAllBoardGames(){
        return bgrepo.findAll();
    }

    /**
     * Retrieves BoardGame object by unique id
     * @param id int id primary key
     * @return BoardGame object containing the unique id
     */
    public BoardGame getBoardGameById(int id){
        Optional<BoardGame> found = bgrepo.findById(id)
                .filter(boardGame -> boardGame.getId()==id);
        return found.orElse(null);
    }

    /**
     * Checks to see if a primary key exists
     * @param id int id primary key
     * @return true if primary key exists
     */
    public boolean boardGameExistById(int id){
        return getBoardGameById(id) != null;
    }

    /**
     * Retrieves random BoardGame ojbect
     * @return BoardGame object retrieved
     */
    public BoardGame random(){
        Random rand = new Random();
        List<BoardGame> boardGames = getAllBoardGames();
        return boardGames.get(rand.nextInt(boardGames.size()));

    }

    /**
     * Adds a new board game to the list
     * @param boardGame BoardGame object to be added
     * @return new BoardGame object
     */
    public BoardGame addBoardGame(BoardGame boardGame){
        boardGame = bgrepo.save(boardGame);
        return boardGame;
    }

    /**
     * Changes fields in board game object
     * It is retrieved by id, each field is changed and then stored
     * in the original object and the object is returned
     * @param updatedBoardGame board game that is to be changed
     * @return BoardGame object with new data in the fields
     */
    public BoardGame updateBoardGame(BoardGame updatedBoardGame){
        BoardGame savedBoardGame = getBoardGameById(updatedBoardGame.getId());
        savedBoardGame.setName(updatedBoardGame.getName());
        savedBoardGame.setCategory(updatedBoardGame.getCategory());
        savedBoardGame.setMinPlayers(updatedBoardGame.getMinPlayers());
        savedBoardGame.setMaxPlayers(updatedBoardGame.getMaxPlayers());
        savedBoardGame = bgrepo.save(savedBoardGame);
        return savedBoardGame;
    }

    /**
     * Deletes board game from list given its unique id
     * @param id  int id primary key
     */
    public void deleteBoardGame(int id){
        bgrepo.deleteById(id);
    }

}//end of BoardGameService class
