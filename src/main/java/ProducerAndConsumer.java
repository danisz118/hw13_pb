import java.util.ArrayList;
import java.util.List;

public class ProducerAndConsumer {

    public static void main(String[] args) {

        List<Integer> buffer = new ArrayList<>();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread threadConsumer = new Thread(producer);
        Thread threadProducer = new Thread(consumer);

        System.out.println("Начало работы...");

        threadConsumer.start();
        threadProducer.start();

    }
}
