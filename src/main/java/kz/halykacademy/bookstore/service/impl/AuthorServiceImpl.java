package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.AuthorMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private  final GenreRepository genreRepository;
    private  final AuthorMapper authorMapper;


    @Override
    public List<AuthorDTO> findAll() {

        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDTO)
                .toList();

    }

    @Override
    public AuthorDTO getAuthorById(long authorId) throws Throwable {

        return authorRepository.findById(authorId)
                .map(authorMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Author %s not found".formatted(authorId)));
    }


    @Override
    public AuthorDTO addAuthor(SaveAuthorDTO author) {
        Author saved = authorRepository.save(
                new Author(
                        author.getAuthorId(),
                        author.getFull_name(),
                        author.getDate_of_birth(),
                        null
                )
        );
        return  authorMapper.toDTO(saved);
    }

    @Override
    public AuthorDTO updateAuthor(SaveAuthorDTO authorDTO, long id) {
        Author author = authorRepository.findById(id).get();

        Author updateAuthor = authorRepository.save(
                new Author(
                        author.getAuthorId(),
                        authorDTO.getFull_name(),
                        authorDTO.getDate_of_birth(),
                        author.getBooks()
                )
        );
        return authorMapper.toDTO(updateAuthor);
    }

    @Override
    public void deleteAuthor(long authorId) throws Exception {

       authorRepository.deleteById(authorId);
    }

    @Override
    public List<AuthorDTO> findByName(String name) {
        return authorRepository.findByName(name).stream().map(authorMapper::toDTO).toList();
    }

    @Override
    public AuthorDTO addBook(long authorId, long bookId) {


        Author author = authorRepository.findById(authorId).get();
        Books books = bookRepository.findById(bookId).get();
        author.getBooks().add(books);

        return authorMapper.toDTO(authorRepository.save(author));
    }


}
