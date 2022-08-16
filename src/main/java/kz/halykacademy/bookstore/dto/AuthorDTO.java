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

    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Set<BookNameDTO> books;
    private List<String> genreList;
}


