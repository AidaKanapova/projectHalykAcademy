package kz.halykacademy.bookstore.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveBookDTO {
    private long bookId;
    private String title;
    private int price;
    private long publisherId;
    private int page_count;
    private LocalDate release_year;
    private boolean deleted;


}