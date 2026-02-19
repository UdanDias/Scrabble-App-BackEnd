package lk.kelaniya.uok.scrabble.scrabbleapp.util;

import lk.kelaniya.uok.scrabble.scrabbleapp.dao.GameDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dao.PerformanceDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.GameDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.PerformanceDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.GameEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PerformanceEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.exception.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PerformanceCalc {
    private final PerformanceDao performanceDao;
    private final GameDao gameDao;
    private final EntityDTOConvert entityDTOConvert;

    public String calcWinner(GameDTO gameDTO) {
        String winnerId="";
        int score1 = gameDTO.getScore1();
        int score2 = gameDTO.getScore2();

        if (score1 > score2) {
            winnerId=gameDTO.getPlayer1Id();
        }else if (score1 < score2) {
            winnerId=gameDTO.getPlayer2Id();
        }else {
            gameDTO.setGameTied(true);
        }

        return winnerId;
    }
    public int calcMargin(GameDTO gameDTO) {
        int score1 = gameDTO.getScore1();
        int score2 = gameDTO.getScore2();
        int margin;
        if (score1 > score2) {
            margin=score1-score2;
        }else{
            margin=score2-score1;
        }
        return margin;
    }
    public void updatePerformanceAfterGame(PerformanceEntity performanceEntity, GameDTO gameDTO) {
        performanceEntity.setTotalGamesPlayed(performanceEntity.getTotalGamesPlayed()+1);

        if (!gameDTO.isGameTied()){
            performanceEntity.setTotalWins(performanceEntity.getTotalWins()+1);

        }else{
            performanceEntity.setTotalWins(performanceEntity.getTotalWins()+0.5);
        }

    }
    public void updateRanks() {

        List<PerformanceEntity> performances = performanceDao.findAll();

        // Sort by totalWins DESC, then avgMargin DESC (rounded to 2 decimals)
        performances.sort((p1, p2) -> {
            // Compare total wins (double) descending
            int cmpWins = Double.compare(p2.getTotalWins(), p1.getTotalWins());
            if (cmpWins != 0) {
                return cmpWins;
            }

            // Compare avgMargin rounded to 2 decimals
            double avg1 = Math.round(p1.getAvgMargin() * 100.0) / 100.0;
            double avg2 = Math.round(p2.getAvgMargin() * 100.0) / 100.0;

            return Double.compare(avg2, avg1); // descending
        });

        int rank = 1;
        int sameRankCount = 1;

        PerformanceEntity previous = null;

        for (PerformanceEntity current : performances) {

            double currentAvg = Math.round(current.getAvgMargin() * 100.0) / 100.0;
            current.setAvgMargin(currentAvg);

            if (previous != null) {
                double prevAvg = Math.round(previous.getAvgMargin() * 100.0) / 100.0;

                if (current.getTotalWins() == previous.getTotalWins() &&
                        Double.compare(currentAvg, prevAvg) == 0) {
                    current.setPlayerRank(rank);
                    sameRankCount++;
                } else {
                    rank += sameRankCount;
                    current.setPlayerRank(rank);
                    sameRankCount = 1;
                }
            } else {
                current.setPlayerRank(rank);
            }
            performanceDao.save(current);
            previous = current;
        }
    }
    public void updateBothPlayersPerformance(PerformanceEntity player1Perf, PerformanceEntity player2Perf, GameDTO gameDTO) {

        if (!gameDTO.isGameTied()) {
            if (gameDTO.getWinnerId().equals(player1Perf.getPlayerId())) {
                updatePerformanceAfterGame(player1Perf, gameDTO);
                player2Perf.setTotalGamesPlayed(player2Perf.getTotalGamesPlayed() + 1);
            } else {
                updatePerformanceAfterGame(player2Perf, gameDTO);
                player1Perf.setTotalGamesPlayed(player1Perf.getTotalGamesPlayed() + 1);
            }
        } else {
            updatePerformanceAfterGame(player1Perf, gameDTO);
            updatePerformanceAfterGame(player2Perf, gameDTO);
        }

        // Update cumulative margins
        player1Perf.setCumMargin(player1Perf.getCumMargin() + (gameDTO.getScore1() - gameDTO.getScore2()));
        player2Perf.setCumMargin(player2Perf.getCumMargin() + (gameDTO.getScore2() - gameDTO.getScore1()));

        // Update average margins
        player1Perf.setAvgMargin(Math.round((double) player1Perf.getCumMargin() / player1Perf.getTotalGamesPlayed() * 100.0) / 100.0);
        player2Perf.setAvgMargin(Math.round((double) player2Perf.getCumMargin() / player2Perf.getTotalGamesPlayed() * 100.0) / 100.0);
    }

    public void reCalculateAllPerformances(){
        List<PerformanceEntity> performanceEntityList = performanceDao.findAll();
        for(PerformanceEntity performance : performanceEntityList){
            performance.setTotalGamesPlayed(0);
            performance.setAvgMargin(0.0);
            performance.setPlayerRank(0);
            performance.setCumMargin(0);
            performance.setTotalWins(0.0);
            performanceDao.save(performance);
        }

        List<GameEntity> GamesList=gameDao.findAll();
        for(GameEntity game : GamesList){
            GameDTO gameDTO=entityDTOConvert.convertGameEntityToGameDTO(game);
            PerformanceEntity player1Perf=performanceDao.findById(game.getPlayer1().getPlayerId())
                    .orElseThrow(()->new PlayerNotFoundException("Player 1 not found"));
            PerformanceEntity player2Perf=performanceDao.findById(game.getPlayer2().getPlayerId())
                    .orElseThrow(()->new PlayerNotFoundException("Player 2 not found"));
            updateBothPlayersPerformance(player1Perf, player2Perf, gameDTO);

        }
        updateRanks();
    }



}
