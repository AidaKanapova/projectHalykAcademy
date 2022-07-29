package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Genre;

import java.util.List;

public interface GenreService {

    public List<Genre> getAllGenre();
    public  Genre getGenreById(long genreId);
    public Genre addGenre(Genre genre);
    public Genre updateGenre(Genre genre);
    public Genre deleteGenre(long genreId) throws Exception;

}
