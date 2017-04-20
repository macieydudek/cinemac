package pl.com.bottega.cinemac.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;
import pl.com.bottega.cinemac.model.commands.Validatable;


@ControllerAdvice
public class ErrorHandlers {


    @ExceptionHandler(InvalidUserActionException.class)

    public ResponseEntity<String> handleInvalidUserActionException(InvalidUserActionException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                String.format("{\"error\": \"Invalid User Action\", \"details\": \"%s\"}", ex.getMessage()),
        headers,
        HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<Validatable.ValidationErrors> handleInvalidCommandException(InvalidCommandException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<Validatable.ValidationErrors>(
                ex.getErrors(),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
