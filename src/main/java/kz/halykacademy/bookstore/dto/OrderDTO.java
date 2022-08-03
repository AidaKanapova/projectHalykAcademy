package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTO {

    private long order_id;
    private String user;
    private List<BookNameDTO> books;
    private int sum;
    private String status;


    public void getBooksSum() {

    }

    public OrderDTO() {
        super();
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setBooks(List<BookNameDTO> books) {
        this.books = books;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOrder_id() {
        return order_id;
    }

    public String getUser() {
        return user;
    }

    public List<BookNameDTO> getBooks() {
        return books;
    }

    public int getSum() {
        return sum;
    }

    public String getStatus() {
        return status;
    }

    public OrderDTO(long order_id, String user, List<BookNameDTO> books, int sum, String status) {
        this.order_id = order_id;
        this.user = user;
        this.books = books;
        this.sum = sum;
        this.status = status;
    }
}