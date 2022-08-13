package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.dto.SaveGenreDTO;
import kz.halykacademy.bookstore.entity.Genre;

import java.util.List;

public interface GenreService {

    public List<GenreDTO> getAllGenre();
    public  GenreDTO getGenreById(Long genreId) throws Throwable;
    public GenreDTO addGenre(GenreDTO genre);
    public GenreDTO updateGenre(GenreDTO genre) throws Throwable;
    public void deleteGenre(Long genreId) throws Throwable;
    public GenreDTO findByName(String name);

}
