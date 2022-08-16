package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {
    public PublisherDTO toDTO(Publisher publisher){
        return  new PublisherDTO(
                publisher.getId(),
                publisher.getName()
        );
    }
}
