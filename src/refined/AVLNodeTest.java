package refined;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import refined.AVLNode.LeafDirection;

class AVLNodeTest {
	private static Integer[] integerList = { new Integer(500), new Integer(750), new Integer(125), new Integer(350) };
	private static AVLNode<Integer> firstNode;
	private static AVLNode<Integer> bigNode;
	private static AVLNode<Integer> smallNode;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		firstNode = new AVLNode<Integer>(integerList[0]);
		bigNode = new AVLNode<Integer>(integerList[1]);
		smallNode = new AVLNode<Integer>(integerList[2]);
	}

	@BeforeEach
	void setUp() throws Exception {
		if (firstNode != null) {
			firstNode.cleanNode();
		}
		if (bigNode != null) {
			bigNode.cleanNode();
		}
		if (smallNode != null) {
			smallNode.cleanNode();
		}
		firstNode = new AVLNode<Integer>(integerList[0]);
		bigNode = new AVLNode<Integer>(integerList[1]);
		smallNode = new AVLNode<Integer>(integerList[2]);
	}

	@Test
	void testBooleanChecks() {
		assertTrue(firstNode.isLeftEmpty());
		assertTrue(firstNode.isRightEmpty());
		assertTrue(firstNode.isEmpty());
		firstNode.connectLeftChild(smallNode);
		assertFalse(firstNode.isLeftEmpty());
		assertFalse(firstNode.isEmpty());
		firstNode.connectRightChild(bigNode);
		assertFalse(firstNode.isRightEmpty());
		assertTrue(smallNode.hasParent());
		assertTrue(bigNode.hasParent());
		assertFalse(firstNode.hasParent());
		firstNode.disconnectRightChild();
		assertTrue(firstNode.isRightEmpty());
		assertFalse(bigNode.hasParent());
		assertFalse(firstNode.isEmpty());
		firstNode.disconnectLeftChild();
		assertTrue(firstNode.isLeftEmpty());
		assertTrue(firstNode.isEmpty());
		assertFalse(smallNode.hasParent());
	}

	@Test
	void testConnectLeftChild() {
		firstNode.connectLeftChild(smallNode);
		AVLNode<Integer> childNode = firstNode.getLeftChildNode();
		AVLNode<Integer> parentNode = childNode.getParentNode();
		assertEquals(smallNode.getObject(), childNode.getObject(), "Left child did not attach to parent node");
		assertEquals(parentNode.getObject(), firstNode.getObject(), "Parent did not attach to left child.");
		assertEquals(childNode.getLeafDirection(), LeafDirection.LEFT);
	}

	@Test
	void testConnectRightChild() {
		firstNode.connectRightChild(bigNode);
		AVLNode<Integer> childNode = firstNode.getRightChildNode();
		AVLNode<Integer> parentNode = childNode.getParentNode();
		assertEquals(bigNode.getObject(), childNode.getObject(), "Right child did not attach to parent node.");
		assertEquals(parentNode.getObject(), firstNode.getObject(), "Parent did not attach to right child.");
		assertEquals(childNode.getLeafDirection(), LeafDirection.RIGHT);
	}

	@Test
	void testDisconnectLeftChild() {
		firstNode.connectLeftChild(smallNode);
		firstNode.connectRightChild(bigNode);
		AVLNode<Integer> disconnectedNode = firstNode.disconnectLeftChild();
		assertNull(firstNode.getLeftChildNode(), "Left child was not disconnected.");
		assertNull(disconnectedNode.getParentNode(), "Parent was not disconnected from left child.");
		assertNotNull(firstNode.getRightChildNode(), "Right child was disconnected when it should not have been.");
		assertNotNull(bigNode.getParentNode(),
				"Parent was disconnected from the right child when it should not have been.");
		assertEquals(disconnectedNode.getLeafDirection(), LeafDirection.NONE);
		assertEquals(bigNode.getLeafDirection(), LeafDirection.RIGHT);
	}

	@Test
	void testDisconnectRightChild() {
		firstNode.connectLeftChild(smallNode);
		firstNode.connectRightChild(bigNode);
		AVLNode<Integer> disconnectedNode = firstNode.disconnectRightChild();
		assertNull(firstNode.getRightChildNode(), "Right child was not disconnected.");
		assertNull(disconnectedNode.getParentNode(), "Parent was not disconnected from right child.");
		assertNotNull(firstNode.getLeftChildNode(), "Left child was disconnected when it should not have been.");
		assertNotNull(smallNode.getParentNode(),
				"Parent was disconnected from the left child when it should not have been.");
		assertEquals(disconnectedNode.getLeafDirection(), LeafDirection.NONE);
		assertEquals(smallNode.getLeafDirection(), LeafDirection.LEFT);
	}

	@Test
	void testNodeHeight() {
		assertEquals(firstNode.getHeight(), 0);
		firstNode.connectLeftChild(smallNode);
		assertEquals(firstNode.getHeight(), 1, "Get Height after shallow update failed.");
		smallNode.connectRightChild(bigNode);
		firstNode.updateNodeHeightDeep();
		assertEquals(2, firstNode.getHeight(), "Get Height after deep update failed.");
	}

	@Test
	void testCalculateBalance() {
		assertEquals(0, firstNode.calculateBalance(), "Balance with no children failed");
		firstNode.connectLeftChild(smallNode);
		assertEquals(1, firstNode.calculateBalance(), "Balance check with a single child failed.");
		smallNode.connectLeftChild(bigNode);
		assertEquals(2, firstNode.calculateBalance(), "Balance with two children on the left side failed.");
		smallNode.disconnectLeftChild();
		assertEquals(1, firstNode.calculateBalance(), "Balance after removing one child from the Left failed.");
		firstNode.connectRightChild(bigNode);
		assertEquals(0, firstNode.calculateBalance(), "Balance with a child on each side failed.");
		firstNode.disconnectLeftChild();
		assertEquals(1, firstNode.calculateBalance(), "Balance with one right child failed.");
		bigNode.connectLeftChild(smallNode);
		assertEquals(2, firstNode.calculateBalance(), "Balance with two children on the right failed.");
	}

}
