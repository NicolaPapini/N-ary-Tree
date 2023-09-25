package n_ary_tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class NAryTree<T> {
	private TreeNode<T> root;
	private int arity;
	
	public NAryTree(int arity) {
		if (arity<0) {
			throw new IllegalArgumentException("Arity needs to be a positive number higher than zero.");	
		} 
		this.arity = arity;
	}
	
	public NAryTree(TreeNode<T> root, int arity) {
		if (arity<0) {
			throw new IllegalArgumentException("Arity needs to be a positive number higher than zero.");	
		} 
		this.arity = arity;
		this.root = root;
	}

	public TreeNode<T> getRoot() {
		return root;
	}
	
	public int getArity() {
		return arity;
	}
	
	public TreeNode<T> setRoot (T info) {
		root = new TreeNode<T>(info);
		return root;
	}
	
	public TreeNode <T> setNewRoot(T info) {				
		TreeNode<T> newRoot = new TreeNode<T>(info);
		newRoot.getChildren().add(root);
		root.setParent(newRoot);
		root = newRoot;
		return root;
	}
	
	public TreeNode<T> findNode(T targetInfo){	
		return find(root,targetInfo);
	}
	
	private TreeNode<T> find(TreeNode<T> root, T info) {
	    if (root == null || root.getInfo().equals(info)) {
	        return root;
	    }
	    
	    for (TreeNode<T> child : root.getChildren()) {
	        TreeNode<T> result = find(child, info);
	        return result;
	    }
	    return null;
	}
	
	public TreeNode<T> add(TreeNode<T> parent, T info){
		if (parent.getChildren().size()>=arity) {
			throw new IllegalStateException("Maximum number of children reached.");		
		}
		TreeNode<T> toAdd = new TreeNode<T>(info);
		parent.getChildren().add(toAdd);
		toAdd.setParent(parent);
		return toAdd;
	}
	
	public TreeNode<T> addAt(TreeNode<T> parent,T info, int position){
		if (parent.getChildren().size()>=arity) {
			throw new IllegalStateException("Maximum number of children reached.");			
		}
		TreeNode<T> toAdd = new TreeNode<T>(info);
		parent.getChildren().add(position-1, toAdd);
		toAdd.setParent(parent);
		return toAdd;
	}
	
	public boolean remove(T target){
		TreeNode<T> toRemove = this.findNode(target);
		if (toRemove==null) {
			return false;
		}
		return toRemove.getParent().getChildren().remove(toRemove);
	}
	
	public TreeNode<T> removeAt(TreeNode<T> parent, int position){
		if (position>arity) {
			throw new IllegalArgumentException("Position not valid for the Tree Arity");
		}
		return parent.getChildren().remove(position-1);	
	}
	
	public void removeChildren(TreeNode<T> father) {
		father.getChildren().clear();
	}
	public boolean isEmpty() {
		return (root==null);
	}
	
	public List<TreeNode<T>> preOrderTraversal(){	
		List<TreeNode<T>> preOrder = new LinkedList<>();
		if (root == null) {
			return preOrder;
		}
		Stack<TreeNode<T>> stack = new Stack<>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode<T> currentNode = stack.pop();
			preOrder.add(currentNode);
			for (int i = currentNode.getChildren().size()-1; i >= 0; i--) {
				stack.push(currentNode.getChildren().get(i));
			}			
		}
		return preOrder;
	}
	
	public List<TreeNode<T>> postOrderTraversal() {
	    List<TreeNode<T>> postOrder = new LinkedList<>();
	    if (root == null) {
	        return postOrder;
	    }

	    Stack<TreeNode<T>> stack = new Stack<>();
	    stack.push(root);

	    while (!stack.isEmpty()) {
	        TreeNode<T> currentNode = stack.pop();
	        postOrder.add(0, currentNode); 
	        for (TreeNode<T> child : currentNode.getChildren()) {
	            stack.push(child);
	        }
	    }
	    return postOrder;
	}
	
	public List<TreeNode<T>> levelOrderTraversal () {	
		List<TreeNode<T>> levelOrder = new LinkedList<>();
		if (root == null) {
			return levelOrder;
		}
				
		Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode<T> currentNode = queue.remove();
			levelOrder.add(currentNode);
			for (TreeNode<T> node : currentNode.getChildren()) {
				if( node != null) {
					queue.add(node);
				}
			}
		}
		return levelOrder;
	}
	
	public int getHeight() {
	    return getHeightHelper(root)-1;
	}

	private int getHeightHelper(TreeNode<T> node) {
	    if (node == null) {
	        return 0; 
	    }
	    int maxHeight = 0;
	    for (TreeNode<T> child : node.getChildren()) {
	        int childHeight = getHeightHelper(child);
	        maxHeight = Math.max(maxHeight, childHeight);
	    }
	    return maxHeight+1;
	}
	
	public void print() {
	    if (root != null) {
	        printHelper(root, 0);
	    }
	}

	private void printHelper(TreeNode<T> node, int depth) {
	    if (node == null) {
	        return;
	    }
	    for (int i = 0; i < depth; i++) {
	        System.out.print("  "); 
	    }
	    System.out.println(node.getInfo());
	    for (TreeNode<T> child : node.getChildren()) {
	        printHelper(child, depth + 1);
	    }
	}
}
