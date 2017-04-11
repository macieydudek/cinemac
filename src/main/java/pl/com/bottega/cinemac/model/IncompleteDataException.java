package pl.com.bottega.cinemac.model;

public class IncompleteDataException extends RuntimeException {
    public IncompleteDataException(String s) {
        super(s);
    }
}
