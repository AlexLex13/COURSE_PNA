package model;

import java.io.Serializable;

public class Doctor extends User implements Serializable{
    private int id;
    private String post;
    private String room;
    private String[] schedule = new String[14];
    private String district;

    public Doctor() {
        super();
        this.id = -1;
        this.post = "";
        this.room = "";
        for(int i = 0; i < 14; i++) {
            this.schedule[i] = "";
        }
        this.district = "";
    }

    public Doctor(int person_id, String surname, String name, String lastname, String phone, int user_id, String login, String password, String role, String work_phone, int id, String post, String room, String district, String[] schedule) {
        super(person_id, surname, name, lastname, phone, user_id, login, password, role, work_phone);
        this.id = id;
        this.post = post;
        this.room = room;
        this.district = district;
        this.schedule = schedule;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String[] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[] schedule) {
        this.schedule = schedule;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getUserId() {return super.getId(); }

    public void setUserId(int id) { super.setId(id); }
}
