import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class Producer implements Runnable {

    private final PipedOutputStream out;

    public Producer(PipedOutputStream out) {
        //register output
        this.out = out;
    }

    @Override
    public void run() {

        int i = 1;
        
        try {
            while (i < 101) {
                //generate and write to stream
                //by pipedoutputstream implementation, data wont be written if the buffer is full
                System.out.println("Generated: " + i);
                out.write(i);
                out.flush();
                i++;
                //sleep less than consumer
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