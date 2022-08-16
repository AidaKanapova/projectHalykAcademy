package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.AuthorMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    public List<AuthorDTO> findAll() {

        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @Override
    public AuthorDTO getAuthorById(Long authorId) throws Throwable {

        return authorRepository.findById(authorId)
                .map(authorMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Author with id %s not found".formatted(authorId)));
    }


    @Override
    public AuthorDTO addAuthor(SaveAuthorDTO author) {
        Author saved = authorRepository.save(
                new Author(
                        null,
                        author.getFullName(),
                        author.getDateOfBirth(),
                        null,
                        false
                )
        );
        return authorMapper.toDTO(saved);
    }

    @Override
    public AuthorDTO updateAuthor(UpdateAuthorDTO authorDTO) throws Throwable {
        Author author = authorRepository.findById(authorDTO.getId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("Author with id %s not found".formatted(authorDTO.getId())));

        Author updateAuthor = authorRepository.save(
                new Author(
                        authorDTO.getId(),
                        authorDTO.getFullName(),
                        authorDTO.getDateOfBirth(),
                        author.getBooks(),
                        author.isDeleted()
                )
        );
        return authorMapper.toDTO(updateAuthor);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        if(!authorRepository.existsByAuthorId(authorId)){
            throw new ResourceNotFoundeException("Author with id %s not found".formatted(authorId));
        }
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<AuthorDTO> findByName(String name) {
        return authorRepository.findByName(name).stream().map(authorMapper::toDTO).toList();
    }


    @Override
    public List<AuthorGenreListDTO> findAuthorsByGenreList(List<String> genreList) {
        return authorRepository.getAuthorByGenreList(genreList).stream().map(authorMapper::toGenreDTO).toList();

    }

}
