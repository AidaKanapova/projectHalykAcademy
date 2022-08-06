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


    /*@Override
    public Author updateAuthors(Author author) {
        return authorRepository.save(author);
    }*/

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



  /*  @Override
    public List<GenreNameDTO> getGenreList(long id) {
         return  null;
    }
*/
   /* @Override
    public List<AuthorGenreDTO> getGenreList(GenreDTO genre) {
        return genreRepository = genreRepository.findById()

        return authorRepository.findById(authorId)
                .map(Author::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Author %s not found".formatted(authorId)));
        findById(book.getPublisherId())
    }*/
}
