package kz.halykacademy.bookstore.dto;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long bookId;
    private String title;
    public Set<GenreNameDTO> genres;
    private int price;
    private Set<AuthorNameDTO> authors;
    private String publisher;
    private int pageCount;
    private LocalDate releaseYear;
    private boolean deleted;

}