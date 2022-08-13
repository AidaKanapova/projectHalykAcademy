package kz.halykacademy.bookstore.service.impl;
import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.GenreMapper;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    @Override
    public List<GenreDTO> getAllGenre() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public GenreDTO getGenreById(Long genreId) throws Throwable {
        return genreRepository.findById(genreId)
                .map(genreMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("genre with id %s not found".formatted(genreId)));
    }

    @Override
    public GenreDTO addGenre(GenreDTO genre) {
        Genre saveGenre = genreRepository.saveAndFlush(
                new Genre(
                        genre.getGenre_id(),
                        genre.getGenre_name()
                )
        );
        return genreMapper.toDTO(saveGenre);
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO) throws Throwable {
        genreRepository.findById(genreDTO.getGenre_id()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("genre with id  %s not found".formatted(genreDTO.getGenre_id())));
        Genre updateGenre = genreRepository.save(
                new Genre(
                        genreDTO.getGenre_id(),
                        genreDTO.getGenre_name()
                )
        );
        return genreMapper.toDTO(updateGenre);
    }


    @Override
    public void deleteGenre(Long genreId) throws Throwable {
        genreRepository.findById(genreId).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("genre with id  %s not found".formatted(genreId)));
        genreRepository.deleteById(genreId);

    }

    @Override
    public GenreDTO findByName(String name) {
        return genreMapper.toDTO(genreRepository.findByName(name));
    }
}
