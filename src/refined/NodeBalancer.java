package refined;

import refined.AVLNode.LeafDirection;

public class NodeBalancer {

	public static void rotateWithLeftChild(AVLNode someNode) {
		// Pop the left child node from someNode
		// Set the left child node as the new parent node.
		AVLNode newParentNode = someNode.disconnectLeftChild();
		// Pop the right child node from the new parent node.
		AVLNode rightChildNode = newParentNode.disconnectRightChild();
		// Connect the popped right child as someNode's left child
		someNode.connectLeftChild(rightChildNode);
		swapNodePositions(someNode, newParentNode, LeafDirection.LEFT);
	}

	//Perform a left-right double rotation
	public static void rotateDoubleWithLeftChild(AVLNode someNode) {
		//Rotate the left child with it's right child
		rotateWithRightChild(someNode.getLeftChildNode());
		//Now rotate with the left child
		rotateWithLeftChild(someNode);
	}

	public static void rotateWithRightChild(AVLNode someNode) {
		// Pop the right child node from someNode
		// Set the right child node as the new parent node.
		AVLNode newParentNode = someNode.disconnectRightChild();
		// Pop the left child node from the new parent node.
		AVLNode leftChildNode = newParentNode.disconnectLeftChild();
		// Connect the left child node as someNode's right child.
		someNode.connectRightChild(leftChildNode);
		swapNodePositions(someNode, newParentNode, LeafDirection.RIGHT);
	}

	//Perform a Right-Left double rotation.
	public static void rotateDoubleWithRightChild(AVLNode someNode) {
		//Rotate the right child with it's left child
		rotateWithLeftChild(someNode.getRightChildNode());
		//Now rotate with the right child child
		rotateWithRightChild(someNode);
	}

	// Swap the position of two nodes in the indicated direction.
	// If the direction given is not LEFT, or RIGHT then no swap will occur.
	private static void swapNodePositions(AVLNode oldParentNode, AVLNode newParentNode, LeafDirection swapDirection) {
		// Save a reference to oldParentNode's parent as superParent.
		AVLNode superParentNode = oldParentNode.getParentNode();
		// Save oldParentNode's leaf direction
		LeafDirection superParentDirection = oldParentNode.getLeafDirection();
		// If the swap direction is LEFT.
		if (swapDirection == LeafDirection.LEFT) {
			// Connect oldParentNode as newParentNode's right child.
			newParentNode.connectRightChild(oldParentNode);
			// Else if the swap direction is RIGHT.
		} else if (swapDirection == LeafDirection.RIGHT) {
			// Connect oldParentNode as newParentNode's left child.
			newParentNode.connectLeftChild(oldParentNode);
		}
		// Else if the swapDirection is NONE don't perform a swap, because that means no swap occured.
		// If the swap direction is not NONE, that indicates that a swap did occur.
		if (swapDirection != LeafDirection.NONE) {
			// Connect newParent to superParent using connectUdingLeafDirection routine.
			connectUsingLeafDirection(newParentNode, superParentNode, superParentDirection);
		}
	}

	// Connect one node to another node as it's child.
	// Uses a leaf direction to determine which side to connect it to.
	private static void connectUsingLeafDirection(AVLNode someNode, AVLNode parentNode, LeafDirection direction) {
		// If the direction is LEFT
		if (direction == LeafDirection.LEFT) {
			// Connect someNode to the parentNode as it's Left Child.
			parentNode.connectLeftChild(someNode);
			// Else if the direction is RIGHT
		} else if (direction == LeafDirection.RIGHT) {
			// Connect someNode to the parentNode as it's Right Child.
			parentNode.connectRightChild(someNode);
		}
		// If the direction was NONE don't do anything because it means there is no
		// parent node.
	}
}
