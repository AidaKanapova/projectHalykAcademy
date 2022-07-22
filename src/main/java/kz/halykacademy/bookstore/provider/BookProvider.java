package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.dto.Book;

import java.util.List;

public interface BookProvider {
    List<Book> getAll();
}
