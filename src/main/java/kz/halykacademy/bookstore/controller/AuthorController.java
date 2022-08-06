package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.AuthorNameDTO;
import kz.halykacademy.bookstore.dto.SaveAuthorDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController  {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;


    @GetMapping("/allAuthors")
    public List<AuthorDTO> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/getById/{id}")
    public AuthorDTO getAuthorById(@PathVariable("id") long authorId) throws Throwable {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("/addAuthor")
    public AuthorDTO addAuthor(@RequestBody SaveAuthorDTO author) {
        return  authorService.addAuthor(author);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public Author deleteAuthor(@PathVariable("id") long authorId) {
        Author author = null;
        try {
            author = authorService.deleteAuthor(authorId);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return author;
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Author>> findByName(@PathVariable("name") String name) {
        List<Author> authors = null;
        try {
            authors = authorService.findByName(name);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);

    }

    @PutMapping("/{authorId}/books/{bookId}")
    Author author(@PathVariable long authorId, @PathVariable long bookId){
        Author author = authorRepository.findById(authorId).get();
        Books book = bookRepository.findById(bookId).get();
        return authorRepository.save(author);
    }

    @GetMapping("/getGenreList/{id}")
    public ResponseEntity<List<String>>  getGenreList(@PathVariable("id") long authorId){

        List<Long> genreId = authorRepository.genreList(authorId);
        List<String> genreList = new ArrayList<>();
        for (Long id:genreId) {
            genreList.add(genreRepository.findById(id).get().getGenre_name());
        }




        return new ResponseEntity<List<String>>(genreList,HttpStatus.OK);


    }



}

