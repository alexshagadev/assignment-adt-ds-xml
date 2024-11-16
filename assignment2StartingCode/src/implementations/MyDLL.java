package implementations;

import utilities.ListADT;
import utilities.Iterator;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element.");
        
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds.");

        Node<E> newNode = new Node<>(toAdd);

        if (index == 0) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } 
            else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } 
        else if (index == size) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } 
        else { // Insert in the middle
            Node<E> current = getNodeAt(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
        
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd);
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null collection.");
        
        Iterator<? extends E> iterator = toAdd.iterator();
        
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return getNodeAt(index).data;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        Node<E> toRemove = getNodeAt(index);
        E data = toRemove.data;

        if (toRemove == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } 
        else if (toRemove == tail) {
            tail = tail.prev;
            
            if (tail != null) tail.next = null;
        } 
        else {
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }

        size--;
        
        return data;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null element.");

        Node<E> current = head;
        
        while (current != null) {
            if (current.data.equals(toRemove)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                } 
                else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                } 
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                
                return current.data;
            }
            current = current.next;
        }
        
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element.");
        
        Node<E> node = getNodeAt(index);
        E oldData = node.data;
        node.data = toChange;
        
        return oldData;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null element.");

        Node<E> current = head;
        
        while (current != null) {
            if (current.data.equals(toFind)) return true;
            current = current.next;
        }
        
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("The provided array cannot be null.");
        }

        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }

        Node<E> current = head;
        int i = 0;
        
        while (current != null) {
            toHold[i++] = current.data;
            current = current.next;
        }

        if (toHold.length > size) {
            for (int j = size; j < toHold.length; j++) {
                toHold[j] = null;
            }
        }

        return toHold;
    }



    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = head;
        int i = 0;
        
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    
    private Node<E> getNodeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds.");

        Node<E> current;
        
        if (index < size / 2) { 
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } 
        else { 
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        
        return current;
    }

    private class DLLIterator implements Iterator<E> {
        private Node<E> current;

        public DLLIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements.");
            
            E data = current.data;
            current = current.next;
            
            return data;
        }
    }
}
