package kz.halykacademy.bookstore.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Books {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "title")
    private  String title;

    @Column(name = "price")
    private  int price;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private  String publisher;

    @Column(name = "page_count")
    private  int page_count;

    @Column(name = "release_year")
    private LocalDate release_year;


    public Books() {
        super();
    }

    public Books(int bookId, String title, int price, String author, String publisher, int page_count, LocalDate release_year) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.page_count = page_count;
        this.release_year = release_year;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
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

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", page_count=" + page_count +
                ", release_year=" + release_year +
                '}';
    }
}
