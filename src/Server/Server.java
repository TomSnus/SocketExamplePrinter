package Server;

import Entity.Printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by Faßreiter on 10.07.2017.
 */
public class Server {
    Random rng = new Random();
    private static Printer printer = new Printer();
    public static void main(String[] args) throws IOException {



        ServerSocket socket = new ServerSocket(1099);
        new Thread(new PrinterRefiller(printer)).start();
        System.out.println("Printer running...");
        while (true) {
            try {
                Socket s = socket.accept();
                System.out.println("New assignment");
                new Thread(new RequestHandler(s, printer)).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
