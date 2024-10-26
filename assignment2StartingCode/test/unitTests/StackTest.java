package unitTests;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.Iterator;
import implementations.MyStack;

/**
 * @author kitty, maryam
 * @version 3.2 Aug. 28, 2024   
 * Class Description:
 * Test for the Arraylist-based implementation of the StackADT defined in the CPRG 304
 * Assignment 2.
 */

public class StackTest
{
	// Attributes
	private MyStack<Integer> stack;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;

	/**
	 * Initializes a MyStack instance and five Integer instances before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		stack = new MyStack<Integer>();
		one = 111;
		two = 222;
		three = 333;
		four = 444;
		five = 555;
	}

	/**
	 * Cleans up instances used after each test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		stack = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#MyStack()}
	 * to create a stack.
	 */
	@Test
	public void testConstructor()
	{
		boolean expected = true;
		boolean actual = stack != null;
		assertEquals( "Failed to create stack.", expected, actual );
		assertEquals( "Failed to return correct size", 0, stack.size() );
	}

	/**
	 * Test method for {@link implementations.MyStack#clear()}
	 * to clear the items of the stack.
	 */
	@Test
	public void testClear_Size()
	{
		stack.push( one );
		stack.push( two );
		stack.clear();
		
		assertEquals( "Stack size is incorrect ", 0, stack.size() );
	}

