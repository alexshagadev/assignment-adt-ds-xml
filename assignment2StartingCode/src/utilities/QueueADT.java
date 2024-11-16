package utilities;

import exceptions.EmptyQueueException;
import java.io.*;

/**
 * This is the professional Queue Interface for Object-Oriented Programming 3
 * (CRPG 304) at the SAIT Polytechnic. This Queue embodies all the standard
 * Queue operations, and includes several helper methods that will give the data
 * structure more flexibility and use.
 */
public interface QueueADT<E> extends Serializable
{
	/**
	 * Enqueue will place the added item at the last position in the queue. This
	 * method will not allow <code>null</code> values to be added to the Queue.
	 * 
	 * @param toAdd the item to be added to the Queue.
	 * @throws NullPointerException raised when a <code>null</code> object is placed
	 *                              in the Queue.
	 */
	public void enqueue( E toAdd ) throws NullPointerException;

	/**
	 * Dequeue will remove the first item that was placed in the Queue.
	 * 
	 * @return the first item in the Queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	public E dequeue() throws EmptyQueueException;

	/**
	 * Peek provides a reference to the first item in the queue without removing
	 * from the queue.
	 * 
	 * @return the first item in the queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	public E peek() throws EmptyQueueException;
	
	/**
	 * dequeueAll removes all items in the queue.
	 */
	public void dequeueAll();

	/**
	 * Returns <code>true</code> when the queue contains no items.
	 * 
	 * @return <code>true</code> when queue length is zero (0).
	 */
	public boolean isEmpty();
	
	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e
	 * such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param toFind
	 *            element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             support null elements.
	 */
	public boolean contains( E toFind ) throws NullPointerException;

	/**
	 * Returns the 1-based position where an object is on this queue. If the
	 * object o occurs as an item in this queue, this method returns the
	 * distance from the front of the queue of the occurrence nearest the front of
	 * the queue; the first item on the stack is considered to be at distance
	 * 1. The equals method is used to compare o to the items in this queue.
	 * 
	 * @param toFind
	 *            the desired object.
	 * @return the 1-based position from the top of the queue where the object
	 *         is located; the return value -1 indicates that the object is not
	 *         on the queue.
	 */
	public int search( E toFind );

	/**
	 * Returns an iterator over the elements in this queue in proper sequence.
	 * 
	 * @return an iterator over the elements in this queue in proper sequence.
	 */
	public Iterator<E> iterator();

	/**
	 * Used to compare two Queue ADT's. To be equal two queues must contain equal
	 * items appearing in the same order.
	 * 
	 * @param that the Queue ADT to be compared to this queue.
	 * @return <code>true</code> if the queues are equal.
	 */
	public boolean equals( QueueADT<E> that );

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	public Object[] toArray();

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the Collection.toArray(Object[]) method.
	 * 
	 * @param toHold the array into which the elements of this queue are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return an array containing the elements of this queue.
	 * @throws NullPointerException if the specified array is null.
	 */
	public E[] toArray( E[] holder ) throws NullPointerException;

	/**
	 * Returns true if the number of items in the queue equals the
	 * length. This operation is only implement when a fixed length queue is
	 * required.
	 * 
	 * @return <code>true</code> if queue is at capacity.
	 */
	public boolean isFull();

	/**
	 * Returns the length of the current queue as an integer value.
	 * 
	 * @return the current size to the queue as an integer.
	 */
	public int size();
}
