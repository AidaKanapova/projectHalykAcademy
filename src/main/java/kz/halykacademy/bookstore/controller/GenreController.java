package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.dto.SaveGenreDTO;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping("/allGenres")
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenre();

    }

    @GetMapping("/getById/{id}")
    public GenreDTO getGenreById(@PathVariable("id") long genreId) throws Throwable {
        return genreService.getGenreById(genreId);

    }

    @PostMapping("/addGenre")
    public GenreDTO addGenre(@RequestBody SaveGenreDTO genre) {
        return genreService.addGenre(genre);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteGenre(@PathVariable("id") long genreId) throws Exception {
        genreService.deleteGenre(genreId);

    }
}



