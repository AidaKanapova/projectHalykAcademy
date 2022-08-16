package kz.halykacademy.bookstore.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UpdateAuthorDTO {
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
}
