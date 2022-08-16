package kz.halykacademy.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

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





   /* public OrderDTO orderDTO() {
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
*/

    public void addBook(Books addBooks) {

            List<Integer> sumList = List.of();
            if (this.books != null)
                sumList = this.books.stream().map(Books::getPrice).collect(Collectors.toList());
            int sum = (sumList.stream().mapToInt(a -> a).sum()) + addBooks.getPrice();

            if (sum < 10000) {
                books.add(addBooks);
            }else {
                throw new IllegalArgumentException("Overprice");
            }
        }
}





