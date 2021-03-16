package synthesizer;

import edu.princeton.cs.algs4.StdAudio;

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
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer((int) Math.round(SR / frequency));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
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
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double front = buffer.dequeue();
        double next = buffer.peek();
        buffer.enqueue(DECAY * (front + next)/2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }
}

