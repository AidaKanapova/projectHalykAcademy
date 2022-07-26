package kz.halykacademy.bookstore.entity;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.List;

@Entity
@Table(name = "publisher")

public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private  long publisherId;

    @Column(name = "name")
    private  String name;

    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.EAGER,
    mappedBy = "publisher")
    @Column(name = "books")
    private List<Books> books;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    public Publisher() {
        super();
    }

    public Publisher(long publisherId, String name, List<Books> books) {
        super();
        this.publisherId = publisherId;
        this.name = name;
        this.books = books;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }

    public List<Books> getBooks() {
        return books;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
