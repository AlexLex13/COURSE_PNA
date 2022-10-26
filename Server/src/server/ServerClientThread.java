package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
                System.out.println("From Client " + clientNumber + ": number is " + clientMessage);
                try {
                    square = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                }catch (NumberFormatException e) {
                    square = -1;
                }
                serverMessage = " Square of " + clientMessage + " is " + square;
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
