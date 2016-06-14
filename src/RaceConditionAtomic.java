import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author László Hágó
 * @version 1.0
 * @since 2016-06-14
 */
public class RaceConditionAtomic {
    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomic = new AtomicInteger(0);

        Runnable r = () -> {

            for (int i = 0 ; i < 1000 ; i++) {
                //this is the atomic version of i++
                atomic.incrementAndGet();
            }
        };

        Thread[] threads = new Thread[1000];
        for (int i = 0 ; i < threads.length ; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }

        for (int i = 0 ; i < threads.length ; i++) {
            threads[i].join();
        }

        System.out.println("Value = " + atomic.get());
    }
}
