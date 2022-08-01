package kz.halykacademy.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.dto.BookNameDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Books> books = new ArrayList<>();

    private int sum;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    private LocalDate created;



    public OrderDTO orderDTO() {
        List<BookNameDTO> books = List.of();
        if (this.books != null)
            books = this.books.stream().map(Books::toBookDTO).collect(Collectors.toList());

        List<Integer> sum = List.of();
        if(this.books != null)
        sum = this.books.stream().map(Books::getPrice).collect(Collectors.toList());
        int summ = sum.stream().mapToInt(a ->a).sum();
        return new OrderDTO(
                this.orderId,
                this.user.getLogin(),
                books,
                summ,
                this.status.toString()
        );

    }

    public Order(){super();}

    public Order(long order_id, User user, List<Books> books, int sum, OrderStatus status, LocalDate created) {
        this.orderId = order_id;
        this.user = user;
        this.books = books;
        this.sum = sum;
        this.status = status;
        this.created = created;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }


    public long getOrderId() {
        return orderId;
    }

    public int getSum() {
        return sum;
    }

    public User getUser() {
        return user;
    }

    public List<Books> getBooks() {
        return books;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void addBook(Books addBooks) {
        books.add(addBooks);
    }
}

