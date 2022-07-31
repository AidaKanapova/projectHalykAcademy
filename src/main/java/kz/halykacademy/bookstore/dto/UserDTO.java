package kz.halykacademy.bookstore.dto;

public class UserDTO {

    private Long user_id;
    private  String login;
    private String password;
    private  String role;
    private boolean blocked;

    public UserDTO(){super();}

    public UserDTO(Long user_id, String login, String password, String role, boolean blocked) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
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
}
