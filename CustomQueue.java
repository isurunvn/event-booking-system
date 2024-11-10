public class CustomQueue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;
    private final int capacity;
    private final Class<T> type;  // Add type information

    public CustomQueue(int capacity, Class<T> type) {  // Modified constructor
        this.capacity = capacity;
        this.size = 0;
        this.front = null;
        this.rear = null;
        this.type = type;  // Store the type
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    public int size() { return size; }

    public void enqueue(T item) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Queue is full - Maximum capacity: " + capacity);
        }

        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public T dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T data = front.data;
        front = front.next;
        size--;

        if (isEmpty()) {
            rear = null;
        }

        return data;
    }

    public T peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(type, size);  // Create properly typed array
        Node<T> current = front;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    public void fromArray(T[] array) {
        clear();
        for (T item : array) {
            enqueue(item);
        }
    }

    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}