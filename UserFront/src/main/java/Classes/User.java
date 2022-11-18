package Classes;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String login;
    private String password;
    private String role;

    public User() {
        this.id = -1;
        this.login = "";
        this.password = "";
        this.role = "";
    }


    public User(int id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
