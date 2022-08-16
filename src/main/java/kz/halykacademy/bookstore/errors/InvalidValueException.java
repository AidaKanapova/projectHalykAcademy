package kz.halykacademy.bookstore.errors;

public class InvalidValueException extends RuntimeException{
    public InvalidValueException(String message) {
        super(message);
    }

}
