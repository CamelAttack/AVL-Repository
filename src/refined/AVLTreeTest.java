package refined;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AVLTreeTest {

	AVLTree<Integer> testTree;
	
	@BeforeEach
	void setUp() throws Exception {
		testTree = new AVLTree<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInsertObject() {
		assertFalse( testTree.insertObject(null) );
	}

}
