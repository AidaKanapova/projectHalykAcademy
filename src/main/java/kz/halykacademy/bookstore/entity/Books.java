package kz.halykacademy.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "update book set deleted = true where book_id = ?")
@Where(clause = "deleted = false")

public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @Column(name = "price")
    private int price;



    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "page_count")
    private int page_count;

    @Column(name = "release_year")
    private LocalDate release_year;

    @Column(name = "deleted")
    private boolean deleted;

    public BookNameDTO toBookDTO() {
        return new BookNameDTO(
                this.bookId,
                this.title
        );
    }

   /* public BookDTO toDTO() {

        Set<AuthorNameDTO> authors = Set.of();
        if (this.authors != null)
            authors = this.authors.stream().map(Author::toAuthorDTO).collect(Collectors.toSet());

        Set<GenreNameDTO> genreNameDTOS = Set.of();
        if (this.genres != null)
            genreNameDTOS = this.genres.stream().map(Genre::toGenreDTO).collect(Collectors.toSet());


        return new BookDTO(
                this.bookId,
                this.title,
                genreNameDTOS,
                this.price,
                authors,
                this.publisher.getName(),
                this.page_count,
                this.release_year,
                this.deleted

        );
    }*/
}

