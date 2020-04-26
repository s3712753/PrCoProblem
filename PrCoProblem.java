import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PrCoProblem {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        //create pipe with a buffer of a size of 4
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out, 4);

        //create producer and consumer
        Producer producer = new Producer(out);
        Consumer consumer = new Consumer(in);

        //create and start their threads
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();

        //wait for threads to finish and clean up
        producerThread.join();
        consumerThread.join();
        out.close();
        in.close();
    }
}