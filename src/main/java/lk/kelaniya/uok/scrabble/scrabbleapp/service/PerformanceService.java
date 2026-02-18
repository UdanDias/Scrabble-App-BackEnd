package lk.kelaniya.uok.scrabble.scrabbleapp.service;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PerformanceDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PlayerDTO;

import java.util.List;

public interface PerformanceService {
    void addPerformance(PerformanceDTO performanceDTO);
    void deletePlayer(String performanceId);
    void updatePlayer(String performanceId,PerformanceDTO performanceDTO);
    PlayerDTO getSelectedPlayer(String performanceId);
    List<PlayerDTO> getAllPerformances();
}
