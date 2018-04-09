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
		boolean objectFound = false;
		if (someObject != null) {
			AVLNode<TreeObject> searchedNode = findNodeWithObject(someObject);
			if (searchedNode != null) {
				objectFound = true;
			} else {
				objectFound = false;
			}
		}
		return objectFound;
	}

	// Delete an object from the tree.
	// Return true if the object was deleted.
	// Return false if no object was found to be deleted.
	public boolean deleteObject(TreeObject someObject) {
		boolean deletionStatus = false;
		// If someObject is NULL, or there is no ROOT node
		if (someObject == null || this.root == null) {
			// Return FALSE because there is no object to look for
			// Or the tree is empty and there is nothing to delete.
			return deletionStatus;
		}
		// Find the nodeForDeletion with findNodeWithObject
		AVLNode<TreeObject> nodeForDeletion = findNodeWithObject(someObject);
		// If nodeForDeletion is NULL
		if (nodeForDeletion == null) {
			// Return FALSE; No node was found to delete
			return deletionStatus;
		}

		// If the node has no children
		if (nodeForDeletion.isEmpty()) {
			// If the node being deleted is the root
			if (areNodesEqual(nodeForDeletion, this.root)) {
				// Then set the root equal to null, there will be no more nodes in the tree
				// after deletion.
				this.root = null;
			}
			// Simply delete the node; No balance problems when deleting a leaf
			nodeForDeletion.deleteNode();
			// Set the deletion status to TRUE;
			deletionStatus = true;

			// Else If the nodeForDeletion only has a left Child
		} else {

			// Find the replacmentNode;
			AVLNode<TreeObject> replacmentNode = null;
			if (!nodeForDeletion.isRightEmpty() && !nodeForDeletion.isRightEmpty()) {
				// Call the deleteMinimum node on the nodeForDeletion's right Subtree
				// Save replacmentNode as the node returned from deleteMinimum
				replacmentNode = removeMinimumNode(nodeForDeletion.getRightChildNode());
			} else if (!nodeForDeletion.isLeftEmpty()) {
				// Set the replacmentNode equal to the nodeForDeletion's left child
				replacmentNode = nodeForDeletion.getLeftChildNode();

				// else If the nodeForDeletion only has a right Child
			} else if (!nodeForDeletion.isRightEmpty()) {
				// Set the replacmentNode equal to the nodeForDeletion's right child
				replacmentNode = nodeForDeletion.getRightChildNode();
			}

			if (replacmentNode != null) {

				System.out.println(
						"AVLTREE :: deleteOBject :: " + " Replacment Node Value is: " + replacmentNode.getObject());
				System.out.println(
						"AVLTREE :: deleteOBject :: " + " Node for Deletion Value is: " + nodeForDeletion.getObject());

				// Delete nodeForDeletion by replacing it's object/value with that of the
				// replacmentNode.
				nodeForDeletion.setObject(replacmentNode.getObject());
				System.out.println("AVLTREE :: deleteOBject :: " + " After replacment node for deletion value is: "
						+ nodeForDeletion.getObject());
				// Call the balanceToRoot routine; Balance's all nodes that may have a new
				// balance
				balanceToRoot(replacmentNode, nodeForDeletion);
				// Deletes the replacmentNode, it has replaced the nodeForDeletion
				replacmentNode.deleteNode();
				// Set the deletion status to TRUE;
				deletionStatus = true;
			}

		}

		return deletionStatus;
	}

	// Deletes all nodes in the tree
	public void deleteTree() {
		AVLNode<TreeObject> currentNode = null;
		ArrayDeque<AVLNode<TreeObject>> deleteList = new ArrayDeque<AVLNode<TreeObject>>();
		if (this.root != null) {
			deleteList.push(this.root);
			this.root = null;
		}
		while (!deleteList.isEmpty()) {
			currentNode = deleteList.pop();
			if (!currentNode.isLeftEmpty()) {
				deleteList.push(currentNode.getLeftChildNode());
			}
			if (!currentNode.isRightEmpty()) {
				deleteList.push(currentNode.getRightChildNode());
			}

			currentNode.deleteNode();
			currentNode = null;
		}
	}

	// Print Tree
	public void printTree() {
		System.out.println("****** Printing  Tree ******");
		if (this.root == null) {
			System.out.println("This tree is empty.");
			return;
		}
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
		System.out.println("****** End Print Tree *****");
		System.out.println("");
	}

	// Gets the tree root node.
	// Only for testing and debugging purposes.
	// If you use the node given as a replacment for tree functionality any problems
	// or error are your own fault.
	public AVLNode<TreeObject> getTreeRoot() {
		return this.root;
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
				// Perform a post rotation correction.
				postRotationRootEvaluation(parentNode);
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

				// Perform a post rotation correction.
				postRotationRootEvaluation(parentNode);
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
	// Returns null if no node is found
	private AVLNode<TreeObject> findNodeWithObject(TreeObject someObject) {
		// Create found node flag.
		boolean nodeFound = false;
		// Create a searchedNode AVLNode for the node we are looking for.
		AVLNode<TreeObject> searchedNode = null;
		// Create a currentNode for the Node were currently checking.
		// Set it as the root nod.
		AVLNode<TreeObject> currentNode = this.root;
		// While the searchedNode has not been found and the currentNode is not NULL
		// Note: If the currentNode is NULL it means we've searched all the way down the
		// tree without finding the node we want.
		while (!nodeFound && currentNode != null) {
			// If the currentNode's object and the object someObject are equal
			if (someObject.compareTo(currentNode.getObject()) == 0) {
				// We found the node were looking for.
				// Set the searchedNode equal to the currentNode
				searchedNode = currentNode;
				// Indicate we have found the ndode.
				nodeFound = true;
				// Else if the object someObject is less than the currentNode's object
			} else if (someObject.compareTo(currentNode.getObject()) == -1) {
				// We need to check against to move down to the left child.
				// Set the currentNode equal to the currentNode's left child
				currentNode = currentNode.getLeftChildNode();
				// Else the currentNode's object is greater than the object someObject
			} else {
				// We need move down to the right child.
				// Set the currentNode equal to the currentNode's right child.
				currentNode = currentNode.getRightChildNode();
			}
		}
		// Return the searchedNode; Whatever it's contents be
		return searchedNode;
	}

	private AVLNode<TreeObject> removeMinimumNode(AVLNode<TreeObject> someNode) {
		// If someNode is NULL
		if (someNode == null) {
			// Return NULL; We cant find the minimum of a nod that does not exist
			return null;
		}

		// Set minimumNode equal to someNode
		AVLNode<TreeObject> minimumNode = someNode;
		// Create parentNode
		AVLNode<TreeObject> parentNode = null;
		// Create nodeRemoved flag
		boolean nodeRemoved = false;
		// While notRemoved
		while (!nodeRemoved) {
			// If minimumNode has a left child
			if (!minimumNode.isLeftEmpty()) {
				// Set minimumNode equal to minimumNode's left child
				minimumNode = minimumNode.getLeftChildNode();

				// Else minimumNode does not have a left child
			} else {
				// If minimumNode is not someNode
				if (!areNodesEqual(minimumNode, someNode)) {
					// Set parentNode equal to minimumNode's parent
					parentNode = minimumNode.getParentNode();
				}
				// Remove minimumNode's parent
				minimumNode.removeParentNodeConnection();
				// Set nodeRemoved to TRUE;
				nodeRemoved = true;
			}
		}

		// While parentNode is not null or does not equal someNode
		while (parentNode != null && !areNodesEqual(parentNode, someNode)) {
			// If the parentNode is not balanced
			if (parentNode.calculateBalance() > 1) {
				// Balance the parentNode
				NodeBalancer.rotateWithLeftChild(parentNode);
				// Perform a post rotation correction.
				postRotationRootEvaluation(parentNode);
				// Set parentNode equal to it's replacement after the rotation
				parentNode = parentNode.getParentNode();
			}
			// Set the parentNode equal to it's parent
			parentNode = parentNode.getParentNode();
		}
		// return the minimumNode;
		return minimumNode;
	}

	private void balanceToRoot(AVLNode<TreeObject> someNode, AVLNode<TreeObject> removedNode) {
		// Create currentNode equal to someNode
		AVLNode<TreeObject> currentNode = someNode;
		// While currentNode is not NULL
		while (currentNode != null) {
			// If this node is unbalanced
			if (currentNode.calculateBalance() > 1) {
				// If removed node was less than currentNode
				if (isLeftNodeLessThan(removedNode, currentNode)) {
					// RotateWithRightChild on currentNode
					NodeBalancer.rotateWithRightChild(currentNode);
					// Else if removed node is greater than the currentNode
				} else {
					// RotateWithLeftChild on currentNode
					NodeBalancer.rotateWithLeftChild(currentNode);
				}
				// Perform a post rotation correction.
				postRotationRootEvaluation(currentNode);
				// Set currentNode equal to currentNode's parent
				// They changed places during the rotation.
				currentNode = currentNode.getParentNode();
			}
			// Set currentNode equal to it's parentNode; Moving up to the next level.
			currentNode = currentNode.getParentNode();
		}
	}

	private void postRotationRootEvaluation(AVLNode<TreeObject> someNode) {
		// If the some node is equal to the current root;
		if (areNodesEqual(someNode, this.root)) {
			// Then the root is no longer correct after a rotation.
			// The root needs to be changed.
			// Set the new root to be the parent node's parent
			this.root = someNode.getParentNode();
		}
	}

}
