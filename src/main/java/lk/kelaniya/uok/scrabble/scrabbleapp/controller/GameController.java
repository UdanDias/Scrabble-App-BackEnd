package lk.kelaniya.uok.scrabble.scrabbleapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    @GetMapping
    public String health(){
        return "health is running";
    }
}
