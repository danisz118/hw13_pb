import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class Consumer implements Runnable{

    List<Integer> buffer;
    Thread producer;

    public synchronized void setProducer(Thread producer) {

        this.producer = producer;
    }

    public Consumer(List<Integer> buffer) {

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

            if (buffer.size() > 0) {
                rnd = (int) (Math.random() * 6);
                System.out.println("Производитель спрашивает: " + rnd);
                for (int i = 0; i < buffer.size(); i++) {
                    try {
                        if (buffer.get(i) == null) {
                            buffer.remove(i);
                            break;
                        }
                        if (buffer.get(i) == rnd) {
                            buffer.remove(i);
                            System.out.println("Потребитель использует: " + rnd + ". Буфер:" + buffer);
                        }
                    } catch (ConcurrentModificationException e) {
                        System.out.println("Exception del: " + e);
                    } catch (NullPointerException e) {
                        System.out.println("Exception del: " + e);
                    }
                }
            } else {
                System.out.println("Буфер пустой!");
            }
        }
    }
}
