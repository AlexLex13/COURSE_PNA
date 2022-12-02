package Server;

import DataBase.DataBaseHandler;
import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ServerClientThread extends Thread {
    Socket serverClient;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;
    int clientNumber;

    ServerClientThread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNumber = counter;
        try {
            inStream = new ObjectInputStream(serverClient.getInputStream());
            outStream = new ObjectOutputStream(serverClient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            String command = "";
            while(!command.equals("exit")){
                command = (String) inStream.readObject();
                switch (command) {
                    case "authorization" -> {
                        User authorizationUser = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.authorization(authorizationUser));
                    }
                    case "getAllAdmins" -> outStream.writeObject(dataBaseHandler.getAllAdmins());
                    case "getAllUsers" -> outStream.writeObject(dataBaseHandler.getAllUsers());
                    case "getAllDoctors" -> outStream.writeObject(dataBaseHandler.getAllDoctors());
                    case "getAllClients" -> outStream.writeObject(dataBaseHandler.getAllClients());
                    case "getRecordsSchedule" -> {
                        Doctor doctor = (Doctor) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.getRecordsSchedule(doctor));
                    }
                    case "getAllVisits" -> outStream.writeObject(dataBaseHandler.getAllVisits());
                    case "getAllVisitsDoctor" -> {
                        Doctor workdoctor = (Doctor) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.getAllVisitsDoctor(workdoctor));
                    }
                    case "insertAdmin" -> {
                        Admin newAdmin = (Admin) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.addAdmin(newAdmin));
                    }
                    case "insertUser" -> {
                        User newUser = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.addUser(newUser));
                    }
                    case "insertDoctor" -> {
                        Doctor newDoctor = (Doctor) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.addDoctor(newDoctor));
                    }
                    case "insertClient" -> {
                        Client newClient = (Client) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.addClient(newClient));
                    }
                    case "insertVisit" -> {
                        Visits addVisit = (Visits) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.addVisit(addVisit));
                    }
                    case "updateMyUserData" -> {
                        User updateMyUserData = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updateMyUserData(updateMyUserData));
                    }
                    case "updatePassword" -> {
                        User updatePassword = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updatePassword(updatePassword));
                    }
                    case "updatePerson" -> {
                        User updatePerson = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updatePerson(updatePerson));
                    }
                    case "updateAdmin" -> {
                        Admin updateAdmin = (Admin) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updateAdmin(updateAdmin));
                    }
                    case "updateUser" -> {
                        User updateUser = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updateUser(updateUser));
                    }
                    case "updateDoctor" -> {
                        Doctor updateDoctor = (Doctor) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updateDoctor(updateDoctor));
                    }
                    case "updateClient" -> {
                        Client updateClient = (Client) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.updateClient(updateClient));
                    }
                    case "deleteAdmin" -> {
                        Admin deleteAdmin = (Admin) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.deleteAdmin(deleteAdmin));
                    }
                    case "deleteUser" -> {
                        User deleteUser = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.deleteUser(deleteUser));
                    }
                    case "deleteClient" -> {
                        Client deleteClient = (Client) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.deleteClient(deleteClient));
                    }
                    case "deleteDoctor" -> {
                        Doctor deleteDoctor = (Doctor) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.deleteDoctor(deleteDoctor));
                    }
                    case "getCheck" -> {
                        Visits currentVisit = (Visits) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.getCheck(currentVisit));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client number " + clientNumber + " exit!");
        }
    }
}
