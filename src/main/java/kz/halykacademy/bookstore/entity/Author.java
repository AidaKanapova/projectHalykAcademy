package kz.halykacademy.bookstore.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="author")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private  long authorId;

    @Column(name = "fullName")
    private  String fullName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private List<Books> books;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    public Author() {
        super();
    }
    public Author(long authorId, String fullName, LocalDate dateOfBirth, List<Books> books) {
        super();
        this.authorId = authorId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Books> getBooks() {
        return books;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
