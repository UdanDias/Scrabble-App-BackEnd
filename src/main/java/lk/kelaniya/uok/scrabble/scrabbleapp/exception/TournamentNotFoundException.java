package lk.kelaniya.uok.scrabble.scrabbleapp.exception;

public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException() {
        super();
    }

    public TournamentNotFoundException(String message) {
        super(message);
    }

    public TournamentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
