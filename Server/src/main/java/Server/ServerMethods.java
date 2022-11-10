package Server;

import Classes.Admin;
import DataBase.DataBaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMethods {
    public static String loginAdmin(Admin admin){
        DataBaseHandler dbHandler = new DataBaseHandler();
        ResultSet result =  dbHandler.getAdmin(admin);

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
