package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private  final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    public Genre addGenre(Genre genre) {
            return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre deleteGenre(long genreId) throws Exception {
        Genre deleteGenre = null;
        try {
            deleteGenre = genreRepository.findById(genreId).orElse(null);
            if (deleteGenre == null) {
                throw new Exception("book not available");
            } else {
                genreRepository.deleteById(genreId);
            }
        } catch (Exception ex) {
            throw ex;
        }

        return deleteGenre;
    }
}
