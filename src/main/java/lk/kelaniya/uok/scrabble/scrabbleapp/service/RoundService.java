package lk.kelaniya.uok.scrabble.scrabbleapp.service;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.RoundDTO;
import java.util.List;

public interface RoundService {
    void addRound(RoundDTO roundDTO);
    RoundDTO getSelectedRound(String roundId);
    List<RoundDTO> getRoundsByTournament(String tournamentId);
    List<RoundDTO> getAllRounds();
    RoundDTO updateRound(String roundId, RoundDTO roundDTO);
    void deleteRound(String roundId);
}