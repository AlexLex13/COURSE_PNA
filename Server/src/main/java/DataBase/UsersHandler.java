package DataBase;

import Classes.Admin;
import Classes.Person;
import Classes.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersHandler {
    public static ResultSet getPerson(Person person) {
        ResultSet resSet = null;
        String TABLE = "User";

        if(person instanceof Admin)
            TABLE = "Admin";

        String select = "SELECT * FROM \"" + TABLE + "\" WHERE login=? AND password=?";
        try {
            PreparedStatement prSt = DataBaseHandler.getDBConnection().prepareStatement(select);
            prSt.setString(1,person.getLogin());
            prSt.setString(2,person.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public static ArrayList<Admin> getAdmins() {
        ArrayList<Admin> admins = new ArrayList<Admin>();
        String select = "SELECT * FROM \"Admin\"";
        try {
            PreparedStatement prSt = DataBaseHandler.getDBConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Admin admin = new Admin(resSet.getString("id"),
                        resSet.getString("login"),
                        resSet.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        String select = "SELECT * FROM \"User\"";
        try {
            PreparedStatement prSt = DataBaseHandler.getDBConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                User user = new User(resSet.getString("id"),
                                        resSet.getString("login"),
                                            resSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
