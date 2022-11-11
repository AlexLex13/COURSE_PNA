package Server;

import Classes.Admin;
import Classes.Person;
import DataBase.UsersHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMethods {
    public static String login(Person person){
        ResultSet result =  UsersHandler.getPerson(person);

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
