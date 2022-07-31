package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.dto.SaveGenreDTO;
import kz.halykacademy.bookstore.entity.Genre;

import java.util.List;

public interface GenreService {

    public List<GenreDTO> getAllGenre();
    public  GenreDTO getGenreById(long genreId) throws Throwable;
    public GenreDTO addGenre(SaveGenreDTO genre);
    /*public Genre updateGenre(Genre genre);*/
    public void deleteGenre(long genreId) throws Exception;

}
