package lk.kelaniya.uok.scrabble.scrabbleapp.dao;

import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDao extends JpaRepository<PlayerEntity, String> {
}
