package DataStructures;

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
        queue[head] = item;
        head = (head + 1) % size;
    }

    public T get(int i) {
        if (i >= size)
            throw new IndexOutOfBoundsException();
        int index = (i + head) % size;
        return queue[index];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0 ; i < size ; i++)
            result.append(get(i)).append(", ");
        result.append("]");
        return result.toString();
    }
}
