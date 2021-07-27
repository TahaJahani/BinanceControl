package Model;

import java.lang.reflect.Array;

public class FixedSizeQueue<T> {
    private T[] queue;
    private int head;
    private int size;

    public FixedSizeQueue(int size) {
        this.size = size;
        this.queue = (T[]) new Object[size];
    }

    public void enqueue(T item) {
        head = (head + 1) % size;
        queue[head] = item;
    }

    public T get(int i) {
        if (i >= size)
            throw new IndexOutOfBoundsException();
        int index = (i + head) % size;
        return queue[index];
    }
}
