package Server;

import Classes.Admin;
import DataBase.UsersHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMethods {
    public static String loginAdmin(Admin admin){
        ResultSet result =  UsersHandler.getAdmin(admin);

        int counter = 0;

        try{
            while(result.next())
                counter++;
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >=1)
            return "Success authorization!";
        else
            return "Error!";
    }
}
