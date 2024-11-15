package implementations;

import utilities.StackADT;
import utilities.Iterator;
import utilities.MyArrayList;
import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;


public class MyStack<E> implements StackADT<E>, Serializable {
    private static final long serialVersionUID = 1L;
    private MyArrayList<E> elements;
    private static final int MAX_CAPACITY = 1000;

    public MyStack() {
        elements = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        if (stackOverflow()) {
            throw new IllegalStateException("Stack overflow");
        }
        elements.add(toAdd);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public E[] toArray(E[] holder) {
        return elements.toArray(holder);
    }

    @Override
    public boolean contains(E toFind) {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null element");
        }
        return elements.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null element");
        }
        for (int i = elements.size() - 1; i >= 0; i--) {
            if (elements.get(i).equals(toFind)) {
                return elements.size() - i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (this == that) return true;
        if (that == null || that.size() != this.size()) return false;

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
    public int size() {
        return elements.size();
    }

    @Override
    public boolean stackOverflow() {
        return elements.size() >= MAX_CAPACITY;
    }

    private class StackIterator implements Iterator<E> {
        private int cursor;

        public StackIterator() {
            cursor = elements.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            return elements.get(cursor--);
        }
    }
}
