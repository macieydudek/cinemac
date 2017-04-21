package pl.com.bottega.cinemac.model.commands;

public class InvalidCommandException extends RuntimeException {

    private Validatable.ValidationErrors errors;

    public InvalidCommandException(Validatable.ValidationErrors errors) {
        this.errors = errors;
    }

    public InvalidCommandException(String fieldName, String error) {
        errors = new Validatable.ValidationErrors();
        errors.add(fieldName, error);
    }

    public Validatable.ValidationErrors getErrors() {
        return errors;
    }
}
