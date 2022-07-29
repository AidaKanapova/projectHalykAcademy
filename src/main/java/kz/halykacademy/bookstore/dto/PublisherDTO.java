package kz.halykacademy.bookstore.dto;

public class PublisherDTO {

    private  long publisherId;
    private  String name;

    public PublisherDTO(){super();}

    public PublisherDTO(long publisherId, String name) {
        this.publisherId=publisherId;
        this.name = name;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }
}
