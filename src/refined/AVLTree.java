package refined;

import java.util.ArrayDeque;

import refined.AVLNode;

public class AVLTree<TreeObject extends Comparable<TreeObject>> {
	// *****PRIVATE Objects
	// The tree root
	private AVLNode<TreeObject> root;

	// *****PUBLIC Functions

	AVLTree() {
		root = null;
	}

	AVLTree(TreeObject someObject) {
		root = new AVLNode<TreeObject>(someObject);
	}

	// Inserts an Object into the tree.
	public boolean insertObject(TreeObject someObject) {
		boolean insertComplete = false;
		// If someObject is null
		if (someObject == null) {
			// Exit the routine by returning false
			// We do not insert null object into the tree
			return false;
		}

		// Create an AVL Node with the given object to be inserted into the tree.
		AVLNode<TreeObject> someNode = new AVLNode<TreeObject>(someObject);
		// If Root is null
		if (this.root == null) {
			// Set the root as some node
			this.root = someNode;
			insertComplete = true;
			// Else if root is not null
		} else {
			// Call the routine that inserts the newly created node into the tree.
			insertComplete = insertNode(this.root, someNode);
		}
		// Operation Complete return true.
		return insertComplete;
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
	public void printTree() {
		AVLNode<TreeObject> currentNode = null;
		ArrayDeque<AVLNode<TreeObject>> printList = new ArrayDeque<AVLNode<TreeObject>>();

		System.out.println("Root: " + this.root.nodeToString());
		printList.add(this.root);

		while (!printList.isEmpty()) {
			currentNode = printList.poll();
			System.out.println(currentNode.nodeToString());
			if (!currentNode.isLeftEmpty())
				printList.add(currentNode.getLeftChildNode());
			if (!currentNode.isRightEmpty())
				printList.add(currentNode.getRightChildNode());
		}
	}

	// *****PRIVATE Functions

	// Deletes a node from the tree matching a given object.
	// Returns the node that has been deleted.
	// Returns null if no node was found for deletion.
	private AVLNode<TreeObject> deleteNode(TreeObject someObject) {
		return null;
	}

	// Inserts a node into the tree recursively.
	// Balances the tree after insertion.
	private boolean insertNode(AVLNode<TreeObject> parentNode, AVLNode<TreeObject> someNode) {
		// Create boolean flag to indicate if an object was inserted
		boolean nodeInserted;

		// If someNode is less than parentNode
		if (isLeftNodeLessThan(someNode, parentNode)) {
			// If parentNode's left child null
			if (parentNode.isLeftEmpty()) {
				// Mark node inserted as true
				nodeInserted = true;
				// Connect someNode as parentNode's left child
				parentNode.connectLeftChild(someNode);
				// Else
			} else {
				// Call insertNode on parentNode's left child
				nodeInserted = insertNode(parentNode.getLeftChildNode(), someNode);
			}

			parentNode.updateNodeHeightShallow();
			// If a node was inserted and the balance of the parentNode is greater than 1
			if (nodeInserted && parentNode.calculateBalance() > 1) {
				if (isLeftNodeLessThan(someNode, parentNode.getLeftChildNode())) {
					NodeBalancer.rotateWithLeftChild(parentNode);
				} else {
					NodeBalancer.rotateDoubleWithLeftChild(parentNode);
				}
				
				//If the parent node is equal to the root;
				if(areNodesEqual(parentNode, this.root)) {
					//Set the new root to the parent node's parent
					this.root = parentNode.getParentNode();
				}
			}
			
			// Else-If someNode is greater than parentNode
		} else if (isLeftNodeGreaterThan(someNode, parentNode)) {
			// If parentNode's right child is null
			if (parentNode.isRightEmpty()) {
				// Mark node inserted as true
				nodeInserted = true;
				// Connect someNode as parentNode's right child
				parentNode.connectRightChild(someNode);
				// Else
			} else {
				// Call insertNode on parentNode's right child
				nodeInserted = insertNode(parentNode.getRightChildNode(), someNode);
			}

			parentNode.updateNodeHeightShallow();
			// If a node was inserted and the balance of the parentNode is greater than 1
			if (nodeInserted && parentNode.calculateBalance() > 1) {
				if (isLeftNodeGreaterThan(someNode, parentNode.getRightChildNode())) {
					NodeBalancer.rotateWithRightChild(parentNode);
				} else {
					NodeBalancer.rotateDoubleWithRightChild(parentNode);
				}
				
				//If the parent node was the root
				if(areNodesEqual(parentNode, this.root)) {
					//Set the new root equal to the parent node's parent
					this.root = parentNode.getParentNode();
				}
			}
			// Else; someNode is equal to currentNode
		} else {
			// Set nodeInserted to FALSE; We don't insert equal values
			nodeInserted = false;
		}

		return nodeInserted;
	}

	// Check if the left node's value is less than the right node's value.
	// Return true if the left node is lesser.
	// Otherwise return false;
	private boolean isLeftNodeLessThan(AVLNode<TreeObject> leftNode, AVLNode<TreeObject> rightNode) {
		if (leftNode == null || rightNode == null) {
			return false;
		} else if (leftNode.getObject().compareTo(rightNode.getObject()) == -1) {
			return true;
		}
		return false;
	}

	// Check if the left node's value is greater than the the right node's value.
	// Return true if the left node is greater.
	// Otherwise return false.
	private boolean isLeftNodeGreaterThan(AVLNode<TreeObject> leftNode, AVLNode<TreeObject> rightNode) {
		if (leftNode == null || rightNode == null) {
			return false;
		} else if (leftNode.getObject().compareTo(rightNode.getObject()) == 1) {
			return true;
		}
		return false;
	}

	// Check if the two nodes have equal values.
	// Return true if the values of the two nodes are equal
	// Otherwise return false.
	private boolean areNodesEqual(AVLNode<TreeObject> leftNode, AVLNode<TreeObject> rightNode) {
		if (leftNode == null || rightNode == null) {
			return false;
		} else if (leftNode.getObject().compareTo(rightNode.getObject()) == 0) {
			return true;
		}
		return false;
	}

	// Find and return a node in the tree matching a given object.
	private AVLNode<TreeObject> findNodeWithObject(TreeObject someObjecct) {
		return null;
	}

}
