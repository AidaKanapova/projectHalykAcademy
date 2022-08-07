package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


   /* private AuthorDTO toDTO(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO = modelMapper.map(author,AuthorDTO.class);
        return authorDTO;
    }*/

    @Override
    public List<AuthorDTO> findAll() {

        return authorRepository.findAll()
                .stream()
                .map(Author::toDTO)
                .toList();
    }

    @Override
    public AuthorDTO getAuthorById(long authorId) throws Throwable {
        /*Author author = authorRepository.findById(authorId).get();
        author.setGenreList(authorRepository.genres(authorId));
        return author.toDTO();*/

        return authorRepository.findById(authorId)
                .map(Author::toDTO)
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
                        null,
                        null
                )
        );
        return  saved.toDTO();
    }

    @Override
    public AuthorDTO updateAuthor(SaveAuthorDTO authorDTO, long id) {
        Author author = authorRepository.findById(id).get();

        Author updateAuthor = authorRepository.save(
                new Author(
                        author.getAuthorId(),
                        authorDTO.getFull_name(),
                        authorDTO.getDate_of_birth(),
                        author.getBooks(),
                        author.getGenreList()
                )
        );
        return updateAuthor.toDTO();
    }


    @Override
    public void deleteAuthor(long authorId) throws Exception {

       authorRepository.deleteById(authorId);
    }

    @Override
    public List<AuthorDTO> findByName(String name) {
        return authorRepository.findByName(name).stream().map(Author::toDTO).toList();
    }



}
