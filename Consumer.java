import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

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