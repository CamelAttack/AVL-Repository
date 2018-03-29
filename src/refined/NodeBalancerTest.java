package refined;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeBalancerTest {

	private static Integer[] integerList = { new Integer(0), new Integer(1), new Integer(2), new Integer(3),
			new Integer(4), new Integer(5), new Integer(6) };
	private static AVLNode<Integer> nodeOne;
	private static AVLNode<Integer> nodeTwo;
	private static AVLNode<Integer> nodeThree;
	private static AVLNode<Integer> nodeFour;
	private static AVLNode<Integer> nodeFive;
	private static AVLNode<Integer> nodeSix;

	@BeforeEach
	void setUp() throws Exception {
		nodeOne = new AVLNode<Integer>(integerList[1]);
		nodeTwo = new AVLNode<Integer>(integerList[2]);
		nodeThree = new AVLNode<Integer>(integerList[3]);
		nodeFour = new AVLNode<Integer>(integerList[4]);
		nodeFive = new AVLNode<Integer>(integerList[5]);
		nodeSix = new AVLNode<Integer>(integerList[6]);
	}

	@AfterEach
	void tearDown() throws Exception {
		nodeFive.cleanNode();
		nodeFour.cleanNode();
		nodeThree.cleanNode();
		nodeTwo.cleanNode();
		nodeOne.cleanNode();
		nodeSix.cleanNode();
		nodeOne = null;
		nodeTwo = null;
		nodeThree = null;
		nodeFour = null;
		nodeFive = null;
		nodeSix = null;
	}

	@Test
	void testRotateWithLeftChild() {
		nodeThree.connectLeftChild(nodeTwo);
		nodeTwo.connectLeftChild(nodeOne);
		NodeBalancer.rotateWithLeftChild(nodeThree);
		assertTrue(nodeOne.isEmpty());
		assertTrue(nodeThree.isEmpty());
		assertFalse(nodeTwo.isLeftEmpty());
		assertFalse(nodeTwo.isRightEmpty());
		assertEquals(nodeTwo.getLeftChildObject(), nodeOne.getObject());
		assertEquals(nodeTwo.getRightChildObject(), nodeThree.getObject());
		assertNull(nodeTwo.getParentNode());
	}

	@Test
	void testRotateDoubleWithLeftChild() {
		//Simple test of double rotation
		nodeThree.connectLeftChild(nodeOne);
		nodeOne.connectRightChild(nodeTwo);
		NodeBalancer.rotateDoubleWithLeftChild(nodeThree);
		assertTrue(nodeOne.isEmpty());
		assertTrue(nodeThree.isEmpty());
		assertFalse(nodeTwo.isLeftEmpty());
		assertFalse(nodeTwo.isRightEmpty());
		assertEquals(nodeTwo.getLeftChildObject(), nodeOne.getObject());
		assertEquals(nodeTwo.getRightChildObject(), nodeThree.getObject());
		assertNull(nodeTwo.getParentNode());
		
		//More Complicated test of left right rotation
		nodeThree.cleanNode();
		nodeTwo.cleanNode();
		nodeOne.cleanNode();;
		nodeOne = null;
		nodeTwo = null;
		nodeThree = null;
		nodeOne = new AVLNode<Integer>(integerList[1]);
		nodeTwo = new AVLNode<Integer>(integerList[2]);
		nodeThree = new AVLNode<Integer>(integerList[3]);
		
		nodeOne.connectRightChild(nodeThree);
		nodeThree.connectLeftChild(nodeTwo);
		nodeThree.connectRightChild(nodeSix);
		nodeSix.connectLeftChild(nodeFour);
		nodeFour.connectRightChild(nodeFive);
		NodeBalancer.rotateDoubleWithLeftChild(nodeSix);
		assertTrue(nodeFour.isEmpty());
		assertTrue(nodeSix.isEmpty());
		assertFalse(nodeFive.isLeftEmpty());
		assertFalse(nodeFive.isRightEmpty());
		assertEquals(nodeFive.getLeftChildObject(), nodeFour.getObject());
		assertEquals(nodeFive.getRightChildObject(), nodeSix.getObject());
		assertEquals(nodeFive.getParentNode().getObject(), nodeThree.getObject());
		assertEquals(nodeThree.getRightChildObject(), nodeFive.getObject());
	}

	@Test
	void testRotateWithRightChild() {
		nodeOne.connectRightChild(nodeTwo);
		nodeTwo.connectRightChild(nodeThree);
		NodeBalancer.rotateWithRightChild(nodeOne);
		assertTrue(nodeOne.isEmpty());
		assertTrue(nodeThree.isEmpty());
		assertFalse(nodeTwo.isLeftEmpty());
		assertFalse(nodeTwo.isRightEmpty());
		assertEquals(nodeTwo.getLeftChildObject(), nodeOne.getObject());
		assertEquals(nodeTwo.getRightChildObject(), nodeThree.getObject());
		assertNull(nodeTwo.getParentNode());
	}

	@Test
	void testRotateDoubleWithRightChild() {
		// Simple test of double rotation
		nodeOne.connectRightChild(nodeThree);
		nodeThree.connectLeftChild(nodeTwo);
		NodeBalancer.rotateDoubleWithRightChild(nodeOne);
		assertTrue(nodeOne.isEmpty());
		assertTrue(nodeThree.isEmpty());
		assertFalse(nodeTwo.isLeftEmpty());
		assertFalse(nodeTwo.isRightEmpty());
		assertEquals(nodeTwo.getLeftChildObject(), nodeOne.getObject());
		assertEquals(nodeTwo.getRightChildObject(), nodeThree.getObject());
		assertNull(nodeTwo.getParentNode());
		
		//More Complicated test of Right Left rotation
		nodeThree.cleanNode();
		nodeTwo.cleanNode();
		nodeOne.cleanNode();;
		nodeOne = null;
		nodeTwo = null;
		nodeThree = null;
		nodeOne = new AVLNode<Integer>(integerList[1]);
		nodeTwo = new AVLNode<Integer>(integerList[2]);
		nodeThree = new AVLNode<Integer>(integerList[3]);
		
		nodeOne.connectRightChild(nodeThree);
		nodeThree.connectLeftChild(nodeTwo);
		nodeThree.connectRightChild(nodeFour);
		nodeFour.connectRightChild(nodeSix);
		nodeSix.connectLeftChild(nodeFive);
		NodeBalancer.rotateDoubleWithRightChild(nodeFour);
		assertTrue(nodeFour.isEmpty());
		assertTrue(nodeSix.isEmpty());
		assertFalse(nodeFive.isLeftEmpty());
		assertFalse(nodeFive.isRightEmpty());
		assertEquals(nodeFive.getLeftChildObject(), nodeFour.getObject());
		assertEquals(nodeFive.getRightChildObject(), nodeSix.getObject());
		assertEquals(nodeFive.getParentNode().getObject(), nodeThree.getObject());
		assertEquals(nodeThree.getRightChildObject(), nodeFive.getObject());
	}

}
