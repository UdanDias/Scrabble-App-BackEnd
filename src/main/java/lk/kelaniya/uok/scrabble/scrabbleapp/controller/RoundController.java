package lk.kelaniya.uok.scrabble.scrabbleapp.controller;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.RoundDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.exception.RoundNotFoundException;
import lk.kelaniya.uok.scrabble.scrabbleapp.exception.TournamentNotFoundException;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/round")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @PostMapping("/addround")
    public ResponseEntity<Void> addRound(@RequestBody RoundDTO roundDTO) {
        if (roundDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            roundService.addRound(roundDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (TournamentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getselectedround")
    public ResponseEntity<RoundDTO> getSelectedRound(@RequestParam("roundId") String roundId) {
        if (roundId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(roundService.getSelectedRound(roundId));
        } catch (RoundNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getroundsbytournament")
    public ResponseEntity<List<RoundDTO>> getRoundsByTournament(@RequestParam("tournamentId") String tournamentId) {
        if (tournamentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(roundService.getRoundsByTournament(tournamentId));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallrounds")
    public ResponseEntity<List<RoundDTO>> getAllRounds() {
        try {
            return ResponseEntity.ok(roundService.getAllRounds());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateround")
    public ResponseEntity<RoundDTO> updateRound(@RequestParam("roundId") String roundId,
                                                @RequestBody RoundDTO roundDTO) {
        if (roundId == null || roundDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(roundService.updateRound(roundId, roundDTO));
        } catch (RoundNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteround")
    public ResponseEntity<Void> deleteRound(@RequestParam("roundId") String roundId) {
        if (roundId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            roundService.deleteRound(roundId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RoundNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}