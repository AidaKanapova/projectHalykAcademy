package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.AuthorNameDTO;
import kz.halykacademy.bookstore.dto.BookNameDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
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

    public AuthorNameDTO toAuthorDTO(Author author) {


        return new AuthorNameDTO(
                author.getAuthorId(),
                author.getFull_name()
        );
    }

    public AuthorDTO toDTO(Author author) {

        Set<BookNameDTO> books = Set.of();
        if (author.getBooks() != null)
            books = author.getBooks().stream().map(Books::toBookDTO).collect(Collectors.toSet());

        List<String> genreList = authorRepository.genreList(author.getAuthorId());


        return new AuthorDTO(
                author.getAuthorId(),
                author.getFull_name(),
                author.getDate_of_birth(),
                books,
                genreList
        );
    }


}
