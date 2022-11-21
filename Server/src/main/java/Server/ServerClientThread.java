package Server;

import DataBase.DataBaseHandler;
import model.User;

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
                switch (command){
                    case "authorization":
                        User authorizationUser = (User) inStream.readObject();
                        outStream.writeObject(dataBaseHandler.authorization(authorizationUser));
                        break;
//                    case "getAllAdmins":
//                        outStream.writeObject(dataBaseHandler.getAllAdmins());
//                        break;
//                    case "getAllUsers":
//                        outStream.writeObject(dataBaseHandler.getAllUsers());
//                        break;
//                    case "getAllDoctors":
//                        outStream.writeObject(dataBaseHandler.getAllDoctors());
//                        break;
//                    case "getAllClients":
//                        outStream.writeObject(dataBaseHandler.getAllClients());
//                        break;
//                    case "getRecordsSchedule":
//                        Doctor doctor = (Doctor) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.getRecordsSchedule(doctor));
//                        break;
//                    case "getAllVisits":
//                        outStream.writeObject(dataBaseHandler.getAllVisits());
//                        break;
//                    case "getAllVisitsDoctor":
//                        Doctor workdoctor = (Doctor) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.getAllVisitsDoctor(workdoctor));
//                        break;
//                    case "insertAdmin":
//                        Admin newAdmin = (Admin) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.addAdmin(newAdmin));
//                        break;
//                    case "insertUser":
//                        User newUser = (User) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.addUser(newUser));
//                        break;
//                    case "insertDoctor":
//                        Doctor newDoctor = (Doctor) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.addDoctor(newDoctor));
//                        break;
//                    case "insertClient":
//                        Client newClient = (Client) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.addClient(newClient));
//                        break;
//                    case "insertVisit":
//                        Visits addVisit = (Visits) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.addVisit(addVisit));
//                        break;
//                    case "updateMyUserData":
//                        User updateMyUserData = (User) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updateMyUserData(updateMyUserData));
//                        break;
//                    case "updatePassword":
//                        User updatePassword = (User) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updatePassword(updatePassword));
//                        break;
//                    case "updatePerson":
//                        User updatePerson = (User) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updatePerson(updatePerson));
//                        break;
//                    case "updateAdmin":
//                        Admin updateAdmin = (Admin) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updateAdmin(updateAdmin));
//                        break;
//                    case "updateUser":
//                        User updateUser = (User) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updateUser(updateUser));
//                        break;
//                    case "updateDoctor":
//                        Doctor updateDoctor = (Doctor) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updateDoctor(updateDoctor));
//                        break;
//                    case "updateClient":
//                        Client updateClient = (Client) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.updateClient(updateClient));
//                        break;
//                    case "deleteAdmin":
//                        Admin deleteAdmin = (Admin) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.deleteAdmin(deleteAdmin));
//                        break;
//                    case "deleteUser":
//                        User deleteUser = (User) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.deleteUser(deleteUser));
//                        break;
//                    case "deleteClient":
//                        Client deletClient = (Client) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.deleteClient(deletClient));
//                        break;
//                    case "deleteDoctor":
//                        Doctor deleteDoctor = (Doctor) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.deleteDoctor(deleteDoctor));
//                        break;
//                    case "getCheck":
//                        Visits currentVisit = (Visits) inStream.readObject();
//                        outStream.writeObject(dataBaseHandler.getCheck(currentVisit));
//                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Client number " + clientNumber + " exit!");
        }
    }
}
