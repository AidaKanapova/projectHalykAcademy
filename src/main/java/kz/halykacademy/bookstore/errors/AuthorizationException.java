package kz.halykacademy.bookstore.errors;



public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
