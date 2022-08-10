package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.*;

import java.util.List;

public interface AuthorService {
    public List<AuthorDTO> findAll();
    public AuthorDTO getAuthorById(long authorId) throws  Throwable ;
    public AuthorDTO addAuthor(SaveAuthorDTO author);

    public AuthorDTO updateAuthor(SaveAuthorDTO author, long id);

    public void deleteAuthor(long authorId) throws Exception;

    public  List<AuthorDTO> findByName(String name);

    public  AuthorDTO addBook(long authorId, long bookId);
    public List<AuthorGenreListDTO> findAuthorsByGenreList(List<String> genreList);


/*
    public List<GenreNameDTO> getGenreList(long id);
*/

/*
    public  List<AuthorGenreDTO> getGenreList(GenreNameDTO genre);
*/
}

