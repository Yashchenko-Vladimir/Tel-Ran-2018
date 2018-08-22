package telran.util;

import java.util.Iterator;

public class TreeIterator<E> implements Iterator<E> {
	NodeTree<E> current;
	NodeTree<E> previous;
	Tree<E> tree;
	
	public TreeIterator(Tree<E> tree) {
		this.tree = tree;
		current = getLeastNode(tree.root);
	}
	private NodeTree<E> getLeastNode(NodeTree<E> node) {
		
		while(node.left != null) {
			node= node.left;
			}
		return node;
	}
	
	private NodeTree<E> getFirstRightPar (NodeTree<E> node){
		NodeTree<E> nodePar = node.parent;
		while (nodePar != null && nodePar.right == node) {
			node = nodePar;
			nodePar = nodePar.parent;
			}
		
		return nodePar;
	}
	
// написать метод next
// current следующий элемент влево null в нашеи дереве 20
//сделать без вызова компаратора
	
	@Override
	public boolean hasNext() {
		
		return current !=null;
	}

	@Override
	public E next() {
		
		NodeTree<E> NodeResult = current;
		if(current.right != null) {
			current = getLeastNode(current.right);
		} else /* (current.right == null)*/ {
			
			current =  getFirstRightPar(current);
		}
				
		return NodeResult.obj;
	}
	
	@Override
	public void remove() {
		if(tree.isJunction(previous))
			current=previous;
		tree.removeNode(previous);
		
	}
}
