package kz.halykacademy.bookstore.dto;

import java.util.Set;

public class GenreNameDTO {

    private String genre_name;

    public GenreNameDTO(){super();}
    public GenreNameDTO(String genre_name) {
        this.genre_name = genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public String getGenre_name() {
        return genre_name;
    }
}
