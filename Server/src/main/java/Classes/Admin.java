package Classes;

import java.io.Serializable;

public class Admin extends Person {

    public Admin(String login, String password) {
        super(login, password);
    }

    public Admin(String id, String login, String password) {
        super(id, login, password);
    }

    @Override
    public String toString() {
        return "Admin{} " + super.toString();
    }
}
