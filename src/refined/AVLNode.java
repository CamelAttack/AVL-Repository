package refined;

public class AVLNode<NodeValue extends Comparable<NodeValue>> {

	// ************************************** STATIC CLASS MEMBERS
	public static enum LeafDirection {
		LEFT, RIGHT, NONE
	}

	// ************************************** PRIVATE MEMBERS
	// Height of the Node
	private int height;
	// Object contained by the Node
	private NodeValue nodeValue;
	// Left Child Node
	private AVLNode<NodeValue> leftChild;
	// Right Child Node
	private AVLNode<NodeValue> rightChild;
	// Parent Node
	private AVLNode<NodeValue> parentNode;
	// Leaf Direction
	private LeafDirection leafDirection;

	// ****************************************PUBLIC METHODS
	// Standard Constructor with Object
	AVLNode(NodeValue someValue) {
		this.nodeValue = someValue;
		this.height = 0;
	}

	// Constructor with Object and Parent
	AVLNode(NodeValue someValue, AVLNode<NodeValue> someNode) {
		this.nodeValue = someValue;
		this.parentNode = someNode;
		this.height = 0;
	}

	// Return the Node Height as an integer;
	public int getHeight() {
		return this.height;
	}

	// Update the height of this node and it's subtrees.
	// Recursively gets the height by running this routine on the left and right
	// subtrees.
	// Return the value of the new height.
	public int updateNodeHeightDeep() {
		int leftHeight = -1;
		int rightHeight = -1;
		if (this.leftChild != null) {
			leftHeight = this.leftChild.updateNodeHeightDeep();
		}
		if (this.rightChild != null) {
			rightHeight = this.rightChild.updateNodeHeightDeep();
		}
		int highHeight = Math.max(leftHeight, rightHeight);
		this.height = highHeight + 1;
		return this.height;
	}

	// Update the height of this node using the current height of it's subtrees.
	// Uses the highest value between it's left and right subtrees to get it's new
	// height.
	// After updating it's height returns the new height value.
	public int updateNodeHeightShallow() {
		int leftHeight = -1;
		int rightHeight = -1;
		if (this.leftChild != null) {
			leftHeight = this.leftChild.getHeight();
		}
		if (this.rightChild != null) {
			rightHeight = this.rightChild.getHeight();
		}
		int highHeight = Math.max(leftHeight, rightHeight);
		this.height = highHeight + 1;
		return this.height;
	}

	// Return the Balance Value of this Node; Where Balance is the Height of the
	// Right Node minus Height of the Left Node
	public int calculateBalance() {
		int balance = 0;
		// Height of the Left Subtree
		int leftHeight = -1;
		// Height of the Right Subtree
		int rightHeight = -1;
		// If the left subtree exists
		if (leftChild != null) {
			// Get the height of left subtree
			leftHeight = leftChild.getHeight();
		}
		// If the right subtree exists
		if (rightChild != null) {
			// Get the height of the right subtree
			rightHeight = rightChild.getHeight();
		}
		// Subtract the height of the right subtree from the height of the left subtree
		balance = rightHeight - leftHeight;
		// Set it to a positive value using the absolute value;
		// For reference we don't care if the result is positive or negative only if the
		// difference is greater than 1.
		balance = Math.abs(balance);
		// Return the balance integer we calculated.
		return balance;
	}

	// Set Node Object
	public void setObject(NodeValue someValue) {
		this.nodeValue = someValue;
	}

	// Return a reference of the Node's Object
	public NodeValue getObject() {
		return this.nodeValue;
	}

	public boolean hasParent() {
		if (this.parentNode != null) {
			return true;
		}
		return false;
	}

	// Return a leaf direction indicating which leaf of it's parent the node is on.
	public LeafDirection getLeafDirection() {
		return leafDirection;
	}

	// Returns true if this node has no children;
	// Returns false if either the left or right child has a node;
	public boolean isEmpty() {
		if (this.leftChild == null && this.rightChild == null) {
			return true;
		}
		return false;
	}

	// Returns true if the left child is null.
	// Returns false if the left child has a node.
	public boolean isLeftEmpty() {
		if (this.leftChild == null)
			return true;
		return false;
	}

	// Returns true if the right child is empty.
	// Returns false if the right child has a node.
	public boolean isRightEmpty() {
		if (this.rightChild == null)
			return true;
		return false;
	}

