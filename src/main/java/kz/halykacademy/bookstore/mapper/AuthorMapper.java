package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorMapper {

    private final AuthorRepository authorRepository;

    private  final BookMapper bookMapper;

    public AuthorNameDTO toAuthorDTO(Author author) {


        return new AuthorNameDTO(
                author.getAuthorId(),
                author.getFullName()
        );
    }

    public AuthorDTO toDTO(Author author) {

        Set<BookNameDTO> books = Set.of();
        if (author.getBooks() != null)
            books = author.getBooks().stream().map(bookMapper::toBookDTO).collect(Collectors.toSet());

        List<String> genreList = authorRepository.genreList(author.getAuthorId());


        return new AuthorDTO(
                author.getAuthorId(),
                author.getFullName(),
                author.getDateOfBirth(),
                books,
                genreList

        );
    }
    public AuthorGenreListDTO toGenreDTO(Author author){
        List<String> genreList = authorRepository.genreList(author.getAuthorId());

        return new AuthorGenreListDTO(
                author.getFullName(),
                genreList
        );
    }



}
