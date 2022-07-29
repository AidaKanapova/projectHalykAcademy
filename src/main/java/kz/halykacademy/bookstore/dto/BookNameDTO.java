package kz.halykacademy.bookstore.dto;

public class BookNameDTO {
    private long bookId;

    private String title;

    public BookNameDTO(){super();}

    public BookNameDTO(long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
