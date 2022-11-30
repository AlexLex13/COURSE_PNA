package model;

import java.io.Serializable;

public class Admin extends User implements Serializable{
    private int id;
    private String rights;
    private String block;

    public Admin() {
        this.id = -1;
        this.rights = "";
        this.block = "";
    }

    public Admin(int user_id, String login, String password, String role,
                 String name, int person_id,
                 int admin_id, String rights, String block){
        setUserId(user_id);
        setLogin(login);
        setPassword(password);
        setRole(role);
        if(name == null) name="";
        setName(name);
        setPersonId(person_id);
        this.id = admin_id;
        this.rights = rights;
        this.block = block;
    }


    public Admin(Admin admin) {
        this.id = admin.getId();
        this.rights = admin.getRights();
        this.block = admin.getBlock();
        setUserId(admin.getUserId());
        setLogin(admin.getLogin());
        setPassword(admin.getPassword());
        setRole(admin.getRole());
        setName(admin.getName());
    }

    public Admin(int id, String rights, String block, int user_id) {
        this.id = id;
        this.rights = rights;
        this.block = block;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {return super.getId(); }

    public void setUserId(int id) { super.setId(id); }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

}
