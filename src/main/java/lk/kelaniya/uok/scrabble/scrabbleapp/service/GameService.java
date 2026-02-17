package lk.kelaniya.uok.scrabble.scrabbleapp.service;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.GameDTO;

import java.util.List;

public interface GameService {
    void addGame(GameDTO gameDTO);
    void deleteGame(String gameId);
    void updateGame(String gameId,GameDTO gameDTO);
    GameDTO getSelectedGame(String gameId);
    List<GameDTO> getAllGames();
}
