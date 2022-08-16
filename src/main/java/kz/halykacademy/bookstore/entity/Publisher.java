package kz.halykacademy.bookstore.entity;


import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.PublisherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publisher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "update publisher set deleted = true where publisher_id = ?")
@Where(clause = "deleted = false")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private  Long id;

    @Column(name = "name")
    private  String name;


    @OneToMany(mappedBy = "publisher")
    private List<Books> books = new ArrayList<>();

    @Column(name = "deleted")
    private boolean deleted;

}
