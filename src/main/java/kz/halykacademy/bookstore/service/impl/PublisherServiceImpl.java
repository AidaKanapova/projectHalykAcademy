package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.PublisherMapper;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private  final PublisherMapper publisherMapper;

    @Override
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toDTO)
                .toList();    }

    @Override
    public PublisherDTO getPublisherById(Long publisherId) throws Throwable {
        return publisherRepository.findById(publisherId)
                .map(publisherMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Publisher with id %s not found".formatted(publisherId)));
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
        return  publisherMapper.toDTO(saved);
    }

    @Override
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO) throws Throwable {
        Publisher publisher = publisherRepository.findById(publisherDTO.getPublisherId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("Publisher with id %s not found".formatted(publisherDTO.getPublisherId())));
        Publisher updatePublisher = publisherRepository.save(
                new Publisher(
                        publisherDTO.getPublisherId(),
                        publisherDTO.getName(),
                        publisher.getBooks()
                )
        );
        return publisherMapper.toDTO(updatePublisher);
    }

    @Override
    public void deletePublisher(Long publisherId) throws Throwable {
        publisherRepository.findById(publisherId).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("Publisher with id %s not found".formatted(publisherId)));
        publisherRepository.deleteById(publisherId);

    }

    @Override
    public List<PublisherDTO> findByName(String name) {
        return  publisherRepository.findByName(name).stream().map(publisherMapper::toDTO).toList();
    }
}
