package Server;

import Classes.Admin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
                switch (clientMessage.split(" ")[0]){
                    case "authorization": {
                        Admin admin = new Admin(clientMessage.split(" ")[1], clientMessage.split(" ")[2]);
                        System.out.println("From Client " + clientNumber + ": received " + admin);
                        serverMessage = ServerMethods.loginAdmin(admin);
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
