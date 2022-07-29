package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.dto.PublisherDTO;

import java.util.List;

public interface PublisherProvider {
    List<PublisherDTO> getAll();
}
