import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

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