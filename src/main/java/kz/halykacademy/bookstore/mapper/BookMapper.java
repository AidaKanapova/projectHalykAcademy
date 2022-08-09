package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.AuthorNameDTO;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.BookNameDTO;
import kz.halykacademy.bookstore.dto.GenreNameDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorMapper authorMapper;

    public BookNameDTO toBookDTO(Books books) {
        return new BookNameDTO(
                books.getBookId(),
                books.getTitle()
        );
    }

    public BookDTO toDTO(Books book) {

        Set<AuthorNameDTO> authors = Set.of();
        if (book.getAuthors() != null)
            authors = book.getAuthors().stream().map(authorMapper::toAuthorDTO).collect(Collectors.toSet());

        Set<GenreNameDTO> genreNameDTOS = Set.of();
        if (book.getGenres() != null)
            genreNameDTOS = book.getGenres().stream().map(Genre::toGenreDTO).collect(Collectors.toSet());


        return new BookDTO(
                book.getBookId(),
                book.getTitle(),
                genreNameDTOS,
                book.getPrice(),
                authors,
                book.getPublisher().getName(),
                book.getPage_count(),
                book.getRelease_year(),
                book.isDeleted()
        );
    }
}
