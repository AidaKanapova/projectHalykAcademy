package kz.halykacademy.bookstore.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherDTO {

    private  long publisherId;
    private  String name;

}
