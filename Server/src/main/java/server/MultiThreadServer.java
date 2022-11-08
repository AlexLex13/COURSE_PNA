package server;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(8888);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();  // сервер принимает запрос на подключение клиента
                System.out.println(">>> " + "Client number " + counter + " started!");
                ServerClientThread sct = new ServerClientThread(serverClient, counter); // отправляем запрос в отдельный поток
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
