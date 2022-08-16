package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.dto.SavePublisherDTO;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getAllPublishers();
    PublisherDTO getPublisherById(Long publisherId) throws Throwable;
    PublisherDTO addPublisher(SavePublisherDTO publisher);
    PublisherDTO updatePublisher(PublisherDTO publisher) throws Throwable;

    void deletePublisher(Long publisherId) throws Throwable;

    List<PublisherDTO> findByName(String name);
}
