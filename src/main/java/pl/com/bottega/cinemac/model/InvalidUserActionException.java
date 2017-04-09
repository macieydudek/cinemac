package pl.com.bottega.cinemac.model;

public class InvalidUserActionException extends RuntimeException {

    public InvalidUserActionException(String msg) {
        super(msg);
    }

}