	// Set the Left Child with a given Node; Set that Node's Parent as this node
	// Update this Node's Height with a Shallow Update
	public void connectLeftChild(AVLNode<NodeValue> someNode) {
		this.leftChild = someNode;
		if (this.leftChild != null) {
			this.leftChild.setParentNode(this);
			this.leftChild.setLeafDirection(LeafDirection.LEFT);
		}
		this.updateNodeHeightShallow();
	}

	// Return a Reference to the Left Child
	public AVLNode<NodeValue> getLeftChildNode() {
		return this.leftChild;
	}

	// Return a reference to the Left Child's Object
	// Otherwise return null.
	public NodeValue getLeftChildObject() {
		if (this.leftChild != null)
			return this.leftChild.getObject();
		return null;
	}

	// Deletes all connection between this node and it's left child.
	// Returns the child. Shallow Height Update.
	public AVLNode<NodeValue> disconnectLeftChild() {
		if (this.leftChild != null) {
			AVLNode<NodeValue> someNode = this.leftChild;
			this.leftChild = null;
			someNode.setParentNode(null);
			someNode.setLeafDirection(LeafDirection.NONE);
			this.updateNodeHeightShallow();
			return someNode;
		}
		return null;
	}

	// Set the Right Child with a given Node; Set that Node's Parent as this Node
	public void connectRightChild(AVLNode<NodeValue> someNode) {
		this.rightChild = someNode;
		if (this.rightChild != null) {
			this.rightChild.setParentNode(this);
			this.rightChild.setLeafDirection(LeafDirection.RIGHT);
		}
		this.updateNodeHeightShallow();
	}

	// Return a reference to the Right Child
	public AVLNode<NodeValue> getRightChildNode() {
		return this.rightChild;
	}

	// Return a reference of Right Child's Object
	public NodeValue getRightChildObject() {
		if (this.rightChild != null)
			return this.rightChild.getObject();
		return null;
	}

	// Deletes all connection between this node and it's right child.
	// Returns the child.
	public AVLNode<NodeValue> disconnectRightChild() {
		if (this.rightChild != null) {
			AVLNode<NodeValue> someNode = this.rightChild;
			this.rightChild = null;
			someNode.setParentNode(null);
			someNode.setLeafDirection(LeafDirection.NONE);
			this.updateNodeHeightShallow();
			return someNode;
		}
		return null;
	}

	// Get a Reference to this Node's Parent
	public AVLNode<NodeValue> getParentNode() {
		return this.parentNode;
	}

	// Return string with all the information about this node; Includes: Value,
	// Height, Parent,
	// Balance, and Children
	public String nodeToString() {
		StringBuilder builder = new StringBuilder("Node Object: ");
		// Append the object for this node
		builder.append(this.nodeValue);
		// Append the height for this node
		builder.append(" Height: ");
		builder.append(this.height);
		// Append the balance for this node
		builder.append(" Balance: ");
		builder.append(this.calculateBalance());
		builder.append(" Parent Value: ");
		// If this node has a parent
		if (this.parentNode != null)
			// Append the object of the parent
			builder.append(this.getParentObject());
		else
			// Else append NULL in place of the parent's object
			builder.append("NULL");
		builder.append(" Children: LEFT: ");
		// If this node has a left child
		if (this.leftChild != null)
			// Append the object of the left child
			builder.append(this.getLeftChildObject());
		else
			// Else append null in place of the left child's object
			builder.append("NULL");
		builder.append(" RIGHT: ");
		// If this node has a right child
		if (this.rightChild != null)
			// Append the object of the right child
			builder.append(this.getRightChildObject());
		else
			// Else append null in place of the right child's object
			builder.append("NULL");
		// Return the string that has been built
		return builder.toString();
	}

	public void deleteNode() {
		nodeValue = null;
		if(parentNode != null) {
			if(leafDirection == LeafDirection.LEFT) {
				parentNode.disconnectLeftChild();
			} else if(leafDirection == LeafDirection.RIGHT) {
				parentNode.disconnectRightChild();
			}
		}
		parentNode = null;
		leftChild = null;
		rightChild = null;
		leafDirection = null;
	}

	// ***********************************PRIVATE Methods

	// Set this Node's Parent
	private void setParentNode(AVLNode<NodeValue> someNode) {
		this.parentNode = someNode;
	}

	// Set the leaf direction of the node indicating which leaf of it's parent the
	// node is on.
	private void setLeafDirection(LeafDirection someDirection) {
		leafDirection = someDirection;
	}

	// Returns a reference to the object contained by the parent of this node.
	// This method may end up being unused which is why it's private for now.
	// DELETE this routine if it goes unused.
	private NodeValue getParentObject() {
		return parentNode.getObject();
	}

}
