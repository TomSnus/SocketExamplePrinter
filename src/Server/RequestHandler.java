package Server;

import Entity.Printer;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by Faßreiter on 10.07.2017.
 */
public class RequestHandler implements Runnable{
    private Socket s;
    private Printer printer;

    public RequestHandler(Socket s, Printer printer) {
        this.s = s;
        this.printer = printer;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = s.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = s.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);

            String text = reader.readLine();

            printer.printDocuments(Integer.valueOf(text));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
