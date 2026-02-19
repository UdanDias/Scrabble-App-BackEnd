package lk.kelaniya.uok.scrabble.scrabbleapp.service.impl;

import jakarta.transaction.Transactional;
import lk.kelaniya.uok.scrabble.scrabbleapp.dao.PerformanceDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dao.PlayerDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PlayerDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PerformanceEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PlayerEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.exception.PlayerNotFoundException;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.PerformanceService;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.PlayerService;
import lk.kelaniya.uok.scrabble.scrabbleapp.util.EntityDTOConvert;
import lk.kelaniya.uok.scrabble.scrabbleapp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional

public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;
    private final EntityDTOConvert entityDTOConvert;
    @Override
    public void addPlayer(PlayerDTO playerDTO) {
        playerDTO.setPlayerId(UtilData.generatePlayerId());
        playerDTO.setAge(UtilData.calcAge(playerDTO.getDob()));
        playerDTO.setAccountCreatedDate(UtilData.generateTodayDate());

        PlayerEntity playerEntity=entityDTOConvert.convertPlayerDTOToPlayerEntity(playerDTO);
        PerformanceEntity performanceEntity=new PerformanceEntity();
        performanceEntity.setPerformanceId(UtilData.generatePerformanceId());
        performanceEntity.setPlayer(playerEntity);
        performanceEntity.setTotalWins(0);
        performanceEntity.setTotalGamesPlayed(0);
        performanceEntity.setCumMargin(0);
        performanceEntity.setAvgMargin(0.0);
        performanceEntity.setPlayerRank(0);

        playerEntity.setPerformance(performanceEntity);
        playerDao.save(playerEntity);
    }

    @Override
    public void deletePlayer(String playerId) {
        playerDao.findById(playerId).orElseThrow(()->new PlayerNotFoundException("Player not found"));
        playerDao.deleteById(playerId);
    }

    @Override
    public void updatePlayer(String playerId, PlayerDTO playerDTO) {
        PlayerEntity playerEntity = playerDao.findById(playerId).orElseThrow(()->new PlayerNotFoundException("Player not Found"));
        playerEntity.setFirstName(playerDTO.getFirstName());
        playerEntity.setLastName(playerDTO.getLastName());
        playerEntity.setAge(UtilData.calcAge(playerDTO.getDob()));
        playerEntity.setGender(playerDTO.getGender());
        playerEntity.setDob(playerDTO.getDob());
        playerEntity.setEmail(playerDTO.getEmail());
        playerEntity.setPhone(playerDTO.getPhone());
        playerEntity.setAddress(playerDTO.getAddress());
        playerEntity.setFaculty(playerDTO.getFaculty());
        playerEntity.setAcademicLevel(playerDTO.getAcademicLevel());

    }

    @Override
    public PlayerDTO getSelectedPlayer(String playerId) {
        PlayerEntity playerEntity=playerDao.findById(playerId).orElseThrow(()->new PlayerNotFoundException("Player not found"));
        return entityDTOConvert.convertPlayerEntityToPlayerDTO(playerEntity);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return entityDTOConvert.convertPlayerEntityListToPlayerDTOList(playerDao.findAll());
    }
}
