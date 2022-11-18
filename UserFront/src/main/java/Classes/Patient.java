package Classes;

import java.io.Serializable;

public class Patient extends Person implements Serializable {
    private String date_of_birth;
    private String address;
    private String user_id;

    public Patient(String id, String name, String date_of_birth, String address, String user_id) {
        super();
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.user_id = user_id;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
