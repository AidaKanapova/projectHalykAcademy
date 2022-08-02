package kz.halykacademy.bookstore.dto;

import java.time.LocalDate;
import java.util.List;

public class SaveBookDTO {
    private long bookId;
    private String title;
    private int price;
    private long publisherId;
    private int page_count;
    private LocalDate release_year;
    private boolean deleted;


    public SaveBookDTO() {
        super();
    }

    public SaveBookDTO(long bookId, String title, int price, long publisherId, int page_count, LocalDate release_year, boolean deleted) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.publisherId = publisherId;
        this.page_count = page_count;
        this.release_year = release_year;
        this.deleted = deleted;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public void setRelease_year(LocalDate release_year) {
        this.release_year = release_year;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public int getPage_count() {
        return page_count;
    }

    public LocalDate getRelease_year() {
        return release_year;
    }

    public boolean isDeleted() {
        return deleted;
    }
}

