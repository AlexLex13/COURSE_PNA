package Classes;

public class Patient {
    private String id;
    private String name;
    private String date_of_birth;
    private String address;
    private String user_id;

    public Patient(String id, String name, String date_of_birth, String address, String user_id) {
        this.id = id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", address='" + address + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
