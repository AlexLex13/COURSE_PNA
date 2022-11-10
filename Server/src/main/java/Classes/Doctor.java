package Classes;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String id;
    private String name;
    private String specialization;
    private String office;

    public Doctor(String id, String name, String specialization, String office) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getOffice() {
        return office;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", office='" + office + '\'' +
                '}';
    }
}
