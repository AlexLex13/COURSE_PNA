package DataBase;

import Classes.Admin;
import Classes.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersHandler {
    public static ResultSet getAdmin(Admin admin) {
        ResultSet resSet = null;

        String select = "SELECT * FROM \"Admin\" WHERE login=? AND password=?";
        try {
            PreparedStatement prSt = DataBaseHandler.getDBConnection().prepareStatement(select);
            prSt.setString(1,admin.getLogin());
            prSt.setString(2,admin.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public static ArrayList<User> getAdmins() {
        ArrayList<User> admins = new ArrayList<User>();
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

    public static ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM \"User\" WHERE login=? AND password=?";
        try {
            PreparedStatement prSt = DataBaseHandler.getDBConnection().prepareStatement(select);
            prSt.setString(1,user.getLogin());
            prSt.setString(2,user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
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
