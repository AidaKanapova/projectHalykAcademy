package kz.halykacademy.bookstore.dto;

import java.time.LocalDate;
import java.util.List;

public class Author {

    private int id;
    private  String full_name;
    private LocalDate date_of_birth;
    private List<BookDTO> books;

    public Author(String full_name, LocalDate date_of_birth, List<BookDTO> books) {
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.books = books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public List<BookDTO> getBooks() {
        return books;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Author {");
        sb.append("full name = ").append(full_name);
        sb.append(", date of birth = ").append(date_of_birth);
        sb.append(", books = ").append(books);
        return sb.toString();
    }
}
