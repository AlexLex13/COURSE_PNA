package Classes;

import java.io.Serializable;

public class User extends Person {
    public User(String login, String password) {
        super(login, password);
    }

    public User(String id, String login, String password) {
        super(id, login, password);
    }

    @Override
    public String toString() {
        return "User{} " + super.toString();
    }
}
