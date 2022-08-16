package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.*;

import kz.halykacademy.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController  {

    private final AuthorService authorService;


    @GetMapping("/allAuthors")
    public List<AuthorDTO> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/getById/{id}")
    public AuthorDTO getAuthorById(@PathVariable("id") Long authorId) throws Throwable {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("/addAuthor")
    public AuthorDTO addAuthor(@RequestBody SaveAuthorDTO author) {
        return  authorService.addAuthor(author);
    }

    @PutMapping("/updateAuthor")
    public AuthorDTO updateAuthor(@RequestBody UpdateAuthorDTO authorDTO) throws Throwable {
        return authorService.updateAuthor(authorDTO);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) throws Throwable {
        authorService.deleteAuthor(authorId);
    }

    @GetMapping("/findByName/{name}")
    public List<AuthorDTO> findByName(@PathVariable("name") String name) {
        return authorService.findByName(name);
    }

    @GetMapping("/getByGenres/{genre}")
    public  List<AuthorGenreListDTO> findAuthorsByGenreList(@PathVariable("genre") List<String> genres){
        return authorService.findAuthorsByGenreList(genres);
    }



}

