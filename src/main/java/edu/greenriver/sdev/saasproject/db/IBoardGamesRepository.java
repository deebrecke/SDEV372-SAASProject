package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.models.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoardGamesRepository extends JpaRepository <BoardGame, Integer>{
}
