package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.dto.BookDTO;

import java.util.List;

public interface BookProvider {
    List<BookDTO> getAll();
}
