package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.AuthorNameDTO;
import kz.halykacademy.bookstore.dto.SaveAuthorDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
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
}
