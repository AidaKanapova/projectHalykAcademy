package kz.halykacademy.bookstore.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBookDTO {

    private Long id;
    private String title;
    private int price;
    private int pageCount;
    private Long publisherId;
    private LocalDate releaseYear;

}
