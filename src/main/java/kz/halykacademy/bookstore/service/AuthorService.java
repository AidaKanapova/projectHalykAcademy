package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.*;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> findAll();

    AuthorDTO getAuthorById(Long authorId) throws Throwable;

    AuthorDTO addAuthor(SaveAuthorDTO author);

    AuthorDTO updateAuthor(UpdateAuthorDTO author) throws Throwable;

    void deleteAuthor(Long authorId) throws Throwable;

    List<AuthorDTO> findByName(String name);

    List<AuthorGenreListDTO> findAuthorsByGenreList(List<String> genreList);
}

