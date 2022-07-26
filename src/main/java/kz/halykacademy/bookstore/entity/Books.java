package kz.halykacademy.bookstore.entity;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
/*
@SQLDelete(sql="UPDATE book SET deleted = true WHERE book_id = ?")
*/

public class Books {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "title")
    private  String title;

    @Column(name = "price")
    private  int price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "author_book",
                joinColumns = @JoinColumn(name="book_id"),
                inverseJoinColumns = @JoinColumn(name = "author_id"),
                foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
                inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "page_count")
    private  int page_count;

    @Column(name = "release_year")
    private LocalDate release_year;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;




    public Books() {
        super();
    }

    public Books(int bookId, String title, int price, List<Author> authors, Publisher publisher, int page_count, LocalDate release_year) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.page_count = page_count;
        this.release_year = release_year;
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

    public void setAuthor(List<Author> authors) {
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

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public List<Author> getAuthor() {
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

    public boolean isDeleted() {
        return deleted;
    }



    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", author='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", page_count=" + page_count +
                ", release_year=" + release_year + '\''+
                ", deleted=" + deleted +

                '}';
    }
}
