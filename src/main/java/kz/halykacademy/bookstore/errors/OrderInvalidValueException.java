package kz.halykacademy.bookstore.errors;

public class OrderInvalidValueException extends RuntimeException{
    public OrderInvalidValueException(String message) {
        super(message);
    }

}
