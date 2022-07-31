package kz.halykacademy.bookstore.dto;

import java.util.List;
import java.util.Set;

public class GenreDTO {

    private long genre_id;
    private String genre_name;
    private Set<BookNameDTO> bookDTOS;

    public GenreDTO() {
        super();
    }

    public GenreDTO(long genre_id, String genre_name, Set<BookNameDTO> bookDTOS) {
        this.genre_id = genre_id;
        this.genre_name = genre_name;
        this.bookDTOS = bookDTOS;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public void setBookDTOS(Set<BookNameDTO> bookDTOS) {
        this.bookDTOS = bookDTOS;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public Set<BookNameDTO> getBookDTOS() {
        return bookDTOS;
    }
}
