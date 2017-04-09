package pl.com.bottega.cinemac.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;

@ControllerAdvice
public class ErrorHandlers {


    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<String> handleInvalidCommandException(InvalidCommandException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                String.format("{\"error\": \"Invalid Command Error\", \"details\": \"%s\"}", ex.getMessage()),
        headers,
        HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
