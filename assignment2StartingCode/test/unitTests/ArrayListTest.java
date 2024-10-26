package unitTests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import implementations.MyArrayList;
import implementations.MyDLL;
import utilities.Iterator;

/**
 * @author kitty, maryam
 * @version 3.2 Aug. 28, 2024  
 * Class Description:
 * Test for the array-based implementation of the ListADT defined in the CPRG304
 * Assignment 2.
 */

public class ArrayListTest
{
	// Attributes
	private MyArrayList<Integer> myList;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;

	/**
	 * Initializes a new MyArrayList instance and five Integer instances before each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		myList = new MyArrayList<>();
		one = 1;
		two = 2;
		three = 3;
		four = 4;
		five = 5;

	}

	/**
	 * Cleans up instances used after each test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		myList = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
	}


	/**
	 * Test method for constructor of MyArrayList class.
	 */
	@Test
	public void testConstructor()
	{
		boolean expected = true;
		boolean actual = myList != null;
		assertEquals( "Failed to create the list ", actual, expected );
		assertEquals( "Failed to return correct size", 0, myList.size() );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#add(int, java.lang.Object)}
	 * to add item to an empty list and return true.
	 */
	@Test
	public void testAddIntE_returnTrue()
	{
		boolean expectedBoolean = true;
		boolean actualBoolean = myList.add( 0, one );

		assertEquals( " ailed to return true.", expectedBoolean, actualBoolean );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(int, java.lang.Object)}
	 * to add null to a list and throw NullPointerException.
	 */
	@Test
	public void testAddIntE_NullPointerException()
	{
		one = null;
		try
		{
			myList.add( 0, null );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(int, java.lang.Object)}
	 * to add an item to a positive invalid index and throw IndexOutOfBoundsExceptionx.
	 */
	@Test
	public void testAddIntE_IndexOutOfBoundsException_Positive()
	{
		myList.add( 0, one );
		myList.add( 1, two );
		myList.add( 2, three );

		try
		{
			myList.add( 4, four );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}
	
	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(int, java.lang.Object)}
	 * to add an item to a positive invalid index and throw IndexOutOfBoundsException.
	 */
	@Test
	public void testAddIntE_IndexOutOfBoundsException_Negative()
	{
		try
		{
			myList.add( -1, one );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)}
	 * to add an item to an empty list an position 0.
	 */
	@Test
	public void testAddIntE_ItemAddedCorrectly_Empty()
	{
		int expectedValue = 1;
		int expectedSize = 1;

		assertTrue(myList.add( 0, one ));
		int actualValue = myList.get( 0 );
		int actualSize = myList.size();
		assertEquals( "Failed to add to the correct position", expectedValue, actualValue );
		assertEquals( "Failed to update size", expectedSize, actualSize);
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)}
	 * to add an item to the tail of the list.
	 */
	@Test
	public void testAddIntE_ItemAddedCorrectly_Tail()
	{
		int expectedSize = 3;
		assertTrue(myList.add( 0, one ));
		assertTrue(myList.add( 1, two ));
		assertTrue(myList.add( 2, three ));
		
		int actualSize = myList.size();
		assertEquals( "Failed to update size", expectedSize, actualSize);
		
		for( int i = 0; i < 3; i++ )
		{
			int expectedValue = i + 1;
			int actualValue = myList.get( i );
			assertEquals( "Failed to add to the correct position.", expectedValue, actualValue );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)}
	 * to add an item to the head of the list.
	 */
	@Test
	public void testAddIntE_ItemAddedCorrectly_Head()
	{
		int expectedSize = 3;
		
		assertTrue(myList.add( 0, three ));
		assertTrue(myList.add( 0, two ));
		assertTrue(myList.add( 0, one ));
		
		int actualSize = myList.size();
		assertEquals( "Failed to update size", expectedSize, actualSize);
		
		for( int i = 0; i < myList.size(); i++ )
		{
			int expectedValue = i + 1;
			int actualValue = myList.get( i );
			assertEquals( "Failed to add item to the correct position.", expectedValue, actualValue );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)} 
	 * to add 100 items to the list (beyond default capacity of 10).
	 */
	@Test
	public void testAddE_ResizeArray()
	{
		int expectedSize = 100;
		
		for( int i = 0; i < 100; i++ )
		{
			assertTrue(myList.add( i + 1 ));
		}
		
		int actualSize = myList.size();
		assertEquals( "Failed to update size", expectedSize, actualSize);
		
		for( int i = 0; i < myList.size(); i++ )
		{
			int expectedValue = i + 1;
			int actualValue = myList.get( i );
			assertEquals( "Failed to add to the correct position.", expectedValue, actualValue );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)}
	 * to add item to an empty list and return true.
	 */
	@Test
	public void testAddE_returnTrue()
	{
		boolean expectedBoolean = true;
		boolean actualBoolean = myList.add( one );

		assertEquals( "Failed to return true.", expectedBoolean, actualBoolean );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(int, java.lang.Object)}
	 * to add a null to a list and throw NullPointerException.
	 */
	@Test
	public void testAddE_NullPointerException()
	{
		try
		{
			myList.add( null );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)}
	 * to add one item to empty list.
	 */
	@Test
	public void testAddE_ItemAddedCorrectly_OneElement()
	{
		int expectedValue = 1;
		int expectedSize = 1;

		assertTrue(myList.add( one ));
		int actualValue = myList.get( 0 );
		int actualSize = myList.size();
		assertEquals( "Failed to add to the correct position.", expectedValue, actualValue );
		assertEquals( "Failed to update size", expectedSize, actualSize);
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#add(java.lang.Object)}
	 * to add multiple items.
	 */
	@Test
	public void testAddE_ItemAddedCorrectly_MultipleElements()
	{
		int expectedSize = 3;

		assertTrue( myList.add( one ) );
		assertTrue( myList.add( two ) );
		assertTrue( myList.add( three ) );

		for( int i = 0; i < myList.size(); i++ )
		{
			int expectedValue = i + 1;
			int actualValue = myList.get( i );
			assertEquals( "Failed to add to the correct positon.", expectedValue, actualValue );
		}
		int actualSize = myList.size();
		assertEquals( "Failed to update size.", expectedSize, actualSize );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#addAll(utilities.List)}
	 * to add an ArrayList to the list.
	 */
	@Test
	public void testAddAll_ArrayList()
	{
		boolean expectedReturn = true;
		MyArrayList<Integer> intArray = new MyArrayList<>();
		intArray.add( three );
		intArray.add( four );
		intArray.add( five );

		myList.add( one );
		myList.add( two );
		
		boolean actualReturn = myList.addAll( intArray );
		assertEquals( "Failed to return true.", expectedReturn, actualReturn );
		
		int expectedSize = 5;
		int actualSize = myList.size();
		assertEquals( "Failed to update size.", expectedSize, actualSize );
		
		for( int i = 0; i < myList.size(); i++ )
		{
			int expectedValue = i + 1;
			int actualValue = myList.get( i );
			assertEquals( "Failed to add item to the correct position.", expectedValue, actualValue );
		}
	}
	
	/**
	 * Test method for
	 * {@link implementations.MyArrayList#addAll(utilities.List)}
	 * to add a DLL to the list.
	 */
	@Test
	public void testAddAll_DLL()
	{
		boolean expectedReturn = true;
		MyDLL<Integer> intList = new MyDLL<>();
		intList.add( three );
		intList.add( four );
		intList.add( five );

		myList.add( one );
		myList.add( two );
		
		boolean actualReturn = myList.addAll( intList );
		assertEquals( "Failed to return true.", expectedReturn, actualReturn );
		
		int expectedSize = 5;
		int actualSize = myList.size();
		assertEquals( "Failed to update size.", expectedSize, actualSize );
		
		for( int i = 0; i < myList.size(); i++ )
		{
			int expectedValue = i + 1;
			int actualValue = myList.get( i );
			assertEquals( "Failed to add to the correct position.", expectedValue, actualValue );
		}
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#addAll(utilities.List)}
	 * to add a null to the list to throw a NullPointerException.
	 */
	@Test
	public void testAddAll_NullPointerException()
	{

		myList.add( one );
		myList.add( two );
		try
		{
			myList.addAll( null );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for {@link implementations.MyArrayList#clear()}
	 * to clear a non-empty list.
	 */
	@Test
	public void testClear_Size()
	{
		int expected = 0;

		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.clear();

		int actual = myList.size();
		assertEquals( "Fialed to updated size.", expected, actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#contains(java.lang.Object)}
	 * to check for the existence of an item and return true.
	 */
	@Test
	public void testContains_returnTrue()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		boolean actual = myList.contains( three );
		assertTrue( "Failed to return true.", actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#contains(java.lang.Object)}
	 * to check for the existence of an item and return false.
	 */
	@Test
	public void testContains_returnFalse()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		boolean actual = myList.contains( five );
		assertFalse( "Failed to return false.", actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#contains(java.lang.Object)}
	 * to pass a null item and throw NullPointerException.
	 */
	@Test
	public void testContains_NullPointerException()
	{
		try
		{
			myList.contains( null );
			fail( "Failed to throw NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to return the only item in list.
	 */
	@Test
	public void testGetInt_One()
	{
		myList.add( one );

		int expected = 1;
		int actual = myList.get( 0 );

		assertEquals( "Failed to get the correct item.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to get the first item in the list.
	 */
	@Test
	public void testGetInt_Head()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		int expected = 1;
		int actual = myList.get( 0 );
		assertEquals( "Failed to get the correct item.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to get the last item in the list. 
	 */
	@Test
	public void testGetInt_Tail()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		int expected = 4;
		int actual = myList.get( 3 );
		assertEquals( "Failed to get the correct item.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to get the middle item in the list.
	 */
	@Test
	public void testGetInt_Middle()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add(five);

		int expected = 3;
		int actual = myList.get( 2 );
		assertEquals( "Failed to get the correct item from list.", expected, actual );
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to get an item in position 0 of an empty list and throw an IndexOutOfBoundsException.
	 */
	@Test
	public void testGetInt_IndexOutOfBoundsException_Empty()
	{
		try
		{
			myList.get( 0 );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to throw an IndexOutOfBoundsException when getting from an invalid positive index.
	 */
	@Test
	public void testGetInt_IndexOutOfBoundsException_PositiveIndex() 
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );

		try
		{
			myList.get( 4 );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#get(int)}
	 * to throw an IndexOutOfBoundsException when getting from an invalid negative index.
	 */
	@Test
	public void testGetInt_IndexOutOfBoundsException_NegativeIndex() // need test for empty
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );

		try
		{
			myList.get( -1 );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#isEmpty()}
	 * to return true when list is empty.
	 */
	@Test
	public void testIsEmpty_True()
	{
		boolean expected = true;
		boolean actual = myList.isEmpty();
		assertEquals( "Failed to return true.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#isEmpty()}
	 * to return false when list is not empty.
	 */
	@Test
	public void testIsEmpty_False()
	{
		myList.add( one );
		boolean expected = false;
		boolean actual = myList.isEmpty();
		assertEquals( "Failed to return false.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#iterator()}
	 * to get an iterator for an empty list.
	 */
	@Test
	public void testIterator_Empty()
	{
		boolean expectedBoolean = false;
		
		Iterator<Integer> it = myList.iterator();
		boolean actualBoolean = it.hasNext();
		assertEquals( "Failed to return false.", expectedBoolean, actualBoolean);
		try
		{
			it.next();
			fail( "Failed to throw NoSuchElementException." );
		}
		catch( NoSuchElementException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#iterator()}
	 * to get an iterator items in a list with multiple items.
	 */
	@Test
	public void testIterator()
	{
		boolean expectedBoolean = true;
		
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );

		Iterator<Integer> it = myList.iterator();
		boolean actualBoolean = it.hasNext();
		assertEquals( "Failed to reutrn true.", expectedBoolean, actualBoolean);
		int expectedValue = one;
		while( it.hasNext() )
		{
			int actualValue = it.next();
			assertEquals( "Failed to return correct item.", expectedValue, actualValue );
			expectedValue++;
		}
		expectedBoolean = false;
		actualBoolean = it.hasNext();
		assertEquals( "Failed to return false.", expectedBoolean, actualBoolean);
		
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to remove the only item in the list.
	 */
	@Test
	public void testRemoveInt_OneElement()
	{
		myList.add( one );

		int expected = 1;
		int actual = myList.remove( 0 );
		
		assertEquals( "Failed to remove the correct item.", expected, actual );
		
		int expectedSize = 0;
		int actualSize = myList.size();
		assertEquals( "Failed to update size.", expectedSize, actualSize );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to remove the item at the head of the list.
	 */
	@Test
	public void testRemoveInt_Head()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		
		int expected1 = 1;
		int actual1 = myList.remove( 0 );

		int expected2 = 2;
		int actual2 = myList.get( 0 );
		
		int expectedSize = 3;
		int actualSize = myList.size();

		assertEquals( "Failed to remove the correct item.", expected1, actual1 );
		assertEquals( "Failed to remove the correct item.", expected2, actual2 );
		assertEquals( "Failed to update size.", expectedSize, actualSize );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to remove the item at the tail of the list.
	 */
	@Test
	public void testRemoveInt_Tail()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		int expected1 = 4;
		int actual1= myList.remove( 3 );

		int expected2 = 3;
		int actual2 = myList.get( 2 );


		int expectedSize = 3;
		int actualSize = myList.size();
		
		assertEquals( "Failed to remove the correct item.", expected1, actual1 );
		assertEquals( "Failed to remove the correct item.", expected2, actual2 );
		assertEquals( "Failed to update size.", expectedSize, actualSize );
		
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to remove the item at neither head nor tail of the list.
	 */
	@Test
	public void testRemoveInt_NotHeadOrTail()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );

		int expected1 = 3;
		int actual1 = myList.remove( 2 );

		int expected2 = 2;
		int actual2 = myList.get( 1 );


		int expected3 = 4;
		int actual3 = myList.get( 2 );
		
		int expectedSize = 4;
		int actualSize = myList.size();
		
		assertEquals( "Failed to remove the correct item.", expected1, actual1 );
		assertEquals( "Failed to remove the correct item.", expected2, actual2 );
		assertEquals( "Failed to remove the correct item.", expected3, actual3 );
		assertEquals( "Failed to update size.", expectedSize, actualSize );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to throw an IndexOutOfBoundsException on an empty list.
	 */
	@Test
	public void testRemoveInt_IndexOutOfBoundsException_Empty()
	{
		try
		{
			myList.remove( 0 );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to throw an IndexOutOfBoundsException on a list with multiple items.
	 */
	@Test
	public void testRemoveInt_IndexOutOfBoundsException_PositiveIndex()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );

		try
		{
			myList.remove( 4 );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
		
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to throw an IndexOutOfBoundsException on a list with multiple items.
	 */
	@Test
	public void testRemoveInt_IndexOutOfBoundsException_NegativeIndex()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );

		
		try
		{
			myList.remove( -1 );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(E)}
	 * to remove from a list with only one item.
	 */
	@Test
	public void testRemoveE_OneElement()
	{
		int expected = 1;
		int expectedSize = 0;
		myList.add( one );
		int actual = myList.remove( one );
		int actualSize = myList.size();

		assertEquals( "Failed to remove the correct item.", expected, actual );
		assertEquals( "Failed to update size.", expectedSize, actualSize );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to remove a found item from a list with multiple items.
	 */
	@Test
	public void testRemove_Found()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		int expected1 = 1;
		int actual1 = myList.remove( one );

		int expected2 = 2;
		int actual2 = myList.get( 0 );

		int expectedSize = 3;
		int actualSize = myList.size();
		
		assertEquals( "Failed to remove the correct item.", expected1, actual1 );
		assertEquals( "Failed to remove the correct item.", expected2, actual2 );
		assertEquals( "Size was not updated correctly.", expectedSize, actualSize );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(int)}
	 * to remove a not found item.
	 */
	@Test
	public void testRemove_NotFound()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		Integer expected1 = null;
		Integer actual1 = myList.remove( five );

		int expected2 = 1;
		int actual2 = myList.get( 0 );

		assertEquals( "Failed to remove the correct item.", expected1, actual1 );
		assertEquals( "Failed to remove the correct item.", expected2, actual2 );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#remove(E)}
	 * to throw a NullPointerException when removing a null object from the list.
	 */
	@Test
	public void testRemoveE_NullPointerException()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		three = null;

		try
		{
			myList.remove( three );
			fail( "Failed to throw a NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to set the item at neither head nor tail position in the list to a new item.
	 */
	@Test
	public void testSet_SpecificIndex()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );

		int toChange = 33 ;

		int expected1 = 3;
		int actual1 = myList.set( 2, toChange );

		int expected2 = 33;
		int actual2 = myList.get( 2 );

		assertEquals( "Failed to return the correct changed item.", expected1, actual1 );
		assertEquals( "Failed to change the item at the specified posiiton.", expected2, actual2 );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to set the item at the head of the list to a new item.
	 */
	@Test
	public void testSet_Head()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );

		int toChange = 11 ;

		int expected1 = 1;
		int expected2 = 11;
		int actual1 = myList.set( 0, toChange );
		int actual2 = myList.get( 0 );

		assertEquals( "Failed to return the correct changed item.", expected1, actual1 );
		assertEquals( "Failed to change the item at the specified posiiton.", expected2, actual2 );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to set the item at tail of the list to a new item.
	 */
	@Test
	public void testSet_Tail()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );

		int toChange =  55;

		int expected1 = 5;
		int actual1 = myList.set( 4, toChange );

		int expected2 = 55;
		int actual2 = myList.get( 4 );

		assertEquals( "Failed to return the correct changed item.", expected1, actual1 );
		assertEquals( "Failed to change the item at the specified posiiton.", expected2, actual2 );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to throw a NullPointerException when a null is passed for the item to set.
	 */
	@Test
	public void testSet_NullPointerException()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );

		try
		{
			myList.set( 2, null );
			fail( "Failed to throw the NullPointerException." );
		}
		catch( NullPointerException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to throw an IndexOutOfBoundsException on an empty list.
	 */
	@Test
	public void testSet_IndexOutOfBoundsException_Empty()
	{
		int toChange = 11;

		try
		{
			myList.set( 0, toChange );
			fail( "Failed to throw the IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to throw IndexOutOfBoundsException on a positive invalid index.
	 */
	@Test
	public void testSet_IndexOutOfBoundsException_PositiveIndex()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );

		int toChange = 33 ;

		try
		{
			myList.set( 4, toChange );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#set(int, E)}
	 * to throw IndexOutOfBoundsException on a negative invalid index.
	 */
	@Test
	public void testSet_IndexOutOfBoundsException_NegativeIndex()
	{
		myList.add( one );
		myList.add( two );
		myList.add( three );

		int toChange = 11 ;

		try
		{
			myList.set( -1, toChange );
			fail( "Failed to throw IndexOutOfBoundsException." );
		}
		catch( IndexOutOfBoundsException e )
		{
			assertTrue( true );
		}
	}
	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#size()}
	 * to return size of an empty list.
	 */
	@Test
	public void testSize_Empty() // test for 0 and 1 and max
	{
		int expected = 0;
		int actual = myList.size();
		assertEquals( "Failed to return correct size.", expected, actual );
	}
	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#size()}
	 * to return size of a list after adding one item.
	 */
	@Test
	public void testSize_AddOneToEmpty() // test for 0 and 1 and max
	{
		int expected = 1;
		myList.add( one );

		int actual = myList.size();
		assertEquals( "Failed to return correct size.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#size()}
	 * to return size of a list after adding multiple items.
	 */
	@Test
	public void testSize_AddMany() 
	{
		int expected = 5;
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );

		int actual = myList.size();
		assertEquals( "Failed to return correct size.", expected, actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#size()}
	 * to return the size of a list after removing the only item.
	 */
	@Test
	public void testSize_RemoveOneToEmpty()
	{
		int expected = 0;
		myList.add( one );
		myList.remove( 0 );
		int actual = myList.size();
		assertEquals( "Failed to return correct size.", expected, actual );
	}

	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#size()}
	 * to return the size of a list after removing a few items.
	 */
	@Test
	public void testSize_RemoveMany() 
	{
		int expected = 2;
		myList.add( one );
		myList.add( two );
		myList.add( three );
		myList.add( four );
		myList.add( five );
		myList.remove( 0 );
		myList.remove( 0 );
		myList.remove( 0 );
		int actual = myList.size();
		assertEquals( "Failed to return correct size.", expected, actual );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#toArray(T[])}
	 * to return an array containing all items of the list when the list has sufficient length.
	 */
	@Test
	public void testToArrayEArray_Sufficient()
	{
		Object[] original = new Integer[500];
		for( int i = 0; i < 500; i++ )
		{
			original[i] = i;
			myList.add( i );
		}

		Integer[] returnArray = new Integer[500];;
		returnArray = myList.toArray( returnArray );

		assertArrayEquals( "Failed to convert list to array.", original, returnArray );
	}
	
	/**
	 * Test method for
	 * {@link implementations.MyArrayList#toArray(T[])}
	 * to return an array containing all items of the list when the list has insufficient length.
	 */
	@Test
	public void testToArrayEArray_Insufficient()
	{
		Object[] original = new Integer[500];
		for( int i = 0; i < 500; i++ )
		{
			original[i] = i;
			myList.add( i );
		}

		Integer[] returnArray = new Integer[400];;
		returnArray = myList.toArray( returnArray );

		assertArrayEquals( "Failed to convert list to array.", original, returnArray );
	}

	/**
	 * Test method for
	 * {@link implementations.MyArrayList#toArray(T[])}
	 * to throw a NullPointerException when a null is passed.
	 */
	@Test
	public void testToArrayNullArray()
	{
		Integer[] returnArray = null;
		try 
		{
			returnArray = myList.toArray(returnArray);
			fail("Failed to throw NullPointerException.");
		} 
		catch (NullPointerException e) 
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for 
	 * {@link implementations.MyArrayList#toArray()}
	 * to return an Object array containing all items in the list.
	 */
	@Test
	public void testToArray()
	{
		Integer[] original = new Integer[500];
		for( int i = 0; i < 500; i++ )
		{
			original[i] = i;
			myList.add( i );
		}

		Object[] returnArray = myList.toArray();

		assertArrayEquals( "Failed to convert list to array.", original, returnArray );
	}
}
