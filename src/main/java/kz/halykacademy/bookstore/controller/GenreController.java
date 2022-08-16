package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.GenreDTO;

import kz.halykacademy.bookstore.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;


    @GetMapping("/allGenres")
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenre();

    }

    @GetMapping("/getById/{id}")
    public GenreDTO getGenreById(@PathVariable("id") Long genreId) throws Throwable {
        return genreService.getGenreById(genreId);

    }

    @PostMapping("/addGenre")
    public GenreDTO addGenre(@RequestBody GenreDTO genre) {
        return genreService.addGenre(genre);

    }

    @PutMapping("/updateGenre")
    public GenreDTO updateBook(@RequestBody GenreDTO genreDTO) throws Throwable
                               {
        return  genreService.updateGenre(genreDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGenre(@PathVariable("id") Long genreId) throws Throwable {
        genreService.deleteGenre(genreId);

    }

    @GetMapping("/getByName/{name}")
    public GenreDTO findByName(@PathVariable("name") String name){
        return  genreService.findByName(name);
    }
}



