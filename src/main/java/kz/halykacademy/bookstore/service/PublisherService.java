package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Publisher;

import java.util.List;

public interface PublisherService {
    public List<PublisherDTO> getAllPublishers();
    public PublisherDTO getPublisherById(long publisherId) throws Throwable;
    public PublisherDTO addPublisher(PublisherDTO publisher);
/*
    public Publisher updatePublisher(Publisher publisher);
*/
    public void deletePublisher(long publisherId) throws Exception;

    public  List<Publisher> findByName(String name);
}
