package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;

import java.util.List;

public interface AuthorService {
    public List<Author> getAllAuthors();
    public Author getAuthorById(long authorId);
    public Author addAuthor(Author author);
    public Author updateAuthors(Author author);
    public Author deleteAuthor(long authorId) throws Exception;

    public  List<Author> findByName(String name);
}
