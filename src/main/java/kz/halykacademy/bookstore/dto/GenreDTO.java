package kz.halykacademy.bookstore.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreDTO {

    private Long id;
    private String genreName;

}
