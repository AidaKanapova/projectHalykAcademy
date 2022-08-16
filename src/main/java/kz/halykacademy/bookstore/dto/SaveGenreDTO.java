package kz.halykacademy.bookstore.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveGenreDTO {

    private Long genre_id;
    private String genre_name;


}
