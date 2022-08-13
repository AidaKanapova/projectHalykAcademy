package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.dto.GenreNameDTO;
import kz.halykacademy.bookstore.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GenreMapper {

    public GenreDTO toDTO(Genre genre){
        return  new GenreDTO(
                genre.getGenre_id(),
                genre.getGenre_name()
        );
    }

    public GenreNameDTO toGenreDTO(Genre genre){
        return new GenreNameDTO(
                genre.getGenre_name()
        );
    }
}
