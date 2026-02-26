package lk.kelaniya.uok.scrabble.scrabbleapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "round")
public class RoundEntity {
    @Id
    private String roundId;
    private int roundNumber;


    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentEntity tournament;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameEntity> games;
}