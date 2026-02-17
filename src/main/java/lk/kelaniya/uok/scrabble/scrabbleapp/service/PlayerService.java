package lk.kelaniya.uok.scrabble.scrabbleapp.service;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    void addPlayer(PlayerDTO playerDTO);
    void deletePlayer(String playerId);
    void updatePlayer(String playerId,PlayerDTO playerDTO);
    PlayerDTO getSelectedPlayer(String playerId);
    List<PlayerDTO> getAllPlayers();

}
