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
    private Long bookId;
    private String title;
    private int price;
    private List<Long> authorList;
    private List<Long> genreList;
    private int pageCount;
    private Long publisherId;
    private LocalDate releaseYear;
}