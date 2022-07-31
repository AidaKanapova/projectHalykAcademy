package kz.halykacademy.bookstore.dto;

import java.util.Set;

public class SaveGenreDTO {

    private long genre_id;
    private String genre_name;

    public SaveGenreDTO(){super();}

    public SaveGenreDTO(long genre_id, String genre_name) {
        this.genre_id = genre_id;
        this.genre_name = genre_name;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
