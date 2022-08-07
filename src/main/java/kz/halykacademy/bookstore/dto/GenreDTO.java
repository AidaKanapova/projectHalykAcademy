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

    private long genre_id;
    private String genre_name;

}
