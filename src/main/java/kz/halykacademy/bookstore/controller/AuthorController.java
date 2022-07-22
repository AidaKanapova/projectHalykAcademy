package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.Author;
import kz.halykacademy.bookstore.provider.AuthorProvider;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AuthorController implements AuthorProvider {


    private static List<Author> authors = List.of(
            new Author("Джек Лондон", LocalDate.of(1876, 1,12), Collections.emptyList()),
            new Author("Харпер Ли", LocalDate.of(1926, 4,28), Collections.emptyList()));

    @Override
    public List<Author> getAll() {
        return authors;
    }
}
