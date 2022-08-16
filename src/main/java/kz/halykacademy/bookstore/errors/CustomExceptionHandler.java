package kz.halykacademy.bookstore.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler(value = AuthorizationException.class)
    protected ResponseEntity<Object> handleException(AuthorizationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MyError("not allowed", LocalDateTime.now()));
    }
    @ExceptionHandler(value = InvalidValueException.class)
    protected ResponseEntity<Object> handleException(InvalidValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyError(ex.getMessage(), LocalDateTime.now()));
    }
    @ExceptionHandler(value = NotAllowedToOrderModificationException.class)
    protected ResponseEntity<Object> handleException(NotAllowedToOrderModificationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MyError(ex.getMessage(), LocalDateTime.now()));
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