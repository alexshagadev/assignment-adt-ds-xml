package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import utilities.Iterator;

/**
 * Implementation of the QueueADT using a DLL to store the elements of the queue.
 * 
 * @param <E> the type of elements held in this queue
 */
public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> dll;

    public MyQueue() {
        this.dll = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element to the queue.");
        }
        dll.add(toAdd);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (dll.isEmpty()) {
            throw new EmptyQueueException("Cannot dequeue from an empty queue.");
        }
        return dll.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (dll.isEmpty()) {
            throw new EmptyQueueException("Cannot peek into an empty queue.");
        }
        return dll.get(0);
    }

    @Override
    public void dequeueAll() {
        dll.clear();
    }

    @Override
    public boolean isEmpty() {
        return dll.isEmpty();
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return dll.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        Iterator<E> iterator = dll.iterator();
        int position = 1;
        while (iterator.hasNext()) {
            if (iterator.next().equals(toFind)) {
                return position;
            }
            position++;
        }
        return -1; 
    }

    @Override
    public Iterator<E> iterator() {
        return dll.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null || this.size() != that.size()) {
            return false;
        }

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();

        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        return dll.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return dll.toArray(holder);
    }

    @Override
    public boolean isFull() {
        // The queue can't really be full since we're using a DLL to store the elements
        return false;
    }

    @Override
    public int size() {
        return dll.size();
    }
}
