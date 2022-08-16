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

    private String fullName;
    private List<String> genreList;

}
