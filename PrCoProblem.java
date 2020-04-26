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

class Producer implements Runnable {

    private final PipedOutputStream out;

    public Producer(PipedOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {

        int i = 1;
        
        try {
            while (i < 101) {
                System.out.println("Generated: " + i);
                out.write(i);
                out.flush();
                i++;
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final PipedInputStream in;

    public Consumer(PipedInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = in.read();
                System.out.println("                        Consumed: " + value);
                if (value == 100)
                    break;
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}