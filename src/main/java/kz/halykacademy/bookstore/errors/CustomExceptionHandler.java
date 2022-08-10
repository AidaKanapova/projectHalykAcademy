package kz.halykacademy.bookstore.errors;

import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MyError(ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(value = ResourceNotFoundeException.class)
    protected ResponseEntity<Object> handleNotFound(ResourceNotFoundeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MyError(ex.getMessage(), LocalDateTime.now()));
    }
}

class MyError {
    private String message;
    private LocalDateTime timestamp;

    public MyError(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}