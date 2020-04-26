import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PrCoProblem {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out, 4);

        Producer producer = new Producer(out);
        Consumer consumer = new Consumer(in);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        out.close();
        in.close();
    }
}