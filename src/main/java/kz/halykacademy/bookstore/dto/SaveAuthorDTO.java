package kz.halykacademy.bookstore.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveAuthorDTO {

    private long authorId;
    private String full_name;
    private LocalDate date_of_birth;


}
