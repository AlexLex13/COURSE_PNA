package model;

import java.io.Serializable;

public class Client extends Person implements Serializable{
    private int id;
    private String district;
    private String dateOfBirth;
    private String address;
    private String passportNumber;

    public Client() {
        super();
        this.id = -1;
        this.district = "";
        this.dateOfBirth = "";
        this.address = "";
        this.passportNumber = "";
    }

    public Client(int id, String surname, String name, String lastname, String phone, int id1, String district, String dateOfBirth, String address, String passportNumber) {
        super(id, name);
        this.id = id1;
        this.district = district;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.passportNumber = passportNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() { return super.getId(); }

    public void setPersonId(int id) { super.setId(id);}

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
