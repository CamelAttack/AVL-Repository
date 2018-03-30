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
	}

	@Test
	void testInsertObjectBasic() {
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
	}

}
