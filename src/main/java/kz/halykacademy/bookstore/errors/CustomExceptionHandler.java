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
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex) {

        HttpStatus httpStatus;
        if(ex instanceof  ResourceNotFoundeException){
            httpStatus = HttpStatus.NOT_FOUND;
        }else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(new MyError("Overprice books",LocalDateTime.now())

        );
    }

    class  MyError{
        private String message;
        private LocalDateTime timestamp;

        public MyError(String message, LocalDateTime timestamp) {
            this.message = message;
            this.timestamp = timestamp;
        }
    }
}

