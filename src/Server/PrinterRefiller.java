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
        try {
            while (true) {
                printer.refillPaper();
                TimeUnit.SECONDS.wait(3);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
