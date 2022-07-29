package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping("/allGenres")
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres  = null;
        try {
            genres = genreService.getAllGenre();
        } catch (Exception ex) {
            ex.getMessage();

        }
        return GenreMapper.INSTANCE.toListDTO(genres);
    }

    @GetMapping("/getById/{id}")
    public GenreDTO getGenreById(@PathVariable("id") long genreId) {
        Genre genre = null;
        try {
            genre = genreService.getGenreById(genreId);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return GenreMapper.INSTANCE.toDTO(genre);
    }

    @PostMapping("/addGenre")
    public GenreDTO addGenre(@RequestBody Genre genre) {
        Genre genre1 = null;
        try {
            genre1 = genreService.addGenre(genre);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return GenreMapper.INSTANCE.toDTO(genre1);
    }

    @DeleteMapping("/delete/{id}")
    public GenreDTO deleteGenre(@PathVariable("id") long genreId) {
        Genre genre = null;
        try {
            genre = genreService.deleteGenre(genreId);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return GenreMapper.INSTANCE.toDTO(genre);
    }

}
*/
