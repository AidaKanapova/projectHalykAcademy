package kz.halykacademy.bookstore.errors;

public class ResourceNotFoundeException  extends RuntimeException{
    public ResourceNotFoundeException(String message) {
        super(message);
    }
}
