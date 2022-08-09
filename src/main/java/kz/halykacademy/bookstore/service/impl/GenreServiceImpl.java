package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.dto.SaveGenreDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class GenreServiceImpl implements GenreService {
    private  final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDTO> getAllGenre() {
        return genreRepository.findAll()
                .stream()
                .map(Genre::toDTO)
                .toList();
    }

    @Override
    public GenreDTO getGenreById(long genreId) throws Throwable {
        return genreRepository.findById(genreId)
                .map(Genre::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Genre %s not found".formatted(genreId)));
    }

    @Override
    public GenreDTO addGenre(GenreDTO genre) {
        Genre saved = genreRepository.save(
                new Genre(
                        genre.getGenre_id(),
                        genre.getGenre_name()
                )
        );
        return  saved.toDTO();
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO, long id) {
        Genre genre = genreRepository.findById(id).get();
                new Genre(
                        genre.getGenre_id(),
                        genreDTO.getGenre_name()
        );
                return genre.toDTO();
    }


    @Override
    public void deleteGenre(long genreId) throws Exception {
        genreRepository.deleteById(genreId);

    }
}
