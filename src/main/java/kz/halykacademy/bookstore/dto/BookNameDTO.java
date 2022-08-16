package kz.halykacademy.bookstore.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookNameDTO {
    private Long bookId;
    private String title;

}
