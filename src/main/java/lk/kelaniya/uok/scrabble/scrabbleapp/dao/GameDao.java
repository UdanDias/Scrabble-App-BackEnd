package lk.kelaniya.uok.scrabble.scrabbleapp.dao;

import lk.kelaniya.uok.scrabble.scrabbleapp.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDao extends JpaRepository<GameEntity, String> {
}
