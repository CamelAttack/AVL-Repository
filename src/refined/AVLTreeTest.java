package refined;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AVLTreeTest {

	AVLTree<Integer> testTree;
	private static Integer[] integerList = { new Integer(0), new Integer(1), new Integer(2), new Integer(3),
			new Integer(4), new Integer(5), new Integer(6), new Integer(7) };

	@BeforeEach
	void setUp() throws Exception {
		testTree = new AVLTree<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		testTree.deleteTree();
	}

	@Test
	void testDeleteTree() {
		System.out.println("********** TESTING DELETE TREE ***********");
		// Test Empty Tree
		testTree.deleteTree();
		// Test Single Element
		testTree.insertObject(integerList[1]);
		testTree.deleteTree();
		assertNull(testTree.getTreeRoot());
		// Test Full Tree
		testTree.insertObject(integerList[1]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		testTree.insertObject(integerList[4]);
		testTree.insertObject(integerList[5]);
		testTree.insertObject(integerList[6]);
		testTree.insertObject(integerList[7]);
		AVLNode<Integer> nodeFour = testTree.getTreeRoot();
		AVLNode<Integer> nodeTwo = nodeFour.getLeftChildNode();
		AVLNode<Integer> nodeSix = nodeFour.getRightChildNode();
		AVLNode<Integer> nodeOne = nodeTwo.getLeftChildNode();
		AVLNode<Integer> nodeThree = nodeTwo.getRightChildNode();
		AVLNode<Integer> nodeFive = nodeSix.getLeftChildNode();
		AVLNode<Integer> nodeSeven = nodeSix.getRightChildNode();
		testTree.deleteTree();
		assertTrue(isNodeCleaned(nodeFour));
		assertTrue(isNodeCleaned(nodeTwo));
		assertTrue(isNodeCleaned(nodeSix));
		assertTrue(isNodeCleaned(nodeOne));
		assertTrue(isNodeCleaned(nodeThree));
		assertTrue(isNodeCleaned(nodeFive));
		assertTrue(isNodeCleaned(nodeSeven));
	}

	@Test
	void testDeleteObject() {
		System.out.println(
				"****************************** TEST OBJECT DELETIONS ***************************************");
		// Test Deleting from an Empty Tree
		assertFalse(testTree.deleteObject(integerList[1]));
		// Test Deleting an Object Not in the tree
		testTree.insertObject(integerList[1]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		assertFalse(testTree.deleteObject(integerList[4]));
		// Test Deleting an Object from a three node tree.
		assertTrue(testTree.deleteObject(integerList[3]));
		// Test deleting several object from a three node tree.
		assertTrue(testTree.deleteObject(integerList[1]));
		assertTrue(testTree.deleteObject(integerList[2]));
		assertNull(testTree.getTreeRoot());
		testTree.deleteTree();
		// Test Deleting an Object from a larger simple tree.
		testTree.insertObject(integerList[1]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		testTree.insertObject(integerList[4]);
		testTree.insertObject(integerList[5]);
		testTree.insertObject(integerList[6]);
		testTree.insertObject(integerList[7]);
		AVLNode<Integer> nodeFour = testTree.getTreeRoot();
		AVLNode<Integer> nodeTwo = nodeFour.getLeftChildNode();
		AVLNode<Integer> nodeSix = nodeFour.getRightChildNode();
		AVLNode<Integer> nodeOne = nodeTwo.getLeftChildNode();
		AVLNode<Integer> nodeThree = nodeTwo.getRightChildNode();
		AVLNode<Integer> nodeFive = nodeSix.getLeftChildNode();
		AVLNode<Integer> nodeSeven = nodeSix.getRightChildNode();
		testTree.deleteObject(integerList[6]);
		nodeSeven = nodeSix;
		assertTrue(nodeSeven.isRightEmpty());
		assertFalse(nodeSeven.isLeftEmpty());
		assertEquals(nodeSeven.getLeftChildNode(), nodeFive);
		assertEquals(nodeFive.getParentNode(), nodeSeven);
		// Test deleting several objects from a larger tree.
		testTree.deleteObject(integerList[7]);
		nodeFive = nodeSeven;
		assertTrue(nodeFive.isRightEmpty());
		assertTrue(nodeFive.isLeftEmpty());
		assertEquals(nodeFour.getRightChildNode(), nodeFive);
		assertEquals(nodeFive.getParentNode(), nodeFour);
		testTree.deleteTree();
		// Test Deleting the root from a single node tree.
		testTree.insertObject(integerList[1]);
		nodeOne = testTree.getTreeRoot();
		testTree.deleteObject(integerList[1]);
		assertNull(testTree.getTreeRoot());
		assertTrue(isNodeCleaned(nodeOne));
		// Test Deleting the root from a three node tree.
		testTree.insertObject(integerList[1]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		nodeTwo = testTree.getTreeRoot();
		nodeOne = nodeTwo.getLeftChildNode();
		nodeThree = nodeTwo.getRightChildNode();
		testTree.deleteObject(integerList[2]);
		nodeThree = nodeTwo;
		assertEquals(testTree.getTreeRoot().getObject(), nodeThree.getObject());
		assertEquals(nodeOne.getObject(), nodeThree.getLeftChildObject());
		assertEquals(nodeOne.getParentNode().getObject(), nodeThree.getObject());
		testTree.deleteTree();
		// Test deleting the root from a larger tree.
		testTree.insertObject(integerList[1]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		testTree.insertObject(integerList[4]);
		testTree.insertObject(integerList[5]);
		testTree.insertObject(integerList[6]);
		testTree.insertObject(integerList[7]);
		nodeFour = testTree.getTreeRoot();
		nodeTwo = nodeFour.getLeftChildNode();
		nodeSix = nodeFour.getRightChildNode();
		nodeOne = nodeTwo.getLeftChildNode();
		nodeThree = nodeTwo.getRightChildNode();
		nodeFive = nodeSix.getLeftChildNode();
		nodeSeven = nodeSix.getRightChildNode();
		testTree.deleteObject(integerList[4]);
		assertEquals(nodeFour.getObject(), integerList[5]);
		assertNull(nodeFive.getObject());
		nodeFive = nodeFour;
		assertEquals(nodeFive.getObject(), testTree.getTreeRoot().getObject());
		assertEquals(nodeFive.getObject(), integerList[5]);
		assertEquals(nodeFive.getLeftChildNode().getObject(), nodeTwo.getObject());
		assertEquals(nodeFive.getRightChildObject(), nodeSix.getObject());
		System.out.println("**************************** ENDING TEST OBJECT DELETIONS ***********************");
	}

	@Test
	void testInsertObjectBasic() {
		System.out.println("*************** TESTING SIMPLE TREE INSERTIONS *******************************");
		// Test Null Value
		assertFalse(testTree.insertObject(null));

		// Test small 3 node tree with a single Left rotation
		testTree.insertObject(integerList[3]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[1]);
		testTree.printTree();

		AVLNode<Integer> nodeTwo = testTree.getTreeRoot();
		AVLNode<Integer> nodeOne = nodeTwo.getLeftChildNode();
		AVLNode<Integer> nodeThree = nodeTwo.getRightChildNode();

		assertTrue(nodeOne.isEmpty());
		assertTrue(nodeThree.isEmpty());
		assertFalse(nodeTwo.isLeftEmpty());
		assertFalse(nodeTwo.isRightEmpty());
		assertEquals(integerList[1], nodeOne.getObject());
		assertEquals(integerList[3], nodeThree.getObject());
		assertNull(nodeTwo.getParentNode());

		// Test Duplicate Value
		assertFalse(testTree.insertObject(new Integer(1)));
		// Test Duplicate Value at Root
		assertFalse(testTree.insertObject(new Integer(2)));
		// Test NULL value with non-empty tree
		assertFalse(testTree.insertObject(null));

		testTree.insertObject(integerList[4]);
		testTree.insertObject(integerList[5]);
		testTree.insertObject(integerList[7]);
		AVLNode<Integer> nodeFour = nodeTwo.getParentNode();
		AVLNode<Integer> nodeFive = nodeFour.getRightChildNode();
		AVLNode<Integer> nodeSeven = nodeFive.getRightChildNode();
		assertEquals(integerList[4], nodeFour.getObject());
		assertEquals(integerList[4], nodeTwo.getParentNode().getObject());
		assertEquals(integerList[4], nodeFive.getParentNode().getObject());
		assertEquals(integerList[2], nodeFour.getLeftChildObject());
		assertEquals(integerList[5], nodeFour.getRightChildObject());
		assertEquals(integerList[7], nodeSeven.getObject());
		assertEquals(integerList[7], nodeFive.getRightChildObject());
		assertEquals(integerList[1], nodeTwo.getLeftChildObject());
		assertEquals(integerList[3], nodeTwo.getRightChildObject());

		testTree.insertObject(integerList[6]);
		System.out.println(" ");
		testTree.printTree();
		assertEquals(integerList[6], nodeFour.getRightChildObject());
		AVLNode<Integer> nodeSix = nodeFour.getRightChildNode();
		assertEquals(integerList[5], nodeSix.getLeftChildObject());
		assertEquals(integerList[7], nodeSix.getRightChildObject());
		System.out.println("*************** ENDING SIMPLE TREE INSERTIONS *******************************");
	}

	@Test
	void testNodeCounts() {
		// Test Empty Tree Count
		assertEquals(0, testTree.getNodeCount());
		assertEquals(-1, testTree.deepNodeCountCheck());
		// Test Single Element Count
		testTree.insertObject(integerList[1]);
		assertEquals(1, testTree.getNodeCount());
		assertEquals(1, testTree.deepNodeCountCheck());
		// Test Three Element Count
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		assertEquals(3, testTree.getNodeCount());
		assertEquals(3, testTree.deepNodeCountCheck());
		// Test Seven Element Count
		testTree.insertObject(integerList[4]);
		testTree.insertObject(integerList[5]);
		testTree.insertObject(integerList[6]);
		testTree.insertObject(integerList[7]);
		assertEquals(7, testTree.getNodeCount());
		assertEquals(7, testTree.deepNodeCountCheck());
		// Test Single Deletion Count
		testTree.deleteObject(integerList[4]);
		assertEquals(6, testTree.getNodeCount());
		assertEquals(6, testTree.deepNodeCountCheck());
		// Test Multiple Deletion count
		testTree.deleteObject(integerList[2]);
		testTree.deleteObject(integerList[6]);
		testTree.deleteObject(integerList[1]);
		assertEquals(3, testTree.getNodeCount());
		assertEquals(3, testTree.deepNodeCountCheck());
	}

	@Test
	void testCheckForObject() {
		// Check an empty tree
		assertFalse(testTree.checkForObject(integerList[0]));
		// Check with a null object
		testTree.insertObject(integerList[1]);
		testTree.insertObject(integerList[2]);
		testTree.insertObject(integerList[3]);
		testTree.insertObject(integerList[4]);
		testTree.insertObject(integerList[5]);
		testTree.insertObject(integerList[6]);
		testTree.insertObject(integerList[7]);
		testTree.printTree();
		assertFalse(testTree.checkForObject(null));
		// Check with an object not in the tree
		assertFalse(testTree.checkForObject(integerList[0]));
		// Check for each element
		assertTrue(testTree.checkForObject(integerList[1]));
		assertTrue(testTree.checkForObject(integerList[2]));
		assertTrue(testTree.checkForObject(integerList[3]));
		assertTrue(testTree.checkForObject(integerList[4]));
		assertTrue(testTree.checkForObject(integerList[5]));
		assertTrue(testTree.checkForObject(integerList[6]));
		assertTrue(testTree.checkForObject(integerList[7]));

	}

	private boolean isNodeCleaned(AVLNode someNode) {
		boolean nodeClean = true;
		if (someNode.getObject() != null) {
			nodeClean = false;
		}
		if (someNode.getLeftChildNode() != null) {
			nodeClean = false;
		}
		if (someNode.getRightChildNode() != null) {
			nodeClean = false;
		}
		if (someNode.getParentNode() != null) {
			nodeClean = false;
		}
		return nodeClean;
	}

}
