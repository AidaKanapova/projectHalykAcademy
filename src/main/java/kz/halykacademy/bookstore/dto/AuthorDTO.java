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
public class AuthorDTO {

    private long authorId;
    private String full_name;
    private LocalDate date_of_birth;
    private Set<BookNameDTO> books;
    private List<String> genreList;
}


