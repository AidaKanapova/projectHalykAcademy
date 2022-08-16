package kz.halykacademy.bookstore.errors;

public class NotAllowedToOrderModificationException extends RuntimeException{
    public NotAllowedToOrderModificationException(String message) {
        super(message);
    }

}

