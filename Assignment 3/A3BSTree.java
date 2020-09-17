import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class A3BSTree <E> implements Tree<E>{
	
	private class Node{
		
		E data;
		Node parent;
		Node leftChild;
		Node rightChild;
		
		private Node(E data) {
			this.data = data;
			parent = null;
			leftChild = null;
			rightChild = null;
		}

	}

	private TreeSet<E> tree;
	Node root;

	public A3BSTree(){
		tree = new TreeSet<>();
	}
	
	//insertion code

	public void add(E e) {
		tree.add(e);	
		
		Node node = new Node(e);
		
		//tree is empty
		if(root==null)
			root = node;
		
		//tree not empty
		else add(root, node);
	}
	
	private void add(Node node, Node nodeAdd) {

		//if value is larger than parent, we add a right child
		if(((Comparable<E>)nodeAdd.data).compareTo(node.data)>0)
			if (node.rightChild==null)
			{
				nodeAdd.parent = node;
				node.rightChild = nodeAdd;
			}
			else add(node.rightChild,nodeAdd);

		//else we add a left child
		else 
			if(((Comparable<E>)nodeAdd.data).compareTo(node.data)<=0)
				if (node.leftChild==null)
				{
					nodeAdd.parent = node;
					node.leftChild = nodeAdd;
				}
				else add(node.leftChild,nodeAdd);
	}

	public void addAll(Collection<? extends E> c) {
		tree.addAll(c);
	}
	
	//deletion code

	public boolean remove(Object o) {
	
		tree.remove(o);
		
		//change object to a value of type E
		E value; Node nodeDelete = null;
		value = (E) o;
		nodeDelete = new Node(value);
		
		//search for node with the given value
		Node node = findNode(root,nodeDelete); 
		
		//value exists and node is returned
		if(node != null)
		{
			//node has no children
			if(numberChildren(node) == 0)
				removeNoChildren(node);

			//node has one child
			else if(numberChildren(node) == 1)
				removeOneChild(node);

			//node has two children
			else if(numberChildren(node) == 2)
				removeTwoChildren(node);
			
			return true;
		}
		
		//node == null
		return false;
		
	}
	
	private void removeNoChildren(Node node) {
		
		if(node == root)
			root = null;
		
		//node we want to delete is a left child
		else if(node == node.parent.leftChild)
			node.parent.leftChild = null;

		//node we want to delete is a right child
		else node.parent.rightChild = null;
	
	}
	
	private void removeOneChild(Node node) {
		
		//node we want to delete HAS a right child
		if(node.leftChild == null)
		{
			if (node == root)
			{
				root = node.rightChild;
				node.rightChild.parent = null;
				return;
			}
			
			//node we want to delete IS a left child
			else if(node == node.parent.leftChild) 
				node.parent.leftChild = node.rightChild;
			
			//node we want to delete IS a right child
			else
				node.parent.rightChild = node.rightChild;
			
			node.rightChild.parent = node.parent;
		}
		
		//node we want to delete HAS a left child
		else
		{
			if (node == root)
			{
				root = node.leftChild;
				node.leftChild.parent = null;
				return;
			}
			
			//node we want to delete IS a left child
			else if(node == node.parent.leftChild)
				node.parent.leftChild = node.leftChild;
			
			//node we want to delete IS a right child
			else
				node.parent.rightChild = node.leftChild;
			
			node.leftChild.parent = node.parent;	
		}
	}
	
	private void removeTwoChildren(Node node) {
		
		Node minNode = minLeftTraversal(node.rightChild);
		
		//minNode has no children
		if(minNode.rightChild == null)
			removeNoChildren(minNode);
		
		//we don't test the case for a left child because if it
		//minNode had a left child it would have been traversed
		
		//minNode has a rightChild
		else removeOneChild(minNode);
		
		if (node == root)
		{
			root = minNode;
		}
		
		//node we want to delete IS a left child
		else if(node == node.parent.leftChild)
		{
			node.parent.leftChild = minNode;
			minNode.parent = node.parent;
		}
		
		//node we want to delete IS a right child
		else
		{
			node.parent.rightChild = minNode;
			minNode.parent = node.parent;
		}
		
		minNode.leftChild = node.leftChild;
		minNode.rightChild = node.rightChild;
		
		node.leftChild.parent = minNode;
		node.rightChild.parent = minNode;
		
	}

	public Iterator<E> iterator() {
		return this.iterator();
	}

	public int height() {
		
		if (root == null)
			return -1;
		
		else return (height(root));
	}
	
	private int height(Node node) {

		if(node == null)
			return 0;

		else 
		{
			int leftHeight = height(node.leftChild);
			int rightHeight = height(node.rightChild);

			if(leftHeight > rightHeight)
				return(leftHeight + 1);
			else return (rightHeight + 1);
		}

	}

	public int size() {
		
		if(root == null)
			return 0;
		
		else return(size(root));
	}
	
	private int size(Node node) {
		
		if(node == null)
			return 0;
		
		else
		{
			int leftSize = size(node.leftChild);
			int rightSize = size(node.rightChild);
			
			return (leftSize + rightSize + 1);
		}
		
	}
	
	//helper code
	
	private Node findNode(Node search, Node find){

		if(search == null)
			return null;

		if(search.data.equals(find.data))
			return search;
		else
		{
			Node returnNode = findNode(search.leftChild,find);
			if(returnNode == null)
				returnNode = findNode(search.rightChild,find);
			return returnNode;
		}
	}

	private int numberChildren(Node node) {
		
		if(node.leftChild==null&&node.rightChild==null)
			return 0;
		
		else if((node.leftChild != null && node.rightChild == null) || 
				(node.rightChild != null && node.leftChild == null))
			return 1;
		
		else return 2;
		
	}
	
	private Node minLeftTraversal(Node node) {
		
		if(node.leftChild == null)
			return node;
		
		return minLeftTraversal(node.leftChild);
	}

}
