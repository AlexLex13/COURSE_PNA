package Server;

import Classes.Admin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static DataBase.DataBaseHandler.getAdmin;

class ServerClientThread extends Thread {
    Socket serverClient;
    DataInputStream inStream;
    DataOutputStream outStream;
    int clientNumber;
    int square;

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
                Admin admin = new Admin(clientMessage.split(" ")[0], clientMessage.split(" ")[1]);
                System.out.println("From Client " + clientNumber + ": received " + admin);
                var res = getAdmin(admin);
                if(res != null)
                    serverMessage = " Success authorization!";
                else
                    serverMessage = "LOX!";
                outStream.writeUTF(serverMessage);
                outStream.flush();
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
