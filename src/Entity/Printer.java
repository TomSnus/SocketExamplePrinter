package Entity;

import java.util.concurrent.TimeUnit;

/**
 * Created by Faßreiter on 10.07.2017.
 */
public class Printer {


    public synchronized void printDocuments(Integer amount) throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        System.out.println("Start printing");
        for(int i = 0; i < amount; i ++) {
            System.out.println("Printing: "+i+"/"+amount);
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("Job Done");
    }
}
