package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.dto.Publisher;

import java.util.List;

public interface PublisherProvider {
    List<Publisher> getAll();
}
