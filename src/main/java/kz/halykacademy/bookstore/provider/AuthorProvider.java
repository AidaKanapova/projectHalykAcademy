package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.dto.Author;

import java.util.List;

public interface AuthorProvider {
    List<Author> getAll();
}
