package kz.halykacademy.bookstore.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookGenreDTO {
    private Long bookId;
    private String title;
    public Set<GenreNameDTO> genreNameDTOS;
}
