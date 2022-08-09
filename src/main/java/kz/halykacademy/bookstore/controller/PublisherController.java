package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.PublisherDTO;

import kz.halykacademy.bookstore.service.PublisherService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController  {

    private final PublisherService publisherService;

    @GetMapping("/allPublishers")
    public List<PublisherDTO> findAll(){
    return publisherService.getAllPublishers();
    }

    @GetMapping("/getById/{id}")
    public PublisherDTO getPublisherById(@PathVariable("id") long publisherId) throws Throwable {
        return publisherService.getPublisherById(publisherId);
    }

    @PostMapping("/addPublisher")
    public PublisherDTO addPublisher(@RequestBody PublisherDTO publisher) {
        return  publisherService.addPublisher(publisher);
    }

    @DeleteMapping("/deletePublisher/{id}")
    public void deletePublisher(@PathVariable("id") long publisherId) throws Exception {
        publisherService.deletePublisher(publisherId);

    }

    @PutMapping("/updatePublisher/{id}")
    public PublisherDTO updateBook(@RequestBody PublisherDTO publisherDTO,
                               @PathVariable long id)  {
        return  publisherService.updatePublisher(publisherDTO,id);
    }

    @GetMapping("/findByName/{name}")
    public List<PublisherDTO> findByName(@PathVariable("name") String name) {
        return publisherService.findByName(name);
    }
}
