package DataBase;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseHandler extends DataBaseConnector {
    public DataBaseHandler() { super.createDBConnection();}

    public User authorization(User user) {
        try {
            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM \"User\" WHERE login='%s';", user.getLogin()));
            if(rs.next()){
                if (user.getPassword().equals(rs.getString("password"))) {
                    user.setRole(rs.getString("role"));
                    user.setId(Integer.parseInt(rs.getString("user_id")));
                }
                else{
                    user.setRole("wrong");
                }
            }
            else {
                user.setRole("wrong");
            }
            return user;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }



    public ArrayList<Admin> getAllAdmins() {
        try {
            ResultSet rs = super.getStatement().executeQuery("SELECT * FROM \"User\" U INNER JOIN \"Person\" P ON U.person_id=P.person_id\n" +
                    "INNER JOIN \"Admin\" A ON U.user_id=A.user_id WHERE U.role='admin';");
            ArrayList<Admin> adminList = new ArrayList<>();
            while(rs.next()){
                Admin admin = new Admin(
                        Integer.parseInt(rs.getString("user_id")),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("name"),
                        Integer.parseInt(rs.getString("person_id")),
                        Integer.parseInt(rs.getString("admin_id")),
                        rs.getString("rights"),
                        rs.getString("block")
                );
                adminList.add(admin);
            }
            return adminList;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        try {
            ResultSet rs = super.getStatement().executeQuery("SELECT * FROM \"User\" U INNER JOIN \"Person\" P ON U.person_id=P.person_id WHERE U.role='user';");
            ArrayList<User> userList = new ArrayList<>();
            while(rs.next()){
                User user = new User(
                        Integer.parseInt(rs.getString("person_id")),
                        rs.getString("name"),
                        Integer.parseInt(rs.getString("user_id")),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                userList.add(user);
            }
            return userList;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Doctor> getAllDoctors() {
        try {
            ResultSet rs = super.getStatement().executeQuery("SELECT * FROM \"User\" U INNER JOIN \"Person\" P on U.person_id=P.person_id\n" +
                    "INNER JOIN \"Doctor\" D ON U.user_id=D.user_id WHERE U.role='doctor';");
            ArrayList<Doctor> doctorList = new ArrayList<>();
            while(rs.next()){
                String[] schedule;
                schedule = rs.getString("schedule").split("-", 14);
                for(int i = 0; i < schedule.length; i++){
                    if(schedule[i] == null) schedule[i] = "";
                }
                Doctor doctor = new Doctor(
                        Integer.parseInt(rs.getString("person_id")),
                        rs.getString("name"),
                        Integer.parseInt(rs.getString("user_id")),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role"),
                        Integer.parseInt(rs.getString("doctor_id")),
                        rs.getString("post"),
                        rs.getString("room"),
                        rs.getString("district"),
                        schedule
                );
                doctorList.add(doctor);
            }
            return doctorList;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Client> getAllClients() {
        try {
            ResultSet rs = super.getStatement().executeQuery("SELECT * FROM \"Client\" C INNER JOIN \"Person\" P ON C.person_id=P.person_id;");
            ArrayList<Client> clintList = new ArrayList<>();
            while(rs.next()){
                Client client = new Client(
                        Integer.parseInt(rs.getString("person_id")),
                        rs.getString("name"),
                        Integer.parseInt(rs.getString("client_id")),
                        rs.getString("district"),
                        rs.getString("birth_date"),
                        rs.getString("address"),
                        rs.getString("passport_id")
                );
                clintList.add(client);
            }
            return clintList;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Schedule> getRecordsSchedule(Doctor doctor){
        try{
            ArrayList<Schedule> scheduleList = new ArrayList<>();
            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT schedule, EXTRACT(ISODOW FROM NOW()) AS daynum FROM \"Doctor\" where doctor_id='%d';", doctor.getId()));
            if(rs.next()){
                String[] doctorSchedule = rs.getString("schedule").split("-", 14);
                int curdamynum = Integer.parseInt(rs.getString("daynum"));
                int day = curdamynum;
                int interval = 0;
                while(day < 7){
                        ResultSet rsDateFirst = super.getStatement().executeQuery(String.format("SELECT NOW() + interval ''%d' day' AS date;", interval));
                    if(rsDateFirst.next()){
                        String currentTime = doctorSchedule[day*2];
                        if(currentTime != null){
                            while(!currentTime.equals(doctorSchedule[day*2+1])){
                                Schedule schedule = new Schedule();
                                Statement statement = super.getConnection().createStatement();
                                ResultSet rsOldRecord = statement.executeQuery(String.format("""
                                        SELECT * FROM "Visit" V
                                        INNER JOIN "Client" C ON V.client_id=C.client_id
                                        INNER JOIN "Doctor" D ON V.doctor_id=D.doctor_id
                                        WHERE D.doctor_id='%d' AND time='%s' AND date='%s';""", doctor.getId(), currentTime, rsDateFirst.getString("date")));
                                if(rsOldRecord.next()){
                                    schedule.setRegistrationTime(rsOldRecord.getString("registration_date"));
                                    schedule.setPassportNumber(rsOldRecord.getString("passport_id"));
                                    schedule.setComment(rsOldRecord.getString("comment"));
                                }
                                schedule.setDate(rsDateFirst.getString("date"));
                                schedule.setTime(currentTime);
                                scheduleList.add(schedule);
                                String[] hms = currentTime.split(":");
                                int hour = Integer.parseInt(hms[0]);
                                hour++;
                                if(String.valueOf(hour).length()==1) currentTime = "0" + hour + ":" + hms[1] + ":" + hms[2];
                                else currentTime = hour + ":" + hms[1] + ":" + hms[2];
                            }
                        }
                    }
                    interval++;
                    day++;
                }
                day = 0;
                while(day < curdamynum){
                    ResultSet rsDateFirst = super.getStatement().executeQuery(String.format("SELECT NOW() + interval ''%d' day' AS date;", interval));
                    if(rsDateFirst.next()){
                        String currentTime = doctorSchedule[day*2];
                        if(currentTime != null){
                            while(!currentTime.equals(doctorSchedule[day*2+1])){
                                Schedule schedule = new Schedule();
                                Statement statement = super.getConnection().createStatement();
                                ResultSet rsOldRecord1 = statement.executeQuery(String.format("""
                                        SELECT * FROM "Visit" V
                                        INNER JOIN "Client" C on V.client_id=C.client_id
                                        INNER JOIN "Doctor" D on V.doctor_id=D.doctor_id
                                        WHERE D.doctor_id='%d' AND time='%s' AND date='%s';""", doctor.getId(), currentTime, rsDateFirst.getString("date")));
                                if(rsOldRecord1.next()){
                                    schedule.setRegistrationTime(rsOldRecord1.getString("registration_date"));
                                    schedule.setPassportNumber(rsOldRecord1.getString("passport_id"));
                                    schedule.setComment(rsOldRecord1.getString("comment"));
                                }
                                schedule.setDate(rsDateFirst.getString("date"));
                                schedule.setTime(currentTime);

                                scheduleList.add(schedule);
                                String[] hms = currentTime.split(":");
                                int hour = Integer.parseInt(hms[0]);
                                hour++;
                                if(String.valueOf(hour).length()==1) currentTime = "0" + hour + ":" + hms[1] + ":" + hms[2];
                                else currentTime = hour + ":" + hms[1] + ":" + hms[2];
                            }
                        }
                    }
                    interval++;
                    day++;
                }
            }
            return scheduleList;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Visits> getAllVisits(){
        try {
            ResultSet rs = super.getStatement().executeQuery("SELECT * FROM \"Visit\" WHERE date>=NOW();");
            ArrayList<Visits> visitsList = new ArrayList<>();
            while(rs.next()){
                Visits visit = new Visits(
                        Integer.parseInt(rs.getString("visit_id")),
                        rs.getString("registration_date"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("comment"),
                        Integer.parseInt(rs.getString("doctor_id")),
                        Integer.parseInt(rs.getString("client_id"))
                );
                visitsList.add(visit);
            }
            return visitsList;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Visits> getAllVisitsDoctor(Doctor doctor){
        try {
            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM \"Visit\" WHERE date>=NOW() AND doctor_id='%d';", doctor.getId()));
            ArrayList<Visits> visitsList = new ArrayList<>();
            while(rs.next()){
                Visits visit = new Visits(
                        Integer.parseInt(rs.getString("visit_id")),
                        rs.getString("registration_date"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("comment"),
                        Integer.parseInt(rs.getString("doctor_id")),
                        Integer.parseInt(rs.getString("client_id"))
                );
                visitsList.add(visit);
            }
            return visitsList;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String getCheck(Visits visit) {
        try{
            ResultSet rs = super.getStatement().executeQuery(String.format("""
                    select * from "Visit" V
                    inner join "Client" C on V.client_id=C.client_id
                    inner join "Doctor" D on V.doctor_id=D.doctor_id
                    inner join "User" U on D.user_id=U.user_id
                    inner join "Person" P on P.person_id=U.person_id
                    where V.visit_id='%d';""", visit.getId()));
            while(rs.next()){
                String result = "";
                result += rs.getString("date") + "#";
                result += rs.getString("time") + "#";
                result += rs.getString("passport_id") + "#";
                result += rs.getString("room") + "#";
                result += rs.getString("post") + "#";
                result += rs.getString("name") + "#";
                return result;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }


    public String addAdmin(Admin newAdmin){
        String[] addData = {
                String.format("INSERT INTO \"Person\" (name) VALUES('%s');", newAdmin.getName()),
                String.format("INSERT INTO \"User\" (login, password, role, person_id) VALUES('%s', '%s', '%s', CURRVAL(pg_get_serial_sequence('\"Person\"', 'person_id')));", newAdmin.getLogin(), newAdmin.getPassword(), newAdmin.getRole()),
                String.format("INSERT INTO \"Admin\" (rights, block, user_id) VALUES('%s', '%s', CURRVAL(pg_get_serial_sequence('\"Person\"', 'person_id')));", newAdmin.getRights(), newAdmin.getBlock())
        };
        return addData(addData);
    }

    public String addUser(User user){
        String[] addData = {
                String.format("INSERT INTO \"Person\" (name) VALUES('%s');", user.getName()),
                String.format("INSERT INTO \"User\" (login, password, role, person_id) VALUES('%s', '%s', '%s', CURRVAL(pg_get_serial_sequence('\"Person\"', 'person_id')));", user.getLogin(), user.getPassword(), user.getRole())
        };
        return addData(addData);
    }

    public String addDoctor(Doctor doctor){
        String[] addData = {
                String.format("INSERT INTO \"Person\" (name) VALUES('%s');", doctor.getName()),
                String.format("INSERT INTO \"User\" (login, password, role, person_id) VALUES('%s', '%s', '%s', CURRVAL(pg_get_serial_sequence('\"Person\"', 'person_id')));", doctor.getLogin(), doctor.getPassword(), doctor.getRole()),
                String.format("INSERT INTO \"Doctor\" (post, room, district, schedule, user_id) VALUES('%s', '%s', '%s', '%s', CURRVAL(pg_get_serial_sequence('\"Person\"', 'person_id')));", doctor.getPost(), doctor.getRoom(), doctor.getDistrict(), "-------------")
        };
        return addData(addData);
    }

    public String addClient(Client client){
        String[] addData = {
                String.format("INSERT INTO \"Person\" (name) VALUES('%s');", client.getName()),
                String.format("INSERT INTO \"Client\" (district, birth_date, address, passport_id, person_id) VALUES('%s', '%s', '%s', '%s', CURRVAL(pg_get_serial_sequence('\"Person\"', 'person_id')));", client.getDistrict(), client.getDateOfBirth(), client.getAddress(), client.getPassportNumber())
        };
        return addData(addData);
    }

    public String addVisit(Visits visit){
        String[] addData = {
                String.format("INSERT INTO \"Visit\" (registration_date, date, time, comment, doctor_id, client_id) VALUES(NOW(), '%s', '%s', '%s', '%d', '%d');", visit.getDate(), visit.getTime(), visit.getComment(), visit.getDoctor_id(), visit.getClient_id())
        };
        return addData(addData);
    }

    private String addData(String[] data){
        try{
            for (String datum : data) {
                super.getStatement().execute(datum);
            }
            return "Успешно добавлено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось добавить данные!";
    }

    public String updateAdmin(Admin admin) {
        String statement = String.format("""
                        UPDATE "User" U INNER JOIN "Person" P ON P.person_id=U.person_id
                        INNER JOIN "Admin" A ON A.user_id=U.user_id
                        SET name='%s',login='%s',rights='%s',block='%s'
                        WHERE U.user_id='%d';""", admin.getName(),
                admin.getLogin(), admin.getRights(), admin.getBlock(), admin.getUserId());
        try {
            super.getStatement().executeUpdate(statement);
            return "Успешно сохранено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось изменить данные";
    }

    public String updateUser(User user) {
        String statement = String.format("""
                        UPDATE "Person"
                        SET name='%s'
                        WHERE person_id = (SELECT person_id FROM "User" WHERE user_id='%d');
                                                
                        UPDATE "User"
                        SET login='%s'
                        WHERE user_id='%d';""", user.getName(), user.getId(),
                user.getLogin(), user.getId());
        return updateData(statement);
    }

    public String updateDoctor(Doctor doctor) {
        StringBuilder schedule = new StringBuilder();
        for(int i = 0; i < 14; i++){
            schedule.append(doctor.getSchedule()[i]);
            if(i == 13) break;
            schedule.append("-");
        }
        String statement = String.format("""
                        UPDATE "Person"
                        SET name='%s'
                        WHERE person_id = (SELECT person_id FROM "User" WHERE user_id='%d');
                                                
                        UPDATE "User"
                        SET login='%s'
                        WHERE user_id='%d';
                        
                        UPDATE "Doctor"
                        SET post='%s',room='%s',district='%s',schedule='%s'
                        WHERE user_id='%d';""", doctor.getName(), doctor.getUserId(),
                doctor.getLogin(), doctor.getUserId(), doctor.getPost(), doctor.getRoom(), doctor.getDistrict(), schedule, doctor.getUserId());
        return updateData(statement);
    }

    public String updateMyUserData(User user) {
        String statement = String.format("UPDATE \"User\" SET login='%s',password='%s' where user_id='%d';",
                user.getLogin(), user.getPassword(), user.getId());
        try {
            ResultSet rs1 = super.getStatement().executeQuery(String.format("select * from \"User\" WHERE login='%s' and user_id!='%d';", user.getLogin(), user.getId()));
            if(rs1.next()){return "Пользователь с таким логином уже существует!"; }
            super.getStatement().executeUpdate(statement);
            return "Успешно сохранено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось изменить данные";
    }

    public String updatePerson(User user) {
        String statement = String.format("""
                UPDATE "Person"
                SET name='%s'
                WHERE person_id = (SELECT person_id FROM "User" WHERE user_id='%d');""", user.getName(),  user.getId());
        return updateData(statement);
    }

    public String updatePassword(User user){
        String statement = String.format("UPDATE \"User\" U SET password='%s' WHERE U.user_id='%d';", user.getPassword(), user.getId());
        return updateData(statement);
    }

    public String updateClient(Client client){
        String statement = String.format("""
                UPDATE "Person"
                SET name='%s'
                WHERE person_id = (SELECT person_id FROM "Client" WHERE client_id='%d');
                                                
                UPDATE "Client"
                SET district='%s',birth_date='%s',address='%s',passport_id='%s'
                WHERE client_id='%d';""", client.getName(),  client.getId(), client.getDistrict(), client.getDateOfBirth(), client.getAddress(), client.getPassportNumber(), client.getId());
        return updateData(statement);
    }

    private String updateData(String statement){
        try {
            super.getStatement().executeUpdate(statement);
            return "Успешно сохранено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось изменить данные";
    }


    public String deleteAdmin(Admin deleteAdmin){
        String statement = String.format("DELETE from \"Admin\" A, \"User\" U, \"Person\" P " +
                "USING \"Admin\", \"User\", \"Person\" " +
                "WHERE U.user_id=A.user_id && U.person_id=P.person_id && U.login='%s';", deleteAdmin.getLogin());
        try{
            super.getStatement().execute(statement);
            return "Успешно удалено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось удалить данные!";
    }


    public String deleteUser(User deleteUser){
        String statement = String.format("DELETE from \"User\" U, \"Person\" P " +
                "USING \"User\", \"Person\" " +
                "WHERE U.person_id=P.person_id && U.login='%s';", deleteUser.getLogin());
        try{
            super.getStatement().execute(statement);
            return "Успешно удалено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось удалить данные!";
    }

    public String deleteClient(Client client){
        String statement = String.format("DELETE from \"Client\" C, \"Visit\" V, \"Person\" P " +
                "USING \"Client\", \"Visit\", \"Person\" " +
                "WHERE C.person_id=P.person_id && C.client_id=V.client_id && C.client_id='%d';", client.getId());
        try{
            super.getStatement().execute(statement);
            return "Успешно удалено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось удалить данные!";
    }

    public String deleteDoctor(Doctor doctor){
        String statement = String.format("DELETE from \"Person\" P, \"Doctor\" D, \"User\" U USING \"Person\", \"Doctor\", \"User\" WHERE P.person_id=U.person_id and U.user_id=doctor.user_id and D.doctor_id='%d';", doctor.getId());
        try{
            super.getStatement().execute(statement);
            return "Успешно удалено!";
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Не удалось удалить данные!";
    }
}