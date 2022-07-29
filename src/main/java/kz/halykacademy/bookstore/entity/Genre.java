package kz.halykacademy.bookstore.entity;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private long genre_id;

    @Column(name = "genre_name")
    private String genre_name;

    @ManyToMany(mappedBy = "genres")
    private List<Books> books;



    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public Genre(){super();}

    public Genre(String genre_name) {
        this.genre_name = genre_name;
    }
}
