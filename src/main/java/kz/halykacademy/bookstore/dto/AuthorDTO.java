package kz.halykacademy.bookstore.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AuthorDTO {

    private  long authorId;
    private  String full_name;
    private LocalDate date_of_birth;
    private Set<BookNameDTO> books;


    public  AuthorDTO(){
        super();
    }

    public AuthorDTO(long authorId, String full_name, LocalDate date_of_birth, Set<BookNameDTO> books) {
        this.authorId = authorId;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.books = books;
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


    public void setBooks(Set<BookNameDTO> books) {
        this.books = books;
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

    public Set<BookNameDTO> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Author {");
        sb.append("full name = ").append(full_name);
        sb.append(", date of birth = ").append(date_of_birth);
        /*sb.append(", books = ").append(books);*/
        return sb.toString();
    }
}
