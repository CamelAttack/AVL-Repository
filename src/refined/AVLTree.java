package refined;

import refined.AVLNode.LeafDirection;

public class AVLTree<TreeObject extends Comparable<TreeObject>> {
	// *****PRIVATE Objects
	// The tree root
	private AVLNode<TreeObject> root;

	// *****PUBLIC Functions
	// Inserts an Object into the tree.
	public void insertObject(TreeObject someObject) {
		// Create an AVL Node with the given object to be inserted into the tree.
		AVLNode<TreeObject> someNode = new AVLNode<TreeObject>(someObject);
		// Call the function that inserts the newly created node into the tree.
		insertNode(someNode);
	}

	// Check if an object exists in the tree.
	// Return true if the object exists, otherwise return false if the object is not
	// found.
	public boolean checkForObject(TreeObject someObject) {
		return false;
	}

	// Delete an object from the tree.
	// Return true if the object was deleted.
	// Return false if no object was found to be deleted.
	public boolean deleteObject(TreeObject someObject) {
		AVLNode<TreeObject> someNode = deleteNode(someObject);
		if (someNode != null) {
			return true;
		}
		return false;
	}
	// Print Tree

	// *****PRIVATE Functions

	// Deletes a node from the tree matching a given object.
	// Returns the node that has been deleted.
	// Returns null if no node was found for deletion.
	private AVLNode<TreeObject> deleteNode(TreeObject someObject) {
		return null;
	}

	// Inserts a node into the tree.
	// Balances the tree after insertion.
	private void insertNode(AVLNode<TreeObject> someNode) {
		// Do something;
	}

	// Find and return a node in the tree matching a given object.
	private AVLNode<TreeObject> findNodeWithObject(TreeObject someObjecct) {
		return null;
	}

	// Balance the Tree to the Left
	private void rotateWithLeftChildNode(AVLNode<TreeObject> someNode) {
		// Pop the left child node from someNode
		// Set the left child node as the new parent node.
		AVLNode<TreeObject> newParentNode = someNode.disconnectLeftChild();
		// Pop the right child node from the new parent node.
		AVLNode<TreeObject> rightChildNode = newParentNode.disconnectRightChild();
		// Connect the popped right child as someNode's left child
		someNode.connectLeftChild(rightChildNode);
		swapNodePositions(someNode, newParentNode, LeafDirection.LEFT);

	}

	// Balance the Tree to the Right
	private void rotateWithRightChildNode(AVLNode<TreeObject> someNode) {
		// Pop the right child node from someNode
		// Set the right child node as the new parent node.
		AVLNode<TreeObject> newParentNode = someNode.disconnectRightChild();
		// Pop the left child node from the new parent node.
		AVLNode<TreeObject> leftChildNode = newParentNode.disconnectLeftChild();
		// Connect the left child node as someNode's right child.
		someNode.connectRightChild(leftChildNode);
		swapNodePositions(someNode, newParentNode, LeafDirection.RIGHT);
	}

	//Swap the position of two nodes in the indicated direction.
	//If the direction given is not LEFT, or RIGHT then no swap will occur.
	private void swapNodePositions(AVLNode<TreeObject> oldParentNode, AVLNode<TreeObject> newParentNode,
			LeafDirection swapDirection) {
		// Save a reference to oldParentNode's parent as superParent.
		AVLNode<TreeObject> superParentNode = oldParentNode.getParentNode();
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
		//Else if the swapDirection is NONE don't perform a swap.
		// If the swap direction is not NONE, that indicates that a swap did occur.
		if (swapDirection != LeafDirection.NONE) {
			// Connect newParent to superParent using connectUdingLeafDirection routine.
			connectUsingLeafDirection(newParentNode, superParentNode, superParentDirection);
		}
	}

	// Connect one node to another node as it's child.
	// Uses a leaf direction to determine which side to connect it to.
	private void connectUsingLeafDirection(AVLNode<TreeObject> someNode, AVLNode<TreeObject> parentNode,
			LeafDirection direction) {
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
