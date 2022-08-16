package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAllGenre();
    GenreDTO getGenreById(Long genreId) throws Throwable;
    GenreDTO addGenre(GenreDTO genre);
    GenreDTO updateGenre(GenreDTO genre) throws Throwable;
    void deleteGenre(Long genreId) throws Throwable;
    GenreDTO findByName(String name);

}
