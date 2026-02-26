package lk.kelaniya.uok.scrabble.scrabbleapp.service;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.TournamentDTO;
import java.util.List;

public interface TournamentService {
    void addTournament(TournamentDTO tournamentDTO);
    TournamentDTO getSelectedTournament(String tournamentId);
    List<TournamentDTO> getAllTournaments();
    TournamentDTO updateTournament(String tournamentId, TournamentDTO tournamentDTO);
    void deleteTournament(String tournamentId);
}