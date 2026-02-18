package lk.kelaniya.uok.scrabble.scrabbleapp.service.impl;

import jakarta.transaction.Transactional;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PerformanceDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PlayerDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.PerformanceService;
import lk.kelaniya.uok.scrabble.scrabbleapp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class PerformanceServiceImpl implements PerformanceService {
    @Override
    public void addPerformance(PerformanceDTO performanceDTO) {
        performanceDTO.setPerformanceId(UtilData.generatePerformanceId());
    }

    @Override
    public void deletePlayer(String performanceId) {

    }

    @Override
    public void updatePlayer(String performanceId, PerformanceDTO performanceDTO) {

    }

    @Override
    public PlayerDTO getSelectedPlayer(String performanceId) {
        return null;
    }

    @Override
    public List<PlayerDTO> getAllPerformances() {
        return List.of();
    }
}
