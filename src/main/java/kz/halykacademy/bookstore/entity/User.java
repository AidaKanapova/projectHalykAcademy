package kz.halykacademy.bookstore.entity;


import kz.halykacademy.bookstore.dto.UserDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true)
    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> order = new ArrayList<>();

    /*@ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
     joinColumns = @JoinColumn(name = "user_id"))*/
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean blocked;

    public UserDTO userDTO() {
        return new UserDTO(
                this.user_id,
                this.login,
                this.password,
                this.role,
                this.blocked
        );
    }

}


