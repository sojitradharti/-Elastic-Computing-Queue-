package business;

/**
 * 
 * @author Kamini Prakash
 * 
 */
public class Queue {
    private int rear, front;
    private int size;
    private Request[] queue;
    private int length;

    public Queue(int size) {
        this.size = size;
        this.queue = new Request[this.size];
        front = 0;
        rear = 0;
        length = 0;
    }

    public boolean isFull() {

        return (length == queue.length);
    }

    public boolean isEmpty() {

        return (length == 0);
    }

    public void enqueue(Request i) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            queue[rear] = i;
            rear = (rear + 1) % queue.length;
            length++;
        }
    }

    public Request dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
        }
        Request data = queue[front];
        front = (front + 1) % queue.length;
        length--;
        return data;
    }

    public Request peek() {
        if (!isEmpty()) {
            return queue[front];
        } else {
            return null;
        }
    }

    public int getSize() {
        return size;
    }

    public Request[] getQueue() {
        return queue;
    }

}
