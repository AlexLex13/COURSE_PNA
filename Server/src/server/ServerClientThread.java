package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNumber;
    int square;

    ServerClientThread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNumber = counter;
    }

    public void run() {
        try {
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "", serverMessage = "";
            while (true) {
                clientMessage = inStream.readUTF();
                if (clientMessage.equals("bye"))
                    break;
                System.out.println("From Client " + clientNumber + ": number is " + clientMessage);
                square = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                serverMessage = "From Server to Client " + clientNumber + " Square of " + clientMessage + " is " + square;
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }
            inStream.close();
            outStream.close();
            serverClient.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client number " + clientNumber + " exit!");
        }
    }
}
