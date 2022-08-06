package kz.halykacademy.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private  long authorId;

    @Column(name = "full_name")
    private  String full_name;

    @Column(name = "date_of_birth")
    private LocalDate date_of_birth;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Books> books = new HashSet<>();


    @OneToMany
    private  List<Genre> genreList;



    public AuthorNameDTO toAuthorDTO(){


        return new AuthorNameDTO(

                this.authorId,
                this.full_name
        );
    }

    public AuthorDTO toDTO(){

        Set<BookNameDTO> books = Set.of();
        if(this.books != null)
            books = this.books.stream().map(Books::toBookDTO).collect(Collectors.toSet());
        return new AuthorDTO(
                this.authorId,
                this.full_name,
                this.date_of_birth,
                books
        );
    }

    public AuthorGenreListDTO authorGenreDTO(){

        List<GenreNameDTO> genres = List.of();
        if(this.genreList != null)
            genres = this.genreList.stream().map(Genre::toGenreDTO).collect(Collectors.toList());
        return new AuthorGenreListDTO(
                this.authorId,
                this.full_name,
                genres
        );
    }


}


