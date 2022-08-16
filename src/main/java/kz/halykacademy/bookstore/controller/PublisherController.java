package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.PublisherDTO;

import kz.halykacademy.bookstore.dto.SavePublisherDTO;
import kz.halykacademy.bookstore.service.PublisherService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publishers")
public class PublisherController  {

    private final PublisherService publisherService;

    @GetMapping("/allPublishers")
    public List<PublisherDTO> findAll(){
    return publisherService.getAllPublishers();
    }

    @GetMapping("/getById/{id}")
    public PublisherDTO getPublisherById(@PathVariable("id") Long publisherId) throws Throwable {
        return publisherService.getPublisherById(publisherId);
    }

    @PostMapping("/addPublisher")
    public PublisherDTO addPublisher(@RequestBody SavePublisherDTO publisher) {
        return  publisherService.addPublisher(publisher);
    }

    @DeleteMapping("/deletePublisher/{id}")
    public void deletePublisher(@PathVariable("id") Long publisherId) throws Throwable {
        publisherService.deletePublisher(publisherId);

    }

    @PutMapping("/updatePublisher")
    public PublisherDTO updateBook(@RequestBody PublisherDTO publisherDTO) throws Throwable {
        return  publisherService.updatePublisher(publisherDTO);
    }

    @GetMapping("/findByName/{name}")
    public List<PublisherDTO> findByName(@PathVariable("name") String name) {
        return publisherService.findByName(name);
    }
}
