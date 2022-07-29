package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/publishers")
public class PublisherController  {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

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

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Publisher>> findByName(@PathVariable("name") String name) {
        List<Publisher> publishers = null;
        try {
            publishers = publisherService.findByName(name);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<List<Publisher>>(publishers,HttpStatus.OK);
    }
}
