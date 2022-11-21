package DataBase;


import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseHandler extends DataBaseConnector {
    public DataBaseHandler() { super.createDBConnection();}

    public User authorization(User user) throws SQLException {
        try {
            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM \"User\" WHERE login='%s';", user.getLogin()));
            if(rs.next()){
                if (user.getPassword().equals(rs.getString("password"))) {
                    user.setRole(rs.getString("role"));
                    user.setId(Integer.parseInt(rs.getString("id")));
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


        //---------------------ПОЛУЧЕНИЕ ВСЕЙ ТАБЛИЦЫ-----------------------------------


//    public ArrayList<Admin> getAllAdmins() throws SQLException{
//        try {
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM user INNER JOIN person on user.person_id=person.person_id\n" +
//                    "INNER JOIN admin on user.user_id=admin.user_id WHERE user.role='admin';"));
//            ArrayList<Admin> adminList = new ArrayList<>();
//            while(rs.next()){
//                Admin admin = new Admin(
//                        Integer.parseInt(rs.getString("user_id")),
//                        rs.getString("login"),
//                        rs.getString("password"),
//                        rs.getString("role"),
//                        rs.getString("work_phone"),
//                        rs.getString("name"),
//                        rs.getString("surname"),
//                        rs.getString("lastname"),
//                        rs.getString("personal_phone"),
//                        Integer.parseInt(rs.getString("person_id")),
//                        Integer.parseInt(rs.getString("admin_id")),
//                        rs.getString("rights"),
//                        rs.getString("block")
//                );
//                adminList.add(admin);
//            }
//            return adminList;
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public ArrayList<User> getAllUsers() throws SQLException{
//        try {
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM user INNER JOIN person ON user.person_id=person.person_id WHERE user.role='user';"));
//            ArrayList<User> userList = new ArrayList<>();
//            while(rs.next()){
//                User user = new User(
//                        Integer.parseInt(rs.getString("person_id")),
//                        rs.getString("surname"),
//                        rs.getString("name"),
//                        rs.getString("lastname"),
//                        rs.getString("personal_phone"),
//                        Integer.parseInt(rs.getString("user_id")),
//                        rs.getString("login"),
//                        rs.getString("password"),
//                        rs.getString("role"),
//                        rs.getString("work_phone")
//                );
//                userList.add(user);
//            }
//            return userList;
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public ArrayList<Doctor> getAllDoctors() throws SQLException{
//        try {
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM user INNER JOIN person on user.person_id=person.person_id\n" +
//                    "INNER JOIN doctor on user.user_id=doctor.user_id WHERE user.role='doctor';"));
//            ArrayList<Doctor> doctorList = new ArrayList<>();
//            while(rs.next()){
//                String schedule[];
//                schedule = rs.getString("schedule").split("-", 14);
//                for(int i = 0; i < schedule.length; i++){
//                    if(schedule[i] == null) schedule[i] = "";
//                }
//                Doctor doctor = new Doctor(
//                        Integer.parseInt(rs.getString("person_id")),
//                        rs.getString("surname"),
//                        rs.getString("name"),
//                        rs.getString("lastname"),
//                        rs.getString("personal_phone"),
//                        Integer.parseInt(rs.getString("user_id")),
//                        rs.getString("login"),
//                        rs.getString("password"),
//                        rs.getString("role"),
//                        rs.getString("work_phone"),
//                        Integer.parseInt(rs.getString("doctor_id")),
//                        rs.getString("post"),
//                        rs.getString("room"),
//                        rs.getString("district"),
//                        schedule
//                );
//                doctorList.add(doctor);
//            }
//            return doctorList;
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public ArrayList<Client> getAllClients() throws SQLException{
//        try {
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM client INNER JOIN person ON client.person_id=person.person_id;"));
//            ArrayList<Client> clintList = new ArrayList<>();
//            while(rs.next()){
//                Client client = new Client(
//                        Integer.parseInt(rs.getString("person_id")),
//                        rs.getString("surname"),
//                        rs.getString("name"),
//                        rs.getString("lastname"),
//                        rs.getString("personal_phone"),
//                        Integer.parseInt(rs.getString("client_id")),
//                        rs.getString("district"),
//                        rs.getString("birth_date"),
//                        rs.getString("address"),
//                        rs.getString("passport_id")
//                );
//                clintList.add(client);
//            }
//            return clintList;
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public ArrayList<Schedule> getRecordsSchedule(Doctor doctor){
//        try{
//            ArrayList<Schedule> scheduleList = new ArrayList<>();
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT schedule, WEEKDAY(curdate()) as daynum FROM doctor where doctor_id='%d';", doctor.getId()));
//            if(rs.next()){
//                String doctorSchedule[] = rs.getString("schedule").split("-", 14);
//                int curdamynum = Integer.parseInt(rs.getString("daynum"));
//                int day = curdamynum;
//                int interval = 0;
//                while(day < 7){
//                    ResultSet rsDateFirst = super.getStatement().executeQuery(String.format("SELECT adddate(curdate(), interval '%d' day) AS date;", interval));
//                    if(rsDateFirst.next()){
//                        String currentTime = doctorSchedule[day*2];
//                        if(currentTime != null){
//                            while(!currentTime.equals(doctorSchedule[day*2+1])){
//                                Schedule schedule = new Schedule();
//                                Statement statement = super.getConnection().createStatement();
//                                ResultSet rsOldRecord = statement.executeQuery(String.format("SELECT * FROM visit \n" +
//                                        "INNER JOIN client on visit.client_id=client.client_id \n" +
//                                        "INNER JOIN doctor on visit.doctor_id=doctor.doctor_id\n" +
//                                        "WHERE doctor.doctor_id='%d' AND time='%s' AND date='%s';", doctor.getId(), currentTime, rsDateFirst.getString("date")));
//                                if(rsOldRecord.next()){
//                                    schedule.setRegistrationTime(rsOldRecord.getString("registration_date"));
//                                    schedule.setPassportNumber(rsOldRecord.getString("passport_id"));
//                                    schedule.setComment(rsOldRecord.getString("comment"));
//                                }
//                                schedule.setDate(rsDateFirst.getString("date"));
//                                schedule.setTime(currentTime);
//                                scheduleList.add(schedule);
//                                String hms[] = currentTime.split(":");
//                                int hour = Integer.parseInt(hms[0]);
//                                hour++;
//                                if(String.valueOf(hour).length()==1) currentTime = "0" + String.valueOf(hour) + ":" + hms[1] + ":" + hms[2];
//                                else currentTime = String.valueOf(hour) + ":" + hms[1] + ":" + hms[2];
//                            }
//                        }
//                    }
//                    interval++;
//                    day++;
//                }
//                day = 0;
//                while(day < curdamynum){
//                    ResultSet rsDateFirst = super.getStatement().executeQuery(String.format("SELECT adddate(curdate(), interval '%d' day) AS date;", interval));
//                    if(rsDateFirst.next()){
//                        String currentTime = doctorSchedule[day*2];
//                        if(currentTime != null){
//                            while(!currentTime.equals(doctorSchedule[day*2+1])){
//                                Schedule schedule = new Schedule();
//                                Statement statement = super.getConnection().createStatement();
//                                ResultSet rsOldRecord1 = statement.executeQuery(String.format("SELECT * FROM visit \n" +
//                                        "INNER JOIN client on visit.client_id=client.client_id \n" +
//                                        "INNER JOIN doctor on visit.doctor_id=doctor.doctor_id\n" +
//                                        "WHERE doctor.doctor_id='%d' AND time='%s' AND date='%s';", doctor.getId(), currentTime, rsDateFirst.getString("date")));
//                                if(rsOldRecord1.next()){
//                                    schedule.setRegistrationTime(rsOldRecord1.getString("registration_date"));
//                                    schedule.setPassportNumber(rsOldRecord1.getString("passport_id"));
//                                    schedule.setComment(rsOldRecord1.getString("comment"));
//                                }
//                                schedule.setDate(rsDateFirst.getString("date"));
//                                schedule.setTime(currentTime);
//
//                                scheduleList.add(schedule);
//                                String hms[] = currentTime.split(":");
//                                int hour = Integer.parseInt(hms[0]);
//                                hour++;
//                                if(String.valueOf(hour).length()==1) currentTime = "0" + String.valueOf(hour) + ":" + hms[1] + ":" + hms[2];
//                                else currentTime = String.valueOf(hour) + ":" + hms[1] + ":" + hms[2];
//                            }
//                        }
//                    }
//                    interval++;
//                    day++;
//                }
//            }
//            return scheduleList;
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public ArrayList<Visits> getAllVisits(){
//        try {
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM visit WHERE date>=curdate();"));
//            ArrayList<Visits> visitsList = new ArrayList<>();
//            while(rs.next()){
//                Visits visit = new Visits(
//                        Integer.parseInt(rs.getString("visit_id")),
//                        rs.getString("registration_date"),
//                        rs.getString("date"),
//                        rs.getString("time"),
//                        rs.getString("comment"),
//                        Integer.parseInt(rs.getString("doctor_id")),
//                        Integer.parseInt(rs.getString("client_id"))
//                );
//                visitsList.add(visit);
//            }
//            return visitsList;
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public ArrayList<Visits> getAllVisitsDoctor(Doctor doctor){
//        try {
//            ResultSet rs = super.getStatement().executeQuery(String.format("SELECT * FROM visit WHERE date>=curdate() AND doctor_id='%d';", doctor.getId()));
//            ArrayList<Visits> visitsList = new ArrayList<>();
//            while(rs.next()){
//                Visits visit = new Visits(
//                        Integer.parseInt(rs.getString("visit_id")),
//                        rs.getString("registration_date"),
//                        rs.getString("date"),
//                        rs.getString("time"),
//                        rs.getString("comment"),
//                        Integer.parseInt(rs.getString("doctor_id")),
//                        Integer.parseInt(rs.getString("client_id"))
//                );
//                visitsList.add(visit);
//            }
//            return visitsList;
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    public String getCheck(Visits visit) throws SQLException{
//        try{
//            ResultSet rs = super.getStatement().executeQuery(String.format("select * from visit \n" +
//                    "inner join client on visit.client_id=client.client_id\n" +
//                    "inner join doctor on visit.doctor_id=doctor.doctor_id\n" +
//                    "inner join user on doctor.user_id=user.user_id\n" +
//                    "inner join person on person.person_id=user.person_id\n" +
//                    "where visit.visit_id='%d';", visit.getId()));
//            while(rs.next()){
//                String result = "";
//                result += rs.getString("date") + "#";
//                result += rs.getString("time") + "#";
//                result += rs.getString("passport_id") + "#";
//                result += rs.getString("room") + "#";
//                result += rs.getString("post") + "#";
//                result += rs.getString("surname") + "#";
//                result += rs.getString("name") + "#";
//                result += rs.getString("lastname") + "#";
//                result += rs.getString("work_phone") + "#";
//                return result;
//            }
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "";
//    }
//
//
//    //-------------------------ДОБАВЛЕНИЕ ДАННЫХ-------------------------------------
//
//
//    public String addAdmin(Admin newAdmin){
//        String addData[] = {
//                String.format("INSERT INTO person (name, surname, lastname, personal_phone) VALUES('%s', '%s', '%s', '%s');", newAdmin.getName(), newAdmin.getSurname(), newAdmin.getLastname(), newAdmin.getPhone()),
//                String.format("INSERT INTO user (login, password, role, work_phone, person_id) VALUES('%s', '%s', '%s', '%s', last_insert_id());", newAdmin.getLogin(), newAdmin.getPassword(), newAdmin.getRole(),newAdmin.getWork_phone()),
//                String.format("INSERT INTO admin (rights, block, user_id) VALUES('%s', '%s', last_insert_id());", newAdmin.getRights(), newAdmin.getBlock())
//        };
//        return addData(addData);
//    }
//
//    public String addUser(User user){
//        String addData[] = {
//                String.format("INSERT INTO person (name, surname, lastname, personal_phone) VALUES('%s', '%s', '%s', '%s');", user.getName(), user.getSurname(), user.getLastname(), user.getPhone()),
//                String.format("INSERT INTO user (login, password, role, work_phone, person_id) VALUES('%s', '%s', '%s', '%s', last_insert_id());", user.getLogin(), user.getPassword(), user.getRole(), user.getWork_phone())
//        };
//        return addData(addData);
//    }
//
//    public String addDoctor(Doctor doctor){
//        String addData[] = {
//                String.format("INSERT INTO person (name, surname, lastname, personal_phone) VALUES('%s', '%s', '%s', '%s');", doctor.getName(), doctor.getSurname(), doctor.getLastname(), doctor.getPhone()),
//                String.format("INSERT INTO user (login, password, role, work_phone, person_id) VALUES('%s', '%s', '%s', '%s', last_insert_id());", doctor.getLogin(), doctor.getPassword(), doctor.getRole(),doctor.getWork_phone()),
//                String.format("INSERT INTO doctor (post, room, district, schedule, user_id) VALUES('%s', '%s', '%s', '%s', last_insert_id());", doctor.getPost(), doctor.getRoom(), doctor.getDistrict(), "-------------")
//        };
//        return addData(addData);
//    }
//
//    public String addClient(Client client){
//        String addData[] = {
//                String.format("INSERT INTO person (name, surname, lastname, personal_phone) VALUES('%s', '%s', '%s', '%s');", client.getName(), client.getSurname(), client.getLastname(), client.getPhone()),
//                String.format("INSERT INTO client (district, birth_date, address, passport_id, person_id) VALUES('%s', '%s', '%s', '%s', last_insert_id());", client.getDistrict(), client.getDateOfBirth(), client.getAddress(), client.getPassportNumber())
//        };
//        return addData(addData);
//    }
//
//    public String addVisit(Visits visit){
//        String addData[] = {
//                String.format("INSERT INTO visit (registration_date, date, time, comment, doctor_id, client_id) VALUES(CURDATE(), '%s', '%s', '%s', '%d', '%d');", visit.getDate(), visit.getTime(), visit.getComment(), visit.getDoctor_id(), visit.getClient_id())
//        };
//        return addData(addData);
//    }
//
//    private String addData(String[] data){
//        try{
//            for(int i = 0; i < data.length;i++){
//                super.getStatement().execute(data[i]);
//            }
//            return "Успешно добавлено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось добавить данные!";
//    }
//
//    //--------------------------ОБНОВЛЕНИЕ ДАННЫХ--------------------------------------
//
//
//    public String updateAdmin(Admin admin) throws SQLException{
//        String statement = String.format("UPDATE user INNER JOIN person ON person.person_id=user.person_id \n" +
//                        "INNER JOIN admin ON admin.user_id=user.user_id\n" +
//                        "SET name='%s',surname='%s',lastname='%s',personal_phone='%s'," +
//                        "login='%s',work_phone='%s',rights='%s',block='%s'\n" +
//                        " WHERE user.user_id='%d';", admin.getName(), admin.getSurname(), admin.getLastname(), admin.getPhone(),
//                admin.getLogin(), admin.getWork_phone(), admin.getRights(), admin.getBlock(), admin.getUserId());
//        try {
//            super.getStatement().executeUpdate(statement);
//            return "Успешно сохранено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось изменить данные";
//    }
//
//    public String updateUser(User user) throws SQLException{
//        String statement = String.format("UPDATE user INNER JOIN person ON person.person_id=user.person_id \n" +
//                        "SET name='%s',surname='%s',lastname='%s',personal_phone='%s'," +
//                        "login='%s',work_phone='%s'\n" +
//                        " WHERE user.user_id='%d';", user.getName(), user.getSurname(), user.getLastname(), user.getPhone(),
//                user.getLogin(), user.getWork_phone(), user.getId());
//        return updateData(statement);
//    }
//
//    public String updateDoctor(Doctor doctor) throws SQLException{
//        String schedule = "";
//        for(int i = 0; i < 14; i++){
//            schedule += doctor.getSchedule()[i];
//            if(i == 13) break;
//            schedule += "-";
//        }
//        String statement = String.format("UPDATE user INNER JOIN person ON person.person_id=user.person_id \n" +
//                        "INNER JOIN doctor ON doctor.user_id=user.user_id\n" +
//                        "SET name='%s',surname='%s',lastname='%s',personal_phone='%s',login='%s',work_phone='%s',post='%s',room='%s',district='%s',schedule='%s'\n" +
//                        "WHERE user.user_id='%d';", doctor.getName(), doctor.getSurname(), doctor.getLastname(), doctor.getPhone(),
//                doctor.getLogin(), doctor.getWork_phone(), doctor.getPost(), doctor.getRoom(), doctor.getDistrict(), schedule, doctor.getUserId());
//        return updateData(statement);
//    }
//
//    public String updateMyUserData(User user) throws SQLException{
//        String statement = String.format("UPDATE user SET login='%s',password='%s',work_phone='%s' where user_id='%d';",
//                user.getLogin(), user.getPassword(), user.getWork_phone(), user.getId());
//        try {
//            ResultSet rs1 = super.getStatement().executeQuery(String.format("select * from user WHERE login='%s' and user_id!='%d';", user.getLogin(), user.getId()));
//            if(rs1.next()){return "Пользователь с таким логином уже существует!"; }
//            super.getStatement().executeUpdate(statement);
//            return "Успешно сохранено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось изменить данные";
//    }
//
//    public String updatePerson(User user) throws SQLException{
//        String statement = String.format("UPDATE person inner join user on person.person_id=user.user_id SET name='%s', surname='%s', " +
//                "lastname='%s', personal_phone='%s' where user_id='%d';", user.getName(), user.getSurname(),user.getLastname(), user.getPhone(), user.getId());
//        return updateData(statement);
//    }
//
//    public String updatePassword(User user){
//        String statement = String.format("UPDATE user SET password='%s' WHERE user.user_id='%d';", user.getPassword(), user.getId());
//        return updateData(statement);
//    }
//
//    public String updateClient(Client client){
//        String statement = String.format("UPDATE client INNER JOIN person ON person.person_id=client.person_id \n" +
//                "SET name='%s',surname='%s',lastname='%s',personal_phone='%s'," +
//                "district='%s',birth_date='%s',address='%s',passport_id='%s'\n" +
//                " WHERE client.client_id='%d';", client.getName(), client.getSurname(), client.getLastname(), client.getPhone(), client.getDistrict(), client.getDateOfBirth(), client.getAddress(), client.getPassportNumber(), client.getId());
//        return updateData(statement);
//    }
//
//    private String updateData(String statement){
//        try {
//            super.getStatement().executeUpdate(statement);
//            return "Успешно сохранено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось изменить данные";
//    }
//
//    //------------------------------УДАЛЕНИЕ ДАННЫХ----------------------------------
//
//
//    public String deleteAdmin(Admin deleteAdmin){
//        String statement = String.format("DELETE from admin, user, person " +
//                "USING admin, user, person " +
//                "WHERE user.user_id=admin.user_id && user.person_id=person.person_id && user.login='%s';", deleteAdmin.getLogin());
//        try{
//            super.getStatement().execute(statement);
//            return "Успешно удалено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось удалить данные!";
//    }
//
//
//    public String deleteUser(User deleteUser){
//        String statement = String.format("DELETE from user, person " +
//                "USING user, person " +
//                "WHERE user.person_id=person.person_id && user.login='%s';", deleteUser.getLogin());
//        try{
//            super.getStatement().execute(statement);
//            return "Успешно удалено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось удалить данные!";
//    }
//
//    public String deleteClient(Client client){
//        String statement = String.format("DELETE from client, person, visit " +
//                "USING client, person, visit " +
//                "WHERE client.person_id=person.person_id && client.client_id=visit.client_id && client.client_id='%d';", client.getId());
//        try{
//            super.getStatement().execute(statement);
//            return "Успешно удалено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось удалить данные!";
//    }
//
//    public String deleteDoctor(Doctor doctor){
//        String statement = String.format("DELETE from person, doctor, user USING person, doctor, user WHERE person.person_id=user.person_id and user.user_id=doctor.user_id and doctor.doctor_id='%d';", doctor.getId());
//        try{
//            super.getStatement().execute(statement);
//            return "Успешно удалено!";
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return "Не удалось удалить данные!";
//    }
}