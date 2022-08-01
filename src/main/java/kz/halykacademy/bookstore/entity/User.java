package kz.halykacademy.bookstore.entity;


import kz.halykacademy.bookstore.dto.UserDTO;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;


    private  String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> order = new ArrayList<>();

    private  String role;
    private boolean blocked;

    public UserDTO userDTO(){
        return  new UserDTO(
                this.user_id,
                this.login,
                this.password,
                this.role,
                this.blocked
        );
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public  User(){super();}

    public User(Long user_id, String login, String password, String role, boolean blocked) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }
}
