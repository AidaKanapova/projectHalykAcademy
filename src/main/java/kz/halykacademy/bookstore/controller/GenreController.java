package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.GenreDTO;

import kz.halykacademy.bookstore.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;


    @GetMapping("/allGenres")
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenre();

    }

    @GetMapping("/getById/{id}")
    public GenreDTO getGenreById(@PathVariable("id") long genreId) throws Throwable {
        return genreService.getGenreById(genreId);

    }

    @PostMapping("/addGenre")
    public GenreDTO addGenre(@RequestBody GenreDTO genre) {
        return genreService.addGenre(genre);

    }

    @PutMapping("/updateGenre/{id}")
    public GenreDTO updateBook(@RequestBody GenreDTO genreDTO,
                              @PathVariable long id)  {
        return  genreService.updateGenre(genreDTO,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGenre(@PathVariable("id") long genreId) throws Exception {
        genreService.deleteGenre(genreId);

    }

    @GetMapping("/getByName/{name}")
    public GenreDTO findByName(@PathVariable("name") String name){
        return  genreService.findBYName(name);
    }
}



