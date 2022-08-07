package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(Publisher::toDTO)
                .toList();    }

    @Override
    public PublisherDTO getPublisherById(long publisherId) throws Throwable {
        return publisherRepository.findById(publisherId)
                .map(Publisher::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Publisher %s not found".formatted(publisherId)));
    }

    @Override
    public PublisherDTO addPublisher(PublisherDTO publisher) {
        Publisher saved = publisherRepository.save(
                new Publisher(
                        publisher.getPublisherId(),
                        publisher.getName(),
                        null
                )
        );
        return  saved.toDTO();
    }

    @Override
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO, long id) {
        Publisher publisher = publisherRepository.findById(id).get();
        Publisher updatePublisher = publisherRepository.save(
                new Publisher(
                        publisher.getPublisherId(),
                        publisherDTO.getName(),
                        publisher.getBooks()
                )
        );
        return updatePublisher.toDTO();
    }

    /* @Override
     public Publisher updatePublisher(Publisher publisher) {
         return publisherRepository.save(publisher);
     }
 */
    @Override
    public void deletePublisher(long publisherId) throws Exception {
        publisherRepository.deleteById(publisherId);

    }

    @Override
    public List<PublisherDTO> findByName(String name) {
        return  publisherRepository.findByName(name).stream().map(Publisher::toDTO).toList();
    }
}
