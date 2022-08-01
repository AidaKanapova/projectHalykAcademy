package kz.halykacademy.bookstore.entity;



import kz.halykacademy.bookstore.dto.*;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private long genre_id;

    @Column(name = "genre_name")
    private String genre_name;

    @ManyToMany(mappedBy = "genres")
    private Set<Books> books;

    public GenreDTO toDTO(){

        Set<BookNameDTO> books = Set.of();
        if(this.books != null)
            books = this.books.stream().map(Books::toBookDTO).collect(Collectors.toSet());

        return  new GenreDTO(
                this.genre_id,
                this.getGenre_name(),
                books
        );
    }

    public GenreNameDTO toGenreDTO(){
        return new GenreNameDTO(


               this.getGenre_name()
        );
    }


    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public Set<Books> getBooks() {
        return books;
    }

    public Genre(){super();}

    public Genre(long genre_id, String genre_name, Set<Books> books) {
        this.genre_id = genre_id;
        this.genre_name = genre_name;
        this.books = books;
    }
}
