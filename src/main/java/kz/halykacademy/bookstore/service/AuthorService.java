package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Author;

import java.util.List;

public interface AuthorService {
    public List<AuthorDTO> findAll();
    public AuthorDTO getAuthorById(long authorId) throws  Throwable ;
    public AuthorDTO addAuthor(SaveAuthorDTO author);
/*
    public AuthorDTO updateAuthors(Author author);
*/
    public Author deleteAuthor(long authorId) throws Exception;

    public  List<Author> findByName(String name);

/*
    public List<GenreNameDTO> getGenreList(long id);
*/

/*
    public  List<AuthorGenreDTO> getGenreList(GenreNameDTO genre);
*/
}

