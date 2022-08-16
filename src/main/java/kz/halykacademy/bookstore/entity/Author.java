package kz.halykacademy.bookstore.entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name="author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "update author set deleted = true where author_id = ?")
@Where(clause = "deleted = false")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long authorId;

    private  String fullName;
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "authors")
    private Set<Books> books = new HashSet<>();

    private boolean deleted;
}


