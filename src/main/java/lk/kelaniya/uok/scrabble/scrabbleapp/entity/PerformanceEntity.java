package lk.kelaniya.uok.scrabble.scrabbleapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "performance")
public class PerformanceEntity {

    @Id
    private String playerId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "player_id",nullable = false, unique = true)
    @ToString.Exclude
    private PlayerEntity player;

    private double totalWins;
    private int totalGamesPlayed;
    private int cumMargin;
    private double avgMargin;
    private int playerRank;
}
