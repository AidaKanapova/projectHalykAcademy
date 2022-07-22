package kz.halykacademy.bookstore.dto;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private  int id;
    private  double price;
    private List<Author> authors;
    private List<Publisher> publishers;
    private String title;
    private  int page_count;
    private LocalDate release_year;

    public Book(double price, List<Author> authors, List<Publisher> publishers, String title, int page_count, LocalDate release_year) {
        this.price = price;
        this.authors = authors;
        this.publishers = publishers;
        this.title = title;
        this.page_count = page_count;
        this.release_year = release_year;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public void setRelease_year(LocalDate release_year) {
        this.release_year = release_year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getPage_count() {
        return page_count;
    }

    public LocalDate getRelease_year() {
        return release_year;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book {");
        sb.append("title = ").append(title);
        sb.append(", price = ").append(price);
        sb.append(", author = ").append(authors);
        sb.append(", publisher = ").append(publishers);
        sb.append(", page count = ").append(page_count);
        sb.append(", year = ").append(release_year);
        return sb.toString();
    }
}
