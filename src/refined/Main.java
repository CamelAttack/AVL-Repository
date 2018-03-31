package refined;

public class Main {

	public static void main(String[] args) {
		System.out.println("Here we have a basic main program. /\n");
		AVLTree<Integer> ourTree = new AVLTree<Integer>();
		
		//Small Tree test
		ourTree.insertObject(buildInteger(1));
		ourTree.printTree();
		System.out.println(" ");
		ourTree.insertObject(buildInteger(2));
		ourTree.insertObject(buildInteger(3));
		ourTree.printTree();
		System.out.println(" ");
		
		//Simple Delete Test
		ourTree.deleteObject(buildInteger(3));
		ourTree.printTree();
		System.out.println(" ");
		ourTree.insertObject(buildInteger(3));
		
		ourTree.insertObject(buildInteger(4));
		ourTree.insertObject(buildInteger(5));
		ourTree.insertObject(buildInteger(6));
		ourTree.printTree();

		if (ourTree.checkForObject(buildInteger(6))) {
			System.out.println("We found the integer 6 in the tree.");
		} else {
			System.err.println("We did nont find the integer 6  in the tree.");
		}

		if (ourTree.checkForObject(buildInteger(4))) {
			System.out.println("We found the integer 4 in the tree.");
		} else {
			System.err.println("We did nont find the integer 4  in the tree.");
		}

		if (ourTree.checkForObject(buildInteger(1))) {
			System.out.println("We found the integer 1 in the tree.");
		} else {
			System.err.println("We did nont find the integer 1 in the tree.");
		}
	}

	public static Integer buildInteger(int someNumber) {
		return new Integer(someNumber);
	}

}
