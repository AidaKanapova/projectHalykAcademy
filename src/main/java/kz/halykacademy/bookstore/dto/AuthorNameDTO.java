package kz.halykacademy.bookstore.dto;

import java.time.LocalDate;
import java.util.Set;

public class AuthorNameDTO {

    private  long authorId;
    private  String full_name;


    public AuthorNameDTO(){super();}
    public AuthorNameDTO(long authorId, String full_name) {
        this.authorId = authorId;
        this.full_name = full_name;

    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


    public long getAuthorId() {
        return authorId;
    }

    public String getFull_name() {
        return full_name;
    }


}
