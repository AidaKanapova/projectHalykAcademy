package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthors(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author deleteAuthor(long authorId) throws Exception {
        Author deleteAuthor = null;
        try {
            deleteAuthor = authorRepository.findById(authorId).orElse(null);
            if (deleteAuthor == null) {
                throw new Exception("author not available");
            } else {
                authorRepository.deleteById(authorId);
            }
        } catch (Exception ex) {
            throw ex;
        }

        return deleteAuthor;
    }

    @Override
    public List<Author> findByName(String name) {
        return (List<Author>) authorRepository.findByName(name);
    }
}
