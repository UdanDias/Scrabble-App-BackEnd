package lk.kelaniya.uok.scrabble.scrabbleapp.exception;

public class RoundNotFoundException extends RuntimeException {
    public RoundNotFoundException() {
        super();
    }

    public RoundNotFoundException(String message) {
        super(message);
    }

    public RoundNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
