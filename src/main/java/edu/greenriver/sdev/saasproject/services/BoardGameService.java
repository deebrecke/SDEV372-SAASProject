package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.IBoardGamesRepository;
import edu.greenriver.sdev.saasproject.models.BoardGame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BoardGameService {
    private IBoardGamesRepository bgrepo;

    public BoardGameService(IBoardGamesRepository bgrepo){
        this.bgrepo = bgrepo;
    }

    public List<BoardGame> getAllBoardGames(){
        return bgrepo.findAll();
    }

    public BoardGame getBoardGameById(int id){
        Optional<BoardGame> found = bgrepo.findById(id)
                .filter(boardGame -> boardGame.getId()==id);
        return found.orElse(null);
    }

    public boolean boardGameExistById(int id){
        return getBoardGameById(id) != null;
    }

    public BoardGame random(){
        Random rand = new Random();
        List<BoardGame> boardGames = getAllBoardGames();
        return boardGames.get(rand.nextInt(boardGames.size()));

    }

    public BoardGame addBoardGame(BoardGame boardGame){
        boardGame = bgrepo.save(boardGame);
        return boardGame;
    }

    public BoardGame updateBoardGame(BoardGame updatedBoardGame){
        BoardGame savedBoardGame = getBoardGameById(updatedBoardGame.getId());
        savedBoardGame.setName(updatedBoardGame.getName());
        savedBoardGame.setCategory(updatedBoardGame.getCategory());
        savedBoardGame.setMinPlayers(updatedBoardGame.getMinPlayers());
        savedBoardGame.setMaxPlayers(updatedBoardGame.getMaxPlayers());
        savedBoardGame = bgrepo.save(savedBoardGame);
        return savedBoardGame;
    }

    public void deleteBoardGame(int id){
        bgrepo.deleteById(id);
    }

}//end of BoardGameService class
