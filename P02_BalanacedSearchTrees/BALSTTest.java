import static org.junit.Assert.fail;


import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * Test Class for BALST.java
 * 
 * @author Wally Estenson
 */
//@SuppressWarnings("rawtypes")
public class BALSTTest {

	//instance of the balst tree 
	BALST<Integer, String> balst2;

	/**
	 * Before each test, create tree
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		balst2 = createInstance2();
	}

	/**
	 * After each test, destroy tree
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		balst2 = null;
	}

	protected BALST<String, String> createInstance() {
		return new BALST<String, String>();
	}

	protected BALST<Integer, String> createInstance2() {
		return new BALST<Integer, String>();
	}

	/**
	 * Insert three values in sorted order and then check the root, left, and right
	 * keys to see if rebalancing occurred.
	 */
	@Test
	void testBALST_001_insert_sorted_order_simple() {
		try {
			balst2.insert(10, "10");
			if (!balst2.getKeyAtRoot().equals(10))
				fail("avl insert at root does not work");

			balst2.insert(20, "20");
			if (!balst2.getKeyOfRightChildOf(10).equals(20))
				fail("avl insert to right child of root does not work");

			balst2.insert(30, "30");
			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			// balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values in reverse sorted order and then check the root, left,
	 * and right keys to see if rebalancing occurred in the other direction.
	 */
	@Test
	void testBALST_002_insert_reversed_sorted_order_simple() {

		try {
			balst2.insert(30, "30");
			if (!balst2.getKeyAtRoot().equals(30))
				fail("avl insert at root does not work");

			balst2.insert(20, "20");
			if (!balst2.getKeyOfLeftChildOf(30).equals(20))
				fail("avl insert to left child of root does not work");

			balst2.insert(10, "10");
			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			// balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values so that a right-left rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred in
	 * the other direction.
	 */
	@Test
	void testBALST_003_insert_smallest_largest_middle_order_simple() {

		try {
			balst2.insert(10, "10");
			if (!balst2.getKeyAtRoot().equals(10))
				fail("avl insert at root does not work");

			balst2.insert(30, "30");
			if (!balst2.getKeyOfRightChildOf(10).equals(30))
				fail("avl insert to left child of root does not work");

			balst2.insert(20, "20");

			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl right left rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			// balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values so that a left-right rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred in
	 * the other direction.
	 */
	 @Test
	void testBALST_004_insert_largest_smallest_middle_order_simple() {

		try {
			balst2.insert(30, "30");
			if (!balst2.getKeyAtRoot().equals(30))
				fail("avl insert at root does not work");

			balst2.insert(10, "10");
			if (!balst2.getKeyOfLeftChildOf(30).equals(10))
				fail("avl insert to left child of root does not work");

			balst2.insert(20, "20");

			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl left right rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			 balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Insert many values in sorted order.
	 * Check along the way.
	 */
	@Test
	void testBALST_005_insert_complex_sorted_order() {

		try {
			balst2.insert(1, "1");
			balst2.insert(2, "2");
			balst2.insert(3, "3");
			balst2.insert(4, "4");

			// should be BST 2, 1, 3, 4 LOT

			if (!balst2.getKeyAtRoot().equals(2))
				fail("test 5 root incorrect after 4 insert");

			if (balst2.getKeyOfRightChildOf(3) != 4)
				fail("test 5 child of 3 incorrect after 4 insert");

			balst2.insert(5, "5");

			if (balst2.getKeyOfRightChildOf(2) != 4)
				fail("test 5 child of 2 incorrect after 5 insert");

			if (balst2.getKeyOfRightChildOf(4) != 5)
				fail("test 5 right child of 4 incorrect after 5 insert");

			if (balst2.getKeyOfLeftChildOf(4) != 3)
				fail("test 5 left child of 4 incorrect after 5 insert");

			balst2.insert(6, "6");

			balst2.insert(7, "7");

			if (balst2.getKeyOfRightChildOf(4) != 6)
				fail("test 5 child of 4 incorrect after 7 insert");

			if (balst2.getKeyOfRightChildOf(6) != 7)
				fail("test 5 right child of 6 incorrect after 5 insert");

			if (balst2.getKeyOfLeftChildOf(6) != 5)
				fail("test 5 left child of 6 incorrect after 5 insert");

			balst2.insert(8, "8");

			if (balst2.getKeyOfRightChildOf(2) != 3)
				fail("test 5 child of 2 incorrect after 8 insert");

			if (balst2.getKeyOfRightChildOf(4) != 6)
				fail("test 5 right child of 4 incorrect after 8 insert");

			if (balst2.getKeyOfLeftChildOf(6) != 5)
				fail("test 5 left child of 6 incorrect after 8 insert");

			// balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert many values in reverse sorted order.
	 * Check along the way.
	 */
	@Test
	void testBALST_006_insert_complex_reverse_sorted_order() {

		try {
			balst2.insert(9, "9");
			balst2.insert(8, "8");
			balst2.insert(7, "7");
			balst2.insert(6, "6");

			if (!balst2.getKeyAtRoot().equals(8))
				fail("test 6 root incorrect after 6 insert");

			if (balst2.getKeyOfLeftChildOf(7) != 6)
				fail("test 6 child of 3 incorrect after 6 insert");

			balst2.insert(5, "5");

			if (balst2.getKeyOfRightChildOf(6) != 7)
				fail("test 6 child of 6 incorrect after 5 insert");

			if (balst2.getKeyOfRightChildOf(8) != 9)
				fail("test 6 right child of 8 incorrect after 5 insert");

			if (balst2.getKeyOfLeftChildOf(6) != 5)
				fail("test 6 left child of 5 incorrect after 5 insert");

			balst2.insert(4, "4");

			balst2.insert(3, "3");

			if (balst2.getKeyOfRightChildOf(4) != 5)
				fail("test 6 child of 4 incorrect after 3 insert");

			if (balst2.getKeyOfRightChildOf(6) != 7)
				fail("test 6 right child of 6 incorrect after 3 insert");

			if (balst2.getKeyOfLeftChildOf(6) != 4)
				fail("test 6 left child of 6 incorrect after 3 insert");

			balst2.insert(2, "2");

			if (balst2.getKeyOfLeftChildOf(3) != 2)
				fail("test 6 child of 3 incorrect after 2 insert");

			if (balst2.getKeyOfRightChildOf(6) != 8)
				fail("test 6 right child of 6 incorrect after 2 insert");

			if (balst2.getKeyOfLeftChildOf(6) != 4)
				fail("test 6 left child of 6 incorrect after 2 insert");

			// balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert many values in random order.
	 * Check along the way including root keys. 
	 */
	@Test
	void testBALST_007_insert_complex_random_order_plus_root_key_test() {

		try {
			balst2.insert(1, "1");
			balst2.insert(99, "99");
			
			balst2.insert(210, "210");
			
			balst2.insert(0, "0");
		
			balst2.insert(10, "10");

			if (!balst2.getKeyAtRoot().equals(99))
				fail("test 7 root incorrect after 10 insert");
			
			balst2.insert(5, "5");
			
			balst2.insert(500, "500");
			
			
			balst2.insert(32, "32");
			
			balst2.insert(100, "100");
			balst2.insert(3, "3");

			if (!balst2.getKeyAtRoot().equals(10))
				fail("test 7 root incorrect after 3 insert");

			if (balst2.getKeyOfRightChildOf(210) != 500)
				fail("test 7 right child of 210 incorrect");

			if (balst2.getKeyOfLeftChildOf(210) != 100)
				fail("test 7 left child of 210 incorrect");

			if (balst2.getKeyOfRightChildOf(1) != 5)
				fail("test 7 right child of 1 incorrect");

			if (balst2.getKeyOfLeftChildOf(99) != 32)
				fail("test 7 left child of 99 incorrect");

			if (balst2.getKeyOfLeftChildOf(5) != 3)
				fail("test 7 left child of 5 incorrect");

			// PROBLEM
			 balst2.insert(2, "2");
				balst2.print();
				if (balst2.getKeyOfLeftChildOf(10) != 1)
					fail("test 7 left child of 10 incorrect");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Checks height method.
	 */
	@Test
	void testBALST_008_height_test() {

		try {
			if (balst2.getHeight() != 0)
				fail("test 8 - height should be null");

			balst2.insert(1, "1");

			if (balst2.getHeight() != 1)
				fail("test 8 - height should be 1");

			balst2.insert(2, "2");
			balst2.insert(3, "3");

			if (balst2.getHeight() != 2)
				fail("test 8 - height should be 2");

			balst2.insert(100, "100");
			balst2.insert(0, "0");
	
			balst2.insert(4, "4");
			balst2.print();
			balst2.insert(500, "500");

			balst2.print();
	
	
			if (balst2.getHeight() != 4)
				fail("test 8 - height should be 4");


		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Checks contains method by adding, removing, and checking contains. 
	 */
	@Test
	void testBALST_09_contains() {

		try {

			balst2.insert(20, "20");

			if (balst2.contains(20) != true)
				fail("test 10 - contain 20 after insert failed to return true");

			balst2.print();
			balst2.remove(20);
			balst2.print();
			
			if (balst2.contains(20) != false)
				fail("test 10 - contain 20 after removal failed to return false");
			
			balst2.insert(10, "10");
			balst2.insert(1, "1");
			balst2.insert(3, "3");

			if (balst2.contains(10) != true)
				fail("test 10 - contain 10 after insert failed to return true");

			if (balst2.contains(3) != true)
				fail("test 10 - contain 3 after insert failed to return true");

			balst2.remove(10);

			if (balst2.contains(10) != false)
				fail("test 10 - contain 10 after removal failed to return false");

			balst2.insert(2, "2");
			balst2.insert(20, "20");
			balst2.insert(23, "23");
			balst2.insert(21, "30");
			balst2.insert(5, "5");
			balst2.insert(0, "0");


			if (balst2.contains(5) != true)
				fail("test 10 - contain 5 after insert failed to return true");

			balst2.remove(5);
			
			balst2.print();
			if (balst2.contains(5) != false)
				fail("test 10 - contain 5 after removal failed to return false");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Checks get method by adding and trying to get. 
	 */
	@Test
	void testBALST_010_get() {

		try {

			balst2.insert(20, "20");


			if (balst2.get(20) != "20")
				fail("test 10 - get 20 after insert failed to return key");

			balst2.insert(30, "25");
			balst2.insert(40, "24");
			balst2.insert(50, "23");
			balst2.insert(60, "22");

			if (balst2.get(50) != "23")
				fail("test 10 - get 50 after insert failed to return key");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}
}
