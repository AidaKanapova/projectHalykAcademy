package kz.halykacademy.bookstore.entity;



import kz.halykacademy.bookstore.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private long genre_id;

    @Column(name = "genre_name")
    private String genre_name;

   /* @ManyToMany(mappedBy = "genres")
    private Set<Books> books;*/

    public GenreDTO toDTO(){
        return  new GenreDTO(
                this.genre_id,
                this.getGenre_name()
        );
    }

    public GenreNameDTO toGenreDTO(){
        return new GenreNameDTO(

               this.getGenre_name()
        );
    }
}
