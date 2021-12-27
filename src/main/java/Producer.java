import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class Producer implements Runnable{

    List<Integer> buffer;
    Thread consumer;

    public synchronized void setConsumer(Thread consumer) {

        this.consumer = consumer;
    }

    public Producer(List<Integer> buffer) {

        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        int rnd = 0;

        while (true) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (buffer.size() < 5) {
                rnd = (int) (Math.random() * 6);
                try {
                    buffer.add(rnd);
                    System.out.println("Производитель производит: " + rnd + ". Буфер:" + buffer);
                } catch (ConcurrentModificationException e) {
                    System.out.println("Exception add: " + e);
                } catch (IllegalMonitorStateException e) {
                    System.out.println("Exception add: " + e);
                }
            } else {
                System.out.println("Буфер полный !");
            }
        }
    }
}
