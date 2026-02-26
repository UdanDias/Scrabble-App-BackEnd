package lk.kelaniya.uok.scrabble.scrabbleapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TournamentDTO implements Serializable {
    private String tournamentId;
    private String tournamentName;
    private TournamentStatus status;
}