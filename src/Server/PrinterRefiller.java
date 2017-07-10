package Server;

import Entity.Printer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Faßreiter on 10.07.2017.
 */
public class PrinterRefiller implements Runnable{

    private Printer printer;

    public PrinterRefiller(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.println("Printrefiller started...");
        try {
            while (true) {
                printer.refillPaper();


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
