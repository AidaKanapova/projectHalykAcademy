package kz.halykacademy.bookstore.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavePublisherDTO {
    private Long id;
    private  String name;
}
