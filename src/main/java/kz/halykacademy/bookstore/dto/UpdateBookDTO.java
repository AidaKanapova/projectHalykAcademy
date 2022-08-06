package kz.halykacademy.bookstore.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBookDTO {

    private String title;
    private int price;
    private int page_count;
    private LocalDate release_year;

}
