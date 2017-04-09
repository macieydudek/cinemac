package pl.com.bottega.model.commands;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
