package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Publisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class BookDTO {
    private long bookId;
    private String title;
    private  int price;
    private Set<AuthorNameDTO> authors;
    private String publisher;
    private  int page_count;
    private LocalDate release_year;

    public BookDTO(){super();}


    public BookDTO(long bookId, String title, int price, Set<AuthorNameDTO> authors, String publisher, int page_count, LocalDate release_year) {
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

    public void setAuthors(Set<AuthorNameDTO> authors) {
        this.authors = authors;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public void setRelease_year(LocalDate release_year) {
        this.release_year = release_year;
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

    public Set<AuthorNameDTO> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
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
        sb.append(", publisher = ").append(publisher);
        sb.append(", page count = ").append(page_count);
        sb.append(", year = ").append(release_year);
        return sb.toString();
    }
}
