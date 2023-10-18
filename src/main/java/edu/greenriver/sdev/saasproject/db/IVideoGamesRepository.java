package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.models.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoGamesRepository extends JpaRepository<VideoGame, Integer> {
}
