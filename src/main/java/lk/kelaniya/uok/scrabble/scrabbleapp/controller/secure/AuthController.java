package lk.kelaniya.uok.scrabble.scrabbleapp.controller.secure;

import lk.kelaniya.uok.scrabble.scrabbleapp.dto.secure.JWTAuthResponse;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.secure.SignIn;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.secure.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn)  {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<JWTAuthResponse> signUp(@RequestBody UserDTO signUp)  {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
