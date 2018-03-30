package refined;

public class Main {

	public static void main(String[] args) {
		System.out.println("Here we have a basic main program.");
		AVLTree<Integer> ourTree = new AVLTree<Integer>();
		ourTree.insertObject(buildInteger(1));
		ourTree.printTree();
		System.out.println(" ");
		ourTree.insertObject(buildInteger(2));
		ourTree.insertObject(buildInteger(3));
		ourTree.printTree();
		System.out.println(" ");
		ourTree.insertObject(buildInteger(4));
		ourTree.insertObject(buildInteger(5));
		ourTree.insertObject(buildInteger(6));
		ourTree.printTree();
	}

	public static Integer buildInteger(int someNumber) {
		return new Integer(someNumber);
	}

}
