package Server;

import Classes.Admin;
import Classes.Person;
import Classes.User;

import java.io.*;
import java.net.Socket;

class ServerClientThread extends Thread {
    Socket serverClient;
    DataInputStream inStream;
    DataOutputStream outStream;
    int clientNumber;

    ServerClientThread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNumber = counter;
    }

    public void run() {
        try {
            inStream = new DataInputStream(serverClient.getInputStream());
            outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage, serverMessage;
            while (true) {
                clientMessage = inStream.readUTF();
                System.out.println("From Client " + clientNumber + "---> " + clientMessage);
                String[] strArr = clientMessage.split(" ");
                switch (strArr[0]){
                    case "authorization": {
                        Person person;
                        if(strArr[1].equals("Admin"))
                            person = new Admin(strArr[2], strArr[3]);
                        else
                            person = new User(strArr[2], strArr[3]);
                        serverMessage = ServerMethods.login(person);
                        outStream.writeUTF(serverMessage);
                        outStream.flush();
                    }break;
                    case "next": {
                        System.out.println("From Client " + clientNumber + ": received " + clientMessage);
                        serverMessage = clientMessage.split(" ")[1] + " HOHHI";
                        outStream.writeUTF(serverMessage);
                        outStream.flush();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                inStream.close();
                outStream.close();
                serverClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            };
            System.out.println("Client number " + clientNumber + " exit!");
        }
    }
}
