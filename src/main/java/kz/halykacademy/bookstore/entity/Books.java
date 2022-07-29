package kz.halykacademy.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.AuthorNameDTO;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.BookNameDTO;
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

public class Books {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Column(name = "title")
    private  String title;

    @Column(name = "price")
    private  int price;


    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "page_count")
    private  int page_count;

    @Column(name = "release_year")
    private LocalDate release_year;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

   /* @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;*/

    public BookNameDTO toBookDTO(){
        return new BookNameDTO(
                this.bookId,
                this.title
        );
    }

    public BookDTO toDTO(){

        Set<AuthorNameDTO> authors = Set.of();
        if(this.authors != null)
            authors = this.authors.stream().map(Author::toAuthorDTO).collect(Collectors.toSet());

        return new BookDTO(
                this.bookId,
                this.title,
                this.price,
                authors,
                this.publisher.getName(),
                this.page_count,
                this.release_year

        );
    }



    public Books() {
        super();
    }

    public Books(long bookId, String title, int price, Set<Author> authors, Publisher publisher, int page_count, LocalDate release_year) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.page_count = page_count;
        this.release_year = release_year;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public void setRelease_year(LocalDate release_year) {
        this.release_year = release_year;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getPage_count() {
        return page_count;
    }

    public LocalDate getRelease_year() {
        return release_year;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", publisher=" + publisher +
                ", page_count=" + page_count +
                ", release_year=" + release_year +
                ", genres=" + genres +
                '}';
    }
}
