package n_ary_tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
	private T info;
	private List<TreeNode<T>> children;
	private TreeNode<T> parent;

	public TreeNode(T info) {
		children = new ArrayList<TreeNode<T>>();
		this.info = info;	
	}
	
	public TreeNode(T info, TreeNode<T> parent) {
		children = new ArrayList<TreeNode<T>>();
		this.info = info;	
		this.parent=parent;
	}
	
	public T getInfo() {
		return info;
	}
	
	public TreeNode<T> getParent() {
		return parent;
	}
	
	public List<TreeNode<T>> getChildren() {
		return children;
	}
	
	public int getLevel() { 
		int level = 0;
		TreeNode<T> temp = parent;
		while (temp != null) {
			level++;
			temp = temp.getParent();
		}
		return level;	
	}
	
	public int getChildCount() {
		return children.size();
	}
	
	public void setParent(TreeNode<T> parent) {
		if (parent == null) {
			throw new IllegalArgumentException("Father can't be Null");	
		} 
		this.parent = parent;
	}
	
	public void setInfo(T newInfo) {
		this.info = newInfo;
	}	

	public List<T> getChildrenInfo() {
		List<T> infoList = new ArrayList<>();
		for (TreeNode<T> node : getChildren()) {
			if (node != null) {
				infoList.add(node.getInfo());
			}
		}
		return infoList;	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj==null) return false;
		if(this.getClass() != obj.getClass()) return false;
		TreeNode<T> other = (TreeNode<T>) obj;	
		return this.getInfo().equals(other);
	}
}
