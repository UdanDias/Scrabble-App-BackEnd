package lk.kelaniya.uok.scrabble.scrabbleapp.dao;

import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceDao extends JpaRepository<PerformanceEntity, String> {
}
