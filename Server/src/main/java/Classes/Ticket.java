package Classes;

public class Ticket {
    private String id;
    private String date;
    private String time;
    private String doctor_id;
    private String patient_id;

    public Ticket(String id, String date, String time, String doctor_id, String patient_id) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", patient_id='" + patient_id + '\'' +
                '}';
    }
}
