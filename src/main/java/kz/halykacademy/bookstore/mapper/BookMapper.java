package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Books;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class BookMapper {
    @Lazy
    @Autowired
    private  AuthorMapper authorMapper;

    @Lazy
    @Autowired
    private  GenreMapper genreMapper;


    public BookNameDTO toBookDTO(Books books) {
        return new BookNameDTO(
                books.getId(),
                books.getTitle()
        );
    }



    public BookDTO toDTO(Books book) {

        Set<AuthorNameDTO> authors = Set.of();
        if (book.getAuthors() != null)
            authors = book.getAuthors().stream().map(authorMapper::toAuthorDTO).collect(Collectors.toSet());

        Set<GenreNameDTO> genres = Set.of();
        if (book.getGenres() != null)
            genres = book.getGenres().stream().map(genreMapper::toGenreDTO).collect(Collectors.toSet());


        return new BookDTO(
                book.getId(),
                book.getTitle(),
                genres,
                book.getPrice(),
                authors,
                book.getPublisher().getName(),
                book.getPageCount(),
                book.getReleaseYear(),
                book.isDeleted()
        );
    }

    public BookGenreDTO toGenreDTO(Books book){
        Set<GenreNameDTO> genres = Set.of();
        if (book.getGenres() != null)
            genres = book.getGenres().stream().map(genreMapper::toGenreDTO).collect(Collectors.toSet());

        return new BookGenreDTO(
                book.getId(),
                book.getTitle(),
                genres
        );
    }

}