	/**
	 * Test method for
	 * {@link implementations.MyStack#contains(java.lang.Object)}
	 * to return true when the stack contains an item.
	 */
	@Test
	public void testContains_True()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );
		
		assertTrue(stack.contains( one ));
		assertTrue(stack.contains( two ));
		assertTrue(stack.contains( three ));
	}

	/**
	 * Test method for
	 * {@link implementations.MyStack#contains(java.lang.Object)}
	 * to return false when the stack doesn't contain the item.
	 */
	@Test
	public void testContains_False()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );

		assertFalse( stack.contains( four ) );
		assertFalse( stack.contains( five ) );
	}

	/**
	 * Test method for
	 * {@link implementations.MyStack#contains(java.lang.Object)}
	 * to throw a NullPointerException when null is passed.
	 */
	@Test
	public void testContains_NullPointerException()
	{
		Integer empty = null;
		stack.push( one );
		stack.push( two );
		stack.push( three );
		try
		{
			stack.contains( empty );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyStack#equals(utilities.MyStack)}
	 * to return true when the two stacks are equal.
	 */
	@Test
	public void testEquals_True()
	{
		MyStack<Integer> stack2 = new MyStack<Integer>();
		boolean expected = true;
		stack.push( one );
		stack.push( two );
		stack.push( three );

		stack2.push( one );
		stack2.push( two );
		stack2.push( three );

		boolean actual = stack.equals( stack2 );
		assertEquals( "Failed to return true", expected, actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyStack#equals(utilities.MyStack)}
	 * to return false when the two stacks are not equal.
	 */
	@Test
	public void testEquals_False()
	{
		MyStack<Integer> stack2 = new MyStack<Integer>();
		boolean expected = false;
		stack.push( two );
		stack.push( one );
		stack.push( three );

		stack2.push( one );
		stack2.push( two );
		stack2.push( three );

		boolean actual = stack.equals( stack2 );
		
		assertEquals( "Failed to return false.", expected, actual );
		
		stack2.clear();
		stack2.push(two);
		stack2.push(one);
		
		actual = stack.equals( stack2 );
		
		assertEquals( "Failed to return false.", expected, actual );
		
	}
	

	/**
	 * Test method for 
	 * {@link implementations.MyStack#isEmpty()}
	 * to return true when the stack is empty.
	 */
	@Test
	public void testIsEmpty_True()
	{
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#isEmpty()}
	 * to return false when the stack is not empty.
	 */
	@Test
	public void testIsEmpty_False()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );
		
		assertFalse(stack.isEmpty());
	}


	/**
	 * Test method for {@link implementations.MyStack#iterator()}
	 * to return an iterator to iterate over items in the stack from top to bottom.
	 */
	@Test
	public void testIterator_NotEmpty()
	{
		int expected1 = 111;
		int expected2 = 222;
		int expected3 = 333;
		int expected4 = 444;
		int expected5 = 555;

		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );

		Iterator<Integer> it = stack.iterator();
		
		assertTrue(it.hasNext());
		
		int actual5 = it.next(); // kitty: flipped 1-5 to 5-1
		int actual4 = it.next();
		int actual3 = it.next();
		int actual2 = it.next();
		int actual1 = it.next();
		
		assertFalse(it.hasNext());
		
		assertEquals( "Stack iterator contained wrong element at position 1 ", expected5, actual5 );
		assertEquals( "Stack iterator contained wrong element at position 2 ", expected4, actual4 );
		assertEquals( "Stack iterator contained wrong element at position 3 ", expected3, actual3 );
		assertEquals( "Stack iterator contained wrong element at position 4 ", expected2, actual2 );
		assertEquals( "Stack iterator contained wrong element at position 5 ", expected1, actual1 );
		
	}
	
	/**
	 * Test method for {@link implementations.MyStack#iterator()}
	 * to return an iterator to iterate over items in an empty stack.
	 */
	@Test
	public void testIterator_Empty()
	{
		Iterator<Integer> it = stack.iterator();
		
		assertFalse(it.hasNext());
		
		try
		{
			it.next();
			fail("Failed to throw NoSuchElementException.");
		}
		catch(NoSuchElementException e)
		{
			assertTrue(true);
		}
		
	}


	/**
	 * Test method for 
	 * {@link implementations.MyStack#peek()}
	 * to return the item on the top of the stack without removing it.
	 * 
	 * @throws EmptyStackException
	 */
	@Test
	public void testPeek_Top() throws EmptyStackException
	{
		int expected5 = 555;

		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );

		int actual5 = stack.peek();
		assertEquals( "Failed to return the top element.", expected5, actual5 );
		assertEquals( "Failed to maintain size.", 5, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#peek()}
	 * to throw EmptyStackException when peeking at the top of an empty stack.
	 */
	@Test
	public void testPeek_Empty()
	{
		try
		{
			stack.peek();
			fail( "Failed to throw EmptyStackException." );
		}
		catch( EmptyStackException e )
		{
			assertTrue( true );
		}
		assertEquals( "Failed to maintain size.", 0, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#pop()}
	 * to remove the item on top of the stack.
	 * 
	 * @throws EmptyStackException
	 */
	@Test
	public void testPop_Top() throws EmptyStackException
	{
		int expected5 = 555;

		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );

		int actual5 = stack.pop();
		assertEquals( "Failed to pop the top element.", expected5, actual5 );
		assertEquals( "Failed to update size.", 4, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#pop()}
	 * to throw EmptyStackException when removing from an empty stack.
	 */
	@Test
	public void testPop_Empty()
	{
		try
		{
			stack.pop();
			fail( "Failed to throw EmptyStackException." );
		}
		catch( EmptyStackException e )
		{
			assertTrue( true );
		}
		assertEquals( "Failed to maintain size.", 0, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#push(java.lang.Object)}
	 * to add multiple items to top of the stack.
	 * 
	 * @throws EmptyStackException
	 */
	@Test
	public void testPush_Top() throws EmptyStackException
	{
		stack.push( one );
		int actual = stack.peek();
		assertEquals( "Failed to push item.", 111, actual );
		assertEquals( "Failed to update size.", 1, stack.size() );

		stack.push( two );
		actual = stack.peek();
		assertEquals( "Failed to push item.", 222, actual );
		assertEquals( "Failed to update size.", 2, stack.size() );

		stack.push( three );
		actual = stack.peek();
		assertEquals( "Failed to push item.", 333, actual );
		assertEquals( "Failed to update size.", 3, stack.size() );

		stack.push( four );
		actual = stack.peek();
		assertEquals( "Failed to push item.", 444, actual );
		assertEquals( "Failed to update size.", 4, stack.size() );

		stack.push( five );
		actual = stack.peek();
		assertEquals( "Failed to push item.", 555, actual );
		assertEquals( "Failed to update size.", 5, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#push(java.lang.Object)}
	 * to throw a NullPointerException when adding a null to the stack.
	 */
	@Test
	public void testPush_NullPointerException()
	{
		Integer empty = null;
		stack.push( one );
		stack.push( two );
		stack.push( three );
		try
		{
			stack.push( empty );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
		assertEquals( "Failed to maintain size.", 3, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#search(java.lang.Object)}
	 * to return the position of an item at the bottom of the stack.
	 */
	@Test
	public void testSearch_Botton()
	{
		int expected = 5;
		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );

		int actual = stack.search( one );
		assertEquals( "Failed to return the correct position.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#search(java.lang.Object)}
	 * to return the position of an item at the top of the stack.
	 */
	@Test
	public void testSearch_Top()
	{
		int expected = 1;
		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );

		int actual = stack.search( five );
		assertEquals( "Failed to return the correct position.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#search(java.lang.Object)}
	 * to return the position of an item in the middle of the stack.
	 */
	@Test
	public void testSearch_Middle()
	{
		int expected = 3;
		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );

		int actual = stack.search( three );
		assertEquals( "Failed to return the correct position.", expected, actual );
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyStack#search(java.lang.Object)}
	 * to return -1 when the item is not found in the stack.
	 */
	@Test
	public void testSearch_NotFound()
	{
		int expected = -1;
		stack.push( one );
		stack.push( two );
		stack.push( three );

		int actual = stack.search( five );
		assertEquals( "Failed to return -1.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link utilities.MyStack#size()}
	 * to return size when stack is empty.
	 */
	@Test
	public void testSize_Empty()
	{
		assertEquals( "Failed to return size.", 0, stack.size() );
	}
	
	/**
	 * Test method for 
	 * {@link utilities.MyStack#size()}
	 * to return size when stack has one item.
	 */
	@Test
	public void testSize_One()
	{
		stack.push( one );
		assertEquals( "SFailed to return size.", 1, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link utilities.MyStack#size()}
	 * to return size when there are many items in the stack.
	 */
	@Test
	public void testSize_AddMany()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );
		stack.push( four );
		stack.push( five );
		assertEquals( "Failed to return size.", 5, stack.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#toArray()}
	 * to return an Object array containing all the items in the stack from top to bottom.
	 */
	@Test
	public void testToArray()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );

		Integer[] array = new Integer[3];
		
		array[0] = three;
		array[1] = two;
		array[2] = one;

		Object[] returnArray = new Integer[3];;
		returnArray = stack.toArray();
		assertArrayEquals( "Failed to convert to array.", array, returnArray );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyStack#toArray(E[])}
	 * returning an array containing all items in the stack when the array passed has sufficient length.
	 */
	@Test
	public void testToArrayEArray_Sufficient()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );

	
		Object[] array = new Integer[3];
		
		array[0] = three;
		array[1] = two;
		array[2] = one;

		Integer[] returnArray = new Integer[3];;
		returnArray = stack.toArray( returnArray );
		assertArrayEquals( "Failed to return the correct array.", array, returnArray );
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyStack#toArray(E[])}
	 * returning an array containing all items in the stack when the array passed doesn't have
	 *  sufficient length.
	 */
	@Test
	public void testToArrayEArray_Insufficient()
	{
		stack.push( one );
		stack.push( two );
		stack.push( three );

	
		Object[] array = new Integer[3];
		
		array[0] = three;
		array[1] = two;
		array[2] = one;

		Integer[] returnArray = new Integer[2];;
		returnArray = stack.toArray( returnArray );
		assertArrayEquals( "Failed to return the correct array.", array, returnArray );
	}
	

	/**
	 * Test method for 
	 * {@link implementations.MyStack#toArray(E[])}
	 * to throw NullPointerException when a null is passed.
	 */@Test
	public void testToArrayNullArray()
	{
		Integer[] returnArray = null;
		try 
		{
			returnArray = stack.toArray(returnArray);
			fail("Failed to throw NullPointerException.");
		} 
		catch (NullPointerException e) 
		{
			assertTrue(true);
		}
	}
	 
	/**
	 * Test method for 
	 * {@link implementations.MyStack#stackOverflow(E[])}
	 * to return false since the stack doesn't have a fixed size .
	 */@Test
	public void testStackoverflow()
	{
		assertFalse(stack.stackOverflow());
	}
}
