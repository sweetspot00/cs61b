package synthesizer;


import java.util.HashSet;
import java.util.Set;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        buffer = new ArrayRingBuffer((int) Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        //
        //       Make sure that your random numbers are different from each
        //       other.
        Set<Double> rand = new HashSet<>();
        int size = buffer.capacity();
        int fs = buffer.fillCount();
        while (fs > 0) {
            buffer.dequeue();
            fs -= 1;
        }
        while (size > 0) {
            double num = Math.random() - 0.5;
            while (rand.contains(num)) {
                num = Math.random() - 0.5;
            }
            rand.add(num);
            buffer.enqueue(num);
            size -= 1;
        }


    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        double front = buffer.peek();
        buffer.dequeue();
        double next = buffer.peek();
        buffer.enqueue(DECAY * (front + next) / 2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }
}

