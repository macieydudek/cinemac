package pl.com.bottega.cinemac.model.commands;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
