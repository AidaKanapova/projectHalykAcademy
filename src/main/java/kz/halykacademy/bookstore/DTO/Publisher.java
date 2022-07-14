package kz.halykacademy.bookstore.DTO;

import java.util.List;

public class Publisher {

    private  int id;
    private  String name;
    private List<Book> books;

    public Publisher(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }
}
