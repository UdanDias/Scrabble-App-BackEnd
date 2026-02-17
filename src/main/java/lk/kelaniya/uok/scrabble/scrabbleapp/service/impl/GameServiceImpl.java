package lk.kelaniya.uok.scrabble.scrabbleapp.service.impl;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.GameDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class GameServiceImpl implements GameService {
    @Override
    public void addGame(GameDTO gameDTO) {

    }

    @Override
    public void deleteGame(String gameId) {

    }

    @Override
    public void updateGame(String gameId, GameDTO gameDTO) {

    }

    @Override
    public GameDTO getSelectedGame(String gameId) {
        return null;
    }

    @Override
    public List<GameDTO> getAllGames() {
        return List.of();
    }
}
