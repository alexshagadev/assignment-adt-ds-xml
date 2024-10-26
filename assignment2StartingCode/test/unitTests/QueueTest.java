package unitTests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import implementations.MyQueue;

/**
 * @author kitty, maryam
 * @version 3.2 Aug. 28, 2024  
 * Class Description:
 * Test for DLL-based implementation of the QueueADT defined in the CPRG 304
 * Assignment 2.
 */

public class QueueTest
{
	// Attributes
	private MyQueue<Integer> queue;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;
	
	/**
	 * Initializes a MyQueue instance and five Integer instances before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		queue = new MyQueue<Integer>();
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
		queue = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#MyQueue()}
	 * to create a queue.
	 */
	@Test
	public void testConstructor()
	{
		boolean expected = true;
		boolean actual = queue != null;
		assertEquals( "Failed to create the queue.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#dequeueAll()}
	 * to clear the queue.
	 */
	@Test
	public void testDequeueAll_Size()
	{
		queue.enqueue( one );
		queue.enqueue( two );
		queue.dequeueAll();
		
		assertEquals( "Failed to clear.", 0, queue.size() );
	}

	/**
	 * Test method for
	 * {@link implementations.MyQueue#equals(utilities.QueueADT)}
	 * to return true when two queues are equal.
	 */
	@Test
	public void testEquals_True()
	{
		MyQueue<Integer> queue2 = new MyQueue<Integer>();
		boolean expected = true;
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );

		queue2.enqueue( one );
		queue2.enqueue( two );
		queue2.enqueue( three );

		boolean actual = queue.equals( queue2 );
		assertEquals( "Failed to return true.", expected, actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyQueue#equals(utilities.QueueADT)}
	 * to return false when two queues are not equal.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testEquals_False() throws EmptyQueueException
	{
		MyQueue<Integer> queue2 = new MyQueue<Integer>();
		boolean expected = false;
		queue.enqueue( two );
		queue.enqueue( one );
		queue.enqueue( three );

		queue2.enqueue( one );
		queue2.enqueue( two );
		queue2.enqueue( three );

		boolean actual = queue.equals( queue2 );
		assertEquals( "Failed to return false.", expected, actual );

		
		queue2.dequeue();
		actual = queue.equals( queue2 );
		assertEquals( "Failed to return false", expected, actual );

	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#isEmpty()}
	 * to return true when queue is empty.
	 */
	@Test
	public void testIsEmpty_True()
	{
		boolean expected = true;

		boolean actual = queue.isEmpty();
		assertEquals( "Failed to return true.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#isEmpty()}
	 * to return false when the queue is not empty.
	 */
	@Test
	public void testIsEmpty_False()
	{
		boolean expected = false;
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
		boolean actual = queue.isEmpty();
		assertEquals( "Failed to return false.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#iterator()} 
	 * to return and iterator to iterate over the items of the queue from head to tail.
	 */
	@Test
	public void testIterator()
	{
		int expected1 = 111;
		int expected2 = 222;
		int expected3 = 333;
		int expected4 = 444;
		int expected5 = 555;

		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
		queue.enqueue( four );
		queue.enqueue( five );

		Iterator<Integer> it = queue.iterator();
		
		assertTrue(it.hasNext());
		
		int actual1 = it.next();
		int actual2 = it.next();
		int actual3 = it.next();
		int actual4 = it.next();
		int actual5 = it.next();

		assertFalse(it.hasNext());
		
		assertEquals( "Failed to return item at position.", expected1, actual1 );
		assertEquals( "Failed to return item at position.", expected2, actual2 );
		assertEquals( "Failed to return item at position.", expected3, actual3 );
		assertEquals( "Failed to return item at position.", expected4, actual4 );
		assertEquals( "Failed to return item at position.", expected5, actual5 );
		
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyQueue#iterator()} 
	 * to return and iterator when the queue is empty.
	 */
	@Test
	public void testIterator_EmptyQ()
	{
		Iterator<Integer> it = queue.iterator();
		
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
	 * {@link implementations.MyQueue#peek()}
	 * to return the first item in the queue.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testPeek_First() throws EmptyQueueException
	{
		int expected1 = 111;

		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
		queue.enqueue( four );
		queue.enqueue( five );

		int actual1 = queue.peek();
		assertEquals( "Failed to return the item.", expected1, actual1 );
		assertEquals( "Failed to maintain size.", 5, queue.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#peek()}
	 * to throw EmptyQueueException when queue is empty.
	 */
	@Test
	public void testPeek_Empty()
	{
		try
		{
			queue.peek();
			fail( "Failed to throw EmptyQueueException." );
		}
		catch( EmptyQueueException e )
		{
			assertTrue( true );
		}
		assertEquals( "Failed to maintain size.", 0, queue.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#dequeue()}
	 * to remove the first item in the queue.
	 * 
	 * @throws EmptyQueueException
	 */
	@Test
	public void testDequeue_Front() throws EmptyQueueException
	{
		int expected1 = 111;

		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
		queue.enqueue( four );
		queue.enqueue( five );

		int actual1 = queue.dequeue();
		assertEquals( "Failed to remove the item.", expected1, actual1 );
		assertEquals( "Failed to update size.", 4, queue.size() );
		
		expected1 = 222;
		actual1 = queue.peek();
		
		assertEquals( "Failed to remove the item.", expected1, actual1 );
		
	}

	/**
	 * Test method for
	 * {@link implementations.MyQueue#dequeue()}
	 * to throw EmptyQueueException when removing from an empty queue.
	 */
	@Test
	public void testDequeue_Empty()
	{
		try
		{
			queue.dequeue();
			fail( "Failed to throw EmptyQueueException." );
		}
		catch( EmptyQueueException e )
		{
			assertTrue( true );
		}
		assertEquals( "Failed to maintain size.", 0, queue.size() );
	}

	/**
	 * Test method for
	 * {@link implementations.MyQueue#enqueue(java.lang.Object)}.
	 * to enqueue multiple items to the queue.
	 * @throws EmptyQueueException
	 */
	@Test
	public void testEnqueue() throws EmptyQueueException
	{
		queue.enqueue( one );
		int actual = queue.peek();
		assertEquals( "Failed to enqueueed the correct element", 111, actual );
		assertEquals( "Failed to update size", 1, queue.size() );

		queue.enqueue( two );
		actual = queue.peek();
		assertEquals( "Queue enqueueed wrong element to head of Queue ", 111, actual );
		assertEquals( "Queue size is incorrect ", 2, queue.size() );

		queue.enqueue( three );
		actual = queue.peek();
		assertEquals( "Queue enqueueed wrong element to head of Queue ", 111, actual );
		assertEquals( "Queue size is incorrect ", 3, queue.size() );

		queue.enqueue( four );
		actual = queue.peek();
		assertEquals( "Queue enqueueed wrong element to head of Queue ", 111, actual );
		assertEquals( "Queue size is incorrect ", 4, queue.size() );

		queue.enqueue( five );
		actual = queue.peek();
		assertEquals( "Queue enqueueed wrong element to head of Queue ", 111, actual );
		assertEquals( "Queue size is incorrect ", 5, queue.size() );
	}

	/**
	 * Test method for
	 * {@link implementations.MyQueue#enqueue(java.lang.Object)}
	 * to throw a NullPointerException when a null is passed.
	 */
	@Test
	public void testEnqueue_NullPointerException()
	{
		Integer empty = null;
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
		try
		{
			queue.enqueue( empty );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
		assertEquals( "Failed to maintain queue size.", 3, queue.size() );
	}

	/**
	 * Test method for 
	 * {@link utilities.QueueADT#size()}
	 * to return the size of the queue when the queue has one item.
	 */
	@Test
	public void testSize_AddOneToEmpty()
	{
		queue.enqueue( one );
		assertEquals( "Failed to update queue size.", 1, queue.size() );
	}
	
	/**
	 * Test method for 
	 * {@link utilities.QueueADT#size()}
	 * to return the size of the queue when the queue has multiple items.
	 */
	@Test
	public void testSize_AddMany()
	{
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
		queue.enqueue( four );
		queue.enqueue( five );
		assertEquals( "Failed to update queue size.", 5, queue.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#toArray()}
	 * to return an Object array storing all items in the queue from head to first.
	 */
	@Test
	public void testToArray()
	{
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );

		Integer[] expectedArray = new Integer[3];
		
		expectedArray[0] = one;
		expectedArray[1] = two;
		expectedArray[2] = three;

		Object[] actualArray = queue.toArray();
		assertArrayEquals( "Failed to convert queue to array.", expectedArray, actualArray );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyQueue#toArray(E[])}
	 * to return an array storing all items in the queue from head to first when the array passed has sufficient length.
	 */
	@Test
	public void testToArrayEArray_SufficientArray()
	{
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
	
		Object[] expectedArray = new Integer[3];
		
		expectedArray[0] = one;
		expectedArray[1] = two;
		expectedArray[2] = three;
		
		Integer[] actualArray = new Integer[3];;
		actualArray = queue.toArray( actualArray );
		assertArrayEquals( "Failed to convert queue to array.", expectedArray, actualArray );
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyQueue#toArray(E[])}
	 * to return an array storing all items in the queue from head to first when the array passed has insufficient length.
	 */
	@Test
	public void testToArrayEArray_InsufficientArray()
	{
		queue.enqueue( one );
		queue.enqueue( two );
		queue.enqueue( three );
	
		Object[] expectedArray = new Integer[3];
		
		expectedArray[0] = one;
		expectedArray[1] = two;
		expectedArray[2] = three;
		
		Integer[] actualArray = new Integer[2];;
		actualArray = queue.toArray( actualArray );
		assertArrayEquals( "Failed to convert queue to array.", expectedArray, actualArray );
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyQueue#toArray(E[])}
	 * to throw NullPointerException when a null is passed.
	 */@Test
	public void testToArrayNullArray()
	{
		Integer[] returnArray = null;
		try 
		{
			returnArray = queue.toArray(returnArray);
			fail("Failed to throw NullPointerException.");
		} 
		catch (NullPointerException e) 
		{
			assertTrue(true);
		}
	}
	 
	 /**
	  * Test method for 
	  * {@link implementations.MyQueue#isFull()}
	  * to return false.
	  */@Test
		public void testIsFull()
		{
			assertFalse(queue.isFull());
			
			for( int i = 0; i < 500; i++ )
			{
				queue.enqueue( i );
			}
	
			assertFalse(queue.isFull());
		} 
	
	 /**
	  * Test method for 
	  * {@link implementations.MyQueue#contains(java.lang.Object)}
	  * to return true when item is found.
	  */@Test
		public void testContains_Found()
		{
		  	queue.enqueue( one );
			queue.enqueue( two );
			queue.enqueue( three );
			
			assertTrue( queue.contains( one ) );
			assertTrue( queue.contains( two ) );
			assertTrue( queue.contains( three ) );
			
		} 
	  
	  /**
	  * Test method for 
	  * {@link implementations.MyQueue#contains(java.lang.Object)}
	  * to return true when item is not found.
	  */@Test
		public void testContains_NotFound()
		{
		  	queue.enqueue( one );
			queue.enqueue( two );
			queue.enqueue( three );
			
			assertFalse( queue.contains( four ) );
			assertFalse( queue.contains( five ) );
			
		} 
	  
	  /**
	  * Test method for 
	  * {@link implementations.MyQueue#contains(java.lang.Object)}
	  * to throw NullPointerException when null is passed.
	  */@Test
		public void testContains_NullPointerException()
		{
		  	try
		  	{
		  		queue.contains( null );
		  		fail("Failed to throw NullPointerException.");
		  	}
		  	catch(NullPointerException e)
		  	{
		  		assertTrue(true);
		  	}
		  	
			assertFalse( queue.contains( five ) );
			
		} 
	  
	  /**
	  * Test method for 
	  * {@link implementations.MyQueue#search(java.lang.Object)}
	  * to return the index of the item when the item exists.
	  */@Test
		public void testSearch_Found()
		{
		  	queue.enqueue( one );
			queue.enqueue( two );
			queue.enqueue( three );
			
			int expectedIndex = 1;
			int actualIndex = queue.search( one ); 
			
			assertEquals("Failed to find item", expectedIndex, actualIndex );
			
			expectedIndex = 2;
			actualIndex = queue.search( two ); 
			assertEquals("Failed to find item", expectedIndex, actualIndex );
			
			expectedIndex = 3;
			actualIndex = queue.search( three ); 
			assertEquals("Failed to find item", expectedIndex, actualIndex );
			
		} 
	  
	  /**
	  * Test method for 
	  * {@link implementations.MyQueue#search(java.lang.Object)}
	  * to return the index of the item when the item doesn't exist.
	  */@Test
		public void testSearch_NotFound()
		{
			int expectedIndex = -1;
			int actualIndex = queue.search( one ); 
			assertEquals("Failed to return -1 for not found", expectedIndex, actualIndex);
			
		  	queue.enqueue( one );
			queue.enqueue( two );
			queue.enqueue( three );
			
			actualIndex = queue.search( five ); 
			
			assertEquals("Failed to return -1 for not found", expectedIndex, actualIndex);
		} 
 
}
