package synthesizer;
import java.util.Iterator;



public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
//    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        super();
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    private class ArrarRingIterator implements Iterator<T> {
        private int ptr;

        public ArrarRingIterator() {
            ptr = first;
        }

        public boolean hasNext() {
            return (ptr != last);
        }

        public T next() {
            T res = rb[ptr];
            ptr = (ptr + 1) % capacity;
            return res;
        }
    }

    public Iterator<T> iterator() {
        return new ArrarRingIterator();
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {

        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            last = (last + 1) % capacity;
            this.fillCount += 1;
        }
//        int superFC = this.fillCount();
//        int superCC = this.capacity();
//        boolean isE = this.isEmpty();
//        boolean isF = this.isFull();

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {

        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T res = rb[first];
            rb[first] = null;
            first = (first + 1) % capacity;
            fillCount -= 1;
            return res;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {

        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }


    }
}
