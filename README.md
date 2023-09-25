# N-ary-Tree
N-ary Tree implementation using Java. The project was done for the Algorithm and Data Structures course at University of Florence.

## Functionalities:

1.  Add a root to the tree or set a new root.
2.  Find the first occurrence of a node based on its content.
3.  Add a node to the tree as a child of a specific parent node. You can also add it in a specific position among the children of a parent node.
4.  Remove the first occurrence of a node based on its content.
5.  Remove a node in a specific position among the children of a parent node.
6.  Check if the tree is empty.
7.  Get the pre-order/post-order/level-order traversal path as a List.
8.  Get the height of the tree.

## Example Usage:

		//Create a N-ary tree with arity 5 and add a root node
		NAryTree<String> tree = new NAryTree<>(5);
		TreeNode<String> root = tree.setRoot("example");
		
		//Add nodes to the Tree
		TreeNode<String> child1 = tree.add(root, "Child 1");
		TreeNode<String> child2 = tree.addAt(root, "Child 1",2);
		
		//Do a level-order traversal
		List<TreeNode<String>> levelOrder = tree.levelOrderTraversal();










