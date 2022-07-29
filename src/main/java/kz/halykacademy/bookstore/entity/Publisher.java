package kz.halykacademy.bookstore.entity;


import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.PublisherDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publisher")

public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private  long publisherId;

    @Column(name = "name")
    private  String name;


    @OneToMany(mappedBy = "publisher")
    private List<Books> books = new ArrayList<>();

  /*  @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;*/


    public PublisherDTO toDTO(){
        return  new PublisherDTO(
                this.publisherId,
                this.name
        );
    }

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

/*
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
*/

    public long getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }

    public List<Books> getBooks() {
        return books;
    }

/*
    public boolean isDeleted() {
        return deleted;
    }
*/
}
