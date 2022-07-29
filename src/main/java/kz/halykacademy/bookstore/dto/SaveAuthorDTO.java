package kz.halykacademy.bookstore.dto;

import java.time.LocalDate;
import java.util.List;

public class SaveAuthorDTO {

    private long authorId;
    private String full_name;
    private LocalDate date_of_birth;

    public SaveAuthorDTO(){super();}

    public SaveAuthorDTO(long authorId, String full_name, LocalDate date_of_birth) {
        this.authorId = authorId;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getFull_name() {
        return full_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }
}
