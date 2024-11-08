package utilities;

/**
 * This interface follows the stack data structure to create and manipulate a stack of elements.
 * Uses the last in first out principle
 *
 * @param <E> Element type for stack
 */
public interface StackADT<E> {
	
	/**
     * Adds an element to the top of the stack
     *
     * @throws IllegalArgumentException if the element is null
     * @pre Element is not null
     * @post The stack contains the element at the top
     */
	void push(E element);
	
	/**
     * Removes the element at the top of the stack and returns it
     *
     * @return Element removed from stack
     * @throws NoSuchElementException if the stack is empty
     * @pre Stack cannot be empty
     * @post Element at the top of the stack is removed
     */
	E pop();
	
	/**
     * Returns the element at the top of the stack
     *
     * @return Element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     * @pre Stack cannot be empty
     * @post None
     */
	E peek();
	
	/**
     * Returns 
     *
     * @return True if stack is empty, false otherwise
     * @pre None
     * @post None
     */
	boolean isEmpty();
	
	/**
     * Returns the number of elements in the stack
     *
     * @return size of stack
     * @pre None
     * @post None
     */
	int size();
	
	/**
     * Removes all elements in the stack
     *
     * @pre None
     * @post Stack has no elements
     */
	void clear();
}
