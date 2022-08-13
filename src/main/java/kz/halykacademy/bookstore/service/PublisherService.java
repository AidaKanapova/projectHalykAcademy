package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Publisher;

import java.util.List;

public interface PublisherService {
    public List<PublisherDTO> getAllPublishers();
    public PublisherDTO getPublisherById(Long publisherId) throws Throwable;
    public PublisherDTO addPublisher(PublisherDTO publisher);
    public PublisherDTO updatePublisher(PublisherDTO publisher) throws Throwable;

    public void deletePublisher(Long publisherId) throws Throwable;

    public  List<PublisherDTO> findByName(String name);
}
