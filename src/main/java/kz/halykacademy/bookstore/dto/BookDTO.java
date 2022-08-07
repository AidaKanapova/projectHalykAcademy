package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Publisher;
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
    public Set<GenreNameDTO> genreNameDTOS;
    private int price;
    private Set<AuthorNameDTO> authors;
    private String publisher;

    private int page_count;
    private LocalDate release_year;
    private boolean deleted;
}