package telran.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Tree<E> implements Set<E> {
	
	Comparator<E> comp;
	NodeTree<E> root;
	int size;
	
	
	public Tree() {
		comp = (Comparator <E>) Comparator.naturalOrder();
	}
	
	public Tree(Comparator<E> comp) {
		this.comp = comp;
	}

	@Override
	public boolean add(E e) {
		if (contains(e)) {
			return false;
		}
		NodeTree<E> parent = null;
		NodeTree<E> newNode = new NodeTree<>(e);
		if(root == null) {
			root = newNode;
		}
		else {
			parent = getParent(e);
			if(comp.compare(e, parent.obj)<0) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
		}
		newNode.parent = parent;
		size++;
		return true;
	}

	private NodeTree<E> getParent(E e) {
		NodeTree<E> parent = root;
		NodeTree<E> current = root;
		while(current != null) {
			parent = current;
			current = comp.compare(e, current.obj)<0?current.left:current.right;
		}
		
		return parent;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean res=false;
		for(E e:c) {
			res=add(e);
		}
		return res;
	}

	@Override
	public void clear() {
		removeIf(x->true);
	}

	@Override
	public boolean contains(Object o) {
		NodeTree<E> current = findNode(o);
		return current!=null;
	}

	private NodeTree<E> findNode(Object o) {
		NodeTree<E> current = root;
		while(current!=null && !o.equals(current.obj)) {
			current = comp.compare((E) o, current.obj)<0?current.left:current.right;
		}
		return current;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		
		return new TreeIterator<E>(this);
	}

	@Override
	public boolean remove(Object o) {
		NodeTree<E> node = findNode(o);
		boolean res = false;
		if (node != null) {
			res = true;
			removeNode(node);
		}
		return res;
	}

	 void removeNode(NodeTree<E> node) {
		if(isJunction(node))
			removeJunction(node);
		else
			removeSimpleNode(node);
	}

	 boolean isJunction(NodeTree<E> node) {
		return node.left != null && node.right!= null;
	}
	
	
	private void removeJunction(NodeTree<E> node) {
		NodeTree<E> substitute = getSubstitute(node);
		node.obj = substitute.obj;
		removeSimpleNode(substitute);
		
	}

	private NodeTree<E> getSubstitute(NodeTree<E> node) {
		NodeTree<E> res = null;
		for(res = node.right; res.left!=null; res = res.left) {}
//		while(node.left != null) {
//			node= node.left;
//			}
		return res;
	}

	private void removeSimpleNode(NodeTree<E> node) {
		NodeTree<E> parent = node.parent;
		NodeTree<E> child = node.left==null?node.right:node.left;
		if (parent != null) {
			if(parent.left == node)
		
			parent.left = child;
		else
			parent.right = child;
		if(child != null)
			child.parent = parent;
		} else
			removeSimpleRoot(child);
		size--;
	}

	private void removeSimpleRoot(NodeTree<E> child) {
		root = child;
		if(child != null)
			child.parent = null;
		
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// удалить которые   содержаться в колекции
		if(!containsAny(c))
			return false;
		removeIf(x->c.contains(x));
		return true;
	}

	private boolean containsAny(Collection<?> c) {
		for(Object obj:c) {
			if(contains(obj))
				return true;
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		
		if (c.containsAll(this))
			return false;
		removeIf(x->!c.contains(x));
		
		// TODO Auto-generated method stub
		// написать remove в итераторе.  есть два current и previous
		// удалить которые  не содержаться в колекции
		return true;
	}

	@Override
	public int size() {
		return  size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
