import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Red Black Tree Program
 * 
 * @author Wally Estenson
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

	// final booleans to show node as Red or Black
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	// LinkedLists for traversals
	private LinkedList<K> iot;
	private LinkedList<K> peot;
	private LinkedList<K> poot;
	private LinkedList<K> lot;
	private LinkedList<K> printList;

	// inner class for BST node
	class RBTNode<K, V> {

		K key;
		V value;
		boolean color;
		RBTNode<K, V> left;
		RBTNode<K, V> right;
		RBTNode<K, V> parent;
		// int balanceFactor;
		// int height;

		/**
		 * 
		 * RBTNode constructor
		 * 
		 * @param key
		 * @param value
		 * @param leftChild
		 * @param rightChild
		 */
		RBTNode(K key, V value, RBTNode<K, V> leftChild, RBTNode<K, V> rightChild, RBTNode<K, V> parent,
				boolean color) {
			this.key = key;
			this.value = value;
			this.left = leftChild;
			this.right = rightChild;
			this.parent = parent;
			this.color = color;
			// this.height = 0;
			// this.balanceFactor = 0;
		}

		RBTNode(K key, V value) {
			this(key, value, null, null, null, BLACK);
		}
	}

	private RBTNode<K, V> root; // root of the tree
	private int numKeys; // numbers of keys in the tree

	// constructor for tree
	public BALST() {
	}

	@Override
	public K getKeyAtRoot() {
		return root.key; // return key at root
	}

	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		RBTNode<K, V> current = root;

		// key is null, throw exception
		if (key == null)
			throw new IllegalNullKeyException();

		// works through the tree to find the key and returns its left childs key
		while (current != null) {
			// if found, return left child key
			if (current.key.equals(key))
				return current.left.key;

			// work through tree
			else if (current.key.compareTo(key) < 0)
				current = current.right;
			else
				current = current.left;
		}
		// if not found, throw exception
		throw new KeyNotFoundException();
	}

	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		RBTNode<K, V> current = root;

		// key is null, throw exception
		if (key == null)
			throw new IllegalNullKeyException();

		// works through the tree to find the key and returns its left childs key
		while (current != null) {
			// if found, return right child key
			if (current.key.equals(key))
				return current.right.key;

			// work through tree
			else if (current.key.compareTo(key) < 0)
				current = current.right;
			else
				current = current.left;
		}
		// if not found, throw exception
		throw new KeyNotFoundException();
	}

	@Override
	public int getHeight() {
		return getHeightHelper(root);
	}

	/**
	 * Helper method for the getHeight method that returns the height of the tree
	 * 
	 * @param RBTNode root of the tree
	 * @return returns int height of the tree
	 */
	private int getHeightHelper(RBTNode root) {
		// if tree is empty
		if (root == null)
			return 0;

		// otherwise, work way through tree recursively
		else {
			int leftDepth = getHeightHelper(root.left);
			int rightDepth = getHeightHelper(root.right);

			// return greater depth
			if (leftDepth > rightDepth)
				return (leftDepth + 1);
			else
				return (rightDepth + 1);
		}
	}

	@Override
	public List<K> getInOrderTraversal() {
		iot = new LinkedList<K>(); // linkedlist to store tree
		iotHelper(root); // call helper method
		return iot; // return list
	}

	/**
	 * Helper method for getInOrderTraversal
	 * 
	 * @param RBTNode root of tree
	 */
	private void iotHelper(RBTNode<K, V> node) {

		// if node it null, list will be empty
		if (node == null)
			return;

		// recursively work through tree and add keys to the list in order
		if (node.left != null)
			iotHelper(node.left);

		iot.add(node.key);

		if (node.right != null)
			iotHelper(node.right);
	}

	@Override
	public List<K> getPreOrderTraversal() {
		peot = new LinkedList<K>(); // linkedlist to store tree
		peotHelper(root); // call helper method
		return peot; // return list
	}

	/**
	 * Helper method for getPreOrderTraversal
	 * 
	 * @param RBTNode root of tree
	 */
	private void peotHelper(RBTNode<K, V> node) {

		// if node is null, list will be empty
		if (node == null)
			return;

		// recursively work through tree and add keys to the list in order
		peot.add(node.key);

		if (node.left != null)
			peotHelper(node.left);

		if (node.right != null)
			peotHelper(node.right);

	}

	@Override
	public List<K> getPostOrderTraversal() {
		poot = new LinkedList<K>(); // linkedlist to store tree
		pootHelper(root); // call helper method
		return poot; // return list
	}

	/**
	 * Helper method for getPostOrderTraversal
	 * 
	 * @param RBTNode root of tree
	 */
	private void pootHelper(RBTNode<K, V> node) {

		// if node is null, list will be empty
		if (node == null)
			return;

		// work through the tree recursively and add keys to list
		if (node.left != null)
			pootHelper(node.left);

		if (node.right != null)
			pootHelper(node.right);

		poot.add(node.key);
	}

	@Override
	public List<K> getLevelOrderTraversal() {
		lot = new LinkedList<K>(); // linkedlist to store tree

		// work through each of the levels and call helper method
		for (int L = 1; L <= getHeight(); L++) {
			lotHelper(root, L);
		}
		return lot; // return list
	}

	/**
	 * Helper method for getLevelOrderTraversal
	 * 
	 * @param RBTNode root of tree
	 */
	private void lotHelper(RBTNode<K, V> node, int level) {
		// List<K> list = new LinkedList<K>();
		// return list;

		// if node is null, list will be empty
		if (node == null) {
			return;
		}
		// recursively work through the tree adding by level
		if (level == 1) {
			lot.add(node.key);
		}
		if (level != 1) {
			lotHelper(node.left, level - 1);
			lotHelper(node.right, level - 1);
		}
		return;

	}

	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		RBTNode<K, V> current = root; // node
		RBTNode<K, V> parent = null; // parent of current node
		RBTNode<K, V> toBeInserted = new RBTNode<K, V>(key, value, null, null, parent, RED); // node that will be
																								// inserted
		// if key is null, throw exception
		if (toBeInserted.key == null)
			throw new IllegalNullKeyException();

		// if the list already contains the key, throw exception
		else if (contains(toBeInserted.key) == true)
			throw new DuplicateKeyException();

		// else add the node
		else {
			// while we havent reached the end of the tree
			while (current != null) {

				// if current is the last item in the list, we will preserve it to be
				// the parent of the inserted node
				parent = current;

				// if current key is greater than the key to be inserted, move right
				if (current.key.compareTo(toBeInserted.key) < 0)
					current = current.right;
				else // otherwise, move left
					current = current.left;
			}

			//// once the node is found, the following show the options for inserting node

			// tree is empty
			if (root == null) {
				toBeInserted.color = BLACK;
				root = toBeInserted;
			}

			// insert to the right of leaf
			else if (parent.key.compareTo(toBeInserted.key) < 0) {
				parent.right = toBeInserted;
				toBeInserted.parent = parent;
			}
			// insert to the left of leaf
			else {
				parent.left = toBeInserted;
				toBeInserted.parent = parent;
			}
			// reference to inserted node parent set
			toBeInserted.parent = parent;

			//// fix tree
			if (toBeInserted != root) {
				insertFix(toBeInserted);
			}
			// inserted node, so increase number of keys
			numKeys++;
		}
	}

	/**
	 * Helper method for fixing the tree after insert The tree must have the same
	 * number of black nodes on each side The tree must not have 2 red nodes in a
	 * row We fix these with color changes and rotations
	 * 
	 * @param RBTNode inserted node
	 */
	private void insertFix(RBTNode<K, V> insertedNode) {
		
		// while there is a red property violation - parent is red
		while (insertedNode.parent.color == RED) {

			// check if parent is left child
			if (insertedNode.parent.parent.left == insertedNode.parent) {

				// if parents sibling is null or black
				if (insertedNode.parent.parent.right == null || insertedNode.parent.parent.right.color == BLACK) {

					// if inserted node is left child of parent, right rotate
					if (insertedNode.parent.left == insertedNode) {
						insertedNode.parent.color = BLACK;
						insertedNode.parent.parent.color = RED;
						rightRotation(insertedNode.parent);
					}
					// else left right rotate
					else {
						insertedNode.color = BLACK;
						insertedNode.parent.parent.color = RED;
						leftRotation(insertedNode);
						rightRotation(insertedNode);
					}
				}
				// else parents sibling is red, so re-coloring
				else {
					insertedNode.parent.parent.right.color = BLACK;
					insertedNode.parent.color = BLACK;

					if (insertedNode.parent.right != null)
						insertedNode.parent.right.color = RED;

					if (insertedNode.parent.left != null)
						insertedNode.parent.left.color = RED;

					if (insertedNode.parent.parent != root)
						insertedNode.parent.parent.color = RED;

					// work way back up tree for more problems
					if (insertedNode.parent.parent != root) {
						insertFix(insertedNode.parent.parent);
					}
					root.color = BLACK; // make sure root is black
				}
			}

			// else parent is right child
			else {

				// if parents sibling is null or black
				if (insertedNode.parent.parent.left == null || insertedNode.parent.parent.left.color == BLACK) {

					// if inserted node is right child of parent, left rotate
					if (insertedNode.parent.right == insertedNode) {
						insertedNode.parent.color = BLACK;
						insertedNode.parent.parent.color = RED;
						leftRotation(insertedNode.parent);
					}
					// else right left rotate
					else {
						insertedNode.color = BLACK;
						insertedNode.parent.parent.color = RED;
						rightRotation(insertedNode);
						leftRotation(insertedNode);
					}
				}
				// else parents sibling is red, so re-coloring
				else {
					insertedNode.parent.parent.left.color = BLACK;
					insertedNode.parent.color = BLACK;

					if (insertedNode.parent.right != null)
						insertedNode.parent.right.color = RED;

					if (insertedNode.parent.left != null)
						insertedNode.parent.left.color = RED;

					if (insertedNode.parent.parent != root)
						insertedNode.parent.parent.color = RED;

					// work way back up tree for more problems
					if (insertedNode.parent.parent != root) {
						insertFix(insertedNode.parent.parent);
					}
					root.color = BLACK; // make sure root is black
				}
			}
		}

	}

	/**
	 * Helper method for fixing the tree and doing right rotation tri node
	 * restructuring
	 * 
	 * @param RBTNode node to be rotated around
	 */
	private void rightRotation(RBTNode<K, V> node) {
		RBTNode<K, V> grandparent = node.parent.parent;
		RBTNode<K, V> right = node.right;

		// reset references
		node.parent.parent = node;
		node.parent.left = node.right;
		node.right = node.parent;

		// if node had right child, reset references
		if (right != null) {
			right.parent = node.right;
			node.right.left = right;
		}

		// if the nodes new parent is not the root
		if (node.parent != root) {

			// set nodes new parent reference to node
			if (grandparent.key.compareTo(node.key) < 0) {
				grandparent.right = node;

			} else {
				grandparent.left = node;
			}
			node.parent = grandparent;
		} else {
			root = node;
			root.color = BLACK;
		}

	}

	/**
	 * Helper method for fixing the tree and doing left rotation tri node
	 * restructuring
	 * 
	 * @param RBTNode node to be rotated around
	 */
	private void leftRotation(RBTNode<K, V> node) {
		RBTNode<K, V> grandparent = node.parent.parent;
		RBTNode<K, V> left = node.left;

		// resetting references
		node.parent.parent = node;
		node.parent.right = node.left;
		node.left = node.parent;

		// if node has left child, set references
		if (left != null) {
			left.parent = node.left;
			node.left.right = left;
		}

		// make former grandparent into parent
		if (node.parent != root) {
			if (grandparent.key.compareTo(node.key) < 0)
				grandparent.right = node;
			else {
				grandparent.left = node;
			}
			node.parent = grandparent;
		}

		else {
			root = node;
			root.color = BLACK;
		}
	}

	// NOTE that remove does not maintain Red Black features
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {

		// if null, throw exception
		if (key == null)
			throw new IllegalNullKeyException();

		// if tree does not contain key, throw exception
		if (contains(key) == false) {
			throw new KeyNotFoundException();
		}

		// call helper to remove
		root = removeHelper(root, key);
		return true;
	}

	/**
	 * Helper method for removing node from tree
	 * 
	 * @param RBTNode root, key of node to be removed
	 */
	private RBTNode<K, V> removeHelper(RBTNode<K, V> node, K key) throws KeyNotFoundException {

		// tree is empty
		if (node == null)
			return node;

		// work way through tree until found
		if (node.key.compareTo(key) > 0) {
			node.left = removeHelper(node.left, key);
		}

		else if (node.key.compareTo(key) < 0) {
			node.right = removeHelper(node.right, key);
		}

		// if found
		else {
			numKeys--; // reduce number of keys
			// key is root and tree only has root
			if (node.left == null && node.right == null) {
				// root = node;
				return null;

			}

			// match has one right child
			if (node.left == null) {
				// node.parent.right = node.right;
				// node.parent = node.right.parent;
				return node.right;
			}

			// match has one left child
			if (node.right == null) {
				// node.parent.left = node.left;
				// node.parent = node.left.parent;
				return node.left;
			}

			// match has 2 children
			else {
				// get in order successor from subtree
				node.key = minValue(node.right);
				// delete in order successor
				node.right = removeHelper(node.right, node.key);
			}
		}
		return node;
	}

	/**
	 * Helper method for finding min value 
	 * 
	 * @param RBTNode node in subtree to start from 
	 */
	private K minValue(RBTNode<K, V> node) {
		K minValue = root.key;
		//work way down to find min value 
		while (root.left != null) {
			minValue = root.left.key;
			root = root.left;
		}
		return minValue;
	}

	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();

		//call helper method 
		return getHelper(root, key);
	}

	/**
	 * Helper method for getting value associated with key 
	 * 
	 * @param RBTNode root of tree
	 * @param key to find 
	 */
	private V getHelper(RBTNode<K, V> node, K key) throws KeyNotFoundException {

		//if tree is empty, throw exception
		if (node == null) {
			throw new KeyNotFoundException();
		}

		//if found, return value
		if (node.key == key)
			return node.value;

		//work way through tree until finding value 
		if (node.key.compareTo(key) > 0)
			return getHelper(node.left, key);

		else if (node.key.compareTo(key) < 0)
			return getHelper(node.right, key);

		//if not found, throw exception 
		throw new KeyNotFoundException();
	}

	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		
		if (key == null)
			throw new IllegalNullKeyException();

		//call helper method 
		return containsHelper(root, key);
	}

	/**
	 * Helper method for checking if tree contains value 
	 * 
	 * @param RBTNode root of tree
	 * @param key to find in tree
	 */
	private boolean containsHelper(RBTNode<K, V> node, K key) {

		//if tree is empty, return false
		if (node == null)
			return false;

		//if found key, return true
		if (node.key == key) {
			return true;
		}

		//work way through tree to find key 
		if (node.key.compareTo(key) > 0) {
			return containsHelper(node.left, key);
		} else {
			return containsHelper(node.right, key);
		}

	}

	@Override
	public int numKeys() {
		return numKeys;
	}

	@Override
	public void print() {
		printHelper(root, 0); // call helper
	}

	private void printHelper(RBTNode<K, V> node, int level) {
			
		//if tree is empty, return empty 
		    if(node==null)
		         return;
		    
		    //recursively call method moving through levels 
		    printHelper(node.right, level+1);
		    
		    if(level!=0){
		        for(int i=0;i<level-1;i++)
		            System.out.print("|\t");
		            System.out.println("|-------"+node.key);
		    }
		    else
		        System.out.println(node.key);
		    printHelper(node.left, level+1);
		}   
}
