package Entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Faßreiter on 10.07.2017.
 */
public class Printer {
    private final int PAPER_MAX = 200;
    private volatile int paper = PAPER_MAX;
    private ReentrantLock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();

    public void printDocuments(Integer amount) throws InterruptedException {
        lock.lock();
        try {

            while(amount > paper) {
                System.out.println("Waiting: amount="+amount + " paper="+paper );
                empty.await();
            }

            TimeUnit.SECONDS.sleep(2);
            System.out.println("Start printing");
            for(int i = 0; i < amount; i ++) {
                System.out.println("Printing: "+i+"/"+amount);
                paper--;
                TimeUnit.MILLISECONDS.sleep(50);
            }
            System.out.println("Job Done");
            full.signal();
        } finally {
            lock.unlock();
        }

    }

    public void refillPaper() throws InterruptedException {
        lock.lock();
        try {

            while(paper >= PAPER_MAX){
                full.await();
            }

            int newPaper = PAPER_MAX-paper;
            System.out.println("Refilling " + newPaper);
            paper+=newPaper;
            empty.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
