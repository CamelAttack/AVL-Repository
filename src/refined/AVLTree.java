package refined;

import refined.AVLNode.LeafDirection;

public class AVLTree<TreeObject extends Comparable<TreeObject>> {
	// *****PRIVATE Objects
	// The tree root
	private AVLNode<TreeObject> root;
	private NodeBalancer balancer;

	// *****PUBLIC Functions

	AVLTree() {
		root = null;
		balancer = new NodeBalancer();
	}

	AVLTree(TreeObject someObject) {
		root = new AVLNode<TreeObject>(someObject);
		balancer = new NodeBalancer();
	}

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

}
