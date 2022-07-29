package kz.halykacademy.bookstore.dto;

import java.util.List;

public class GenreDTO {

    private long genre_id;
    private String genre_name;
    private List<AuthorDTO> authorDTOS;
    private List<BookDTO> bookDTOS;

    public GenreDTO(){super();}

    public GenreDTO(String genre_name, List<AuthorDTO> authorDTOS, List<BookDTO> bookDTOS) {
        this.genre_name = genre_name;
        this.authorDTOS = authorDTOS;
        this.bookDTOS = bookDTOS;
    }

    public List<BookDTO> getBookDTOS() {
        return bookDTOS;
    }

    public List<AuthorDTO> getAuthorDTOS() {
        return authorDTOS;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setAuthorDTOS(List<AuthorDTO> authorDTOS) {
        this.authorDTOS = authorDTOS;
    }

    public void setBookDTOS(List<BookDTO> bookDTOS) {
        this.bookDTOS = bookDTOS;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
