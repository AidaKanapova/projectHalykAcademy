package kz.halykacademy.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dto.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="author")

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
    private List<Genre> genres = new ArrayList<>();


    /*@Query(value = "select g from Author_book a join Book_genre g on a.book_id = g.book_id where a.author_id = ?1 ")
    List<GenreNameDTO> findByName(String name) {

        return
    }*/


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

   /* public AuthorGenreDTO AuthorGenreToDTO(){
        Set<GenreNameDTO> genreNameDTOS = Set.of();
        if(this.genres != null)
            genreNameDTOS = this.genres.stream().map(Genre::toGenreDTO).collect(Collectors.toSet());


        return new AuthorGenreDTO(
                this.full_name,
                genre
        );

    }
*/

   /* @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;*/

   public Author() {
        super();
    }
    public Author(long authorId, String fullName, LocalDate dateOfBirth, Set<Books> books, List<Genre> genres) {
        this.authorId = authorId;
        this.full_name = fullName;
        this.date_of_birth = dateOfBirth;
        this.books = books;
        this.genres= genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }


    public List<Genre> getGenres() {
        return genres;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getFull_name() {
        return full_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public Set<Books> getBooks() {
        return books;
    }


    public void addBook(Books book){
        books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Books book){
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

}


