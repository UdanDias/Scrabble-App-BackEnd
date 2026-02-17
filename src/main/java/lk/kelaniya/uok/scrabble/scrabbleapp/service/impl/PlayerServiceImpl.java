package lk.kelaniya.uok.scrabble.scrabbleapp.service.impl;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PlayerDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Override
    public void addPlayer(PlayerDTO playerDTO) {

    }

    @Override
    public void deletePlayer(String playerId) {

    }

    @Override
    public void updatePlayer(String playerId, PlayerDTO playerDTO) {

    }

    @Override
    public PlayerDTO getSelectedPlayer(String playerId) {
        return null;
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return List.of();
    }
}
