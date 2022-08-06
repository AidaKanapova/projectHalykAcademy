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
public class AuthorGenreListDTO {

    private long authorId;
    private String full_name;
    private List<GenreNameDTO> genreList;

}
