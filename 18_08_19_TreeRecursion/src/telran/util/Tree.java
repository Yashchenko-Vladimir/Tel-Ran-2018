package telran.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

//import telran.util.tests.E;
//import telran.util.tests.NodeTree;

@SuppressWarnings("unchecked")
public class Tree<E> implements Set<E> {
	
	Comparator<E> comp;
	NodeTree<E> root;
	int size;
	private int spacePerLevel = 5;
	
	
	public void setSpacePerLevel(int spacePerLevel) {
		this.spacePerLevel = spacePerLevel;
	}

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
	public boolean add2(E e) {
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
		int a = this.height();
		if ( a > (Math.log(size) /Math.log(2)) * 1.2) {
			this.balance();
		}
		size++;
		return true;
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
		// ������� �������   ����������� � ��������
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
		// �������� remove � ���������.  ���� ��� current � previous
		// ������� �������  �� ����������� � ��������
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
	
	public int getSizeRecursion() {
		return getSizeRecursion(root);
		
	}
	
	private int getSizeRecursion(NodeTree<E> root) {
		int res = 0;
		if (root != null) {
			res = getSizeRecursion(root.left) + getSizeRecursion(root.right) + 1;
		}
		return res;
	}
	
	public void printRotated() {
		printRotated(root, 0);
		
	}

	private void printRotated(NodeTree<E> root, int level) {
		if(root != null) {
			printRotated(root.right, level + 1);
			printRoot(root, level);
			printRotated(root.left, level + 1);
		}
	}

	private void printRoot(NodeTree<E> root, int level) {
		printShift(level);
		System.out.println(root.obj);
		
	}

	private void printShift(int level) {
		for (int i = 0; i < level*spacePerLevel ; i++) {
			System.out.print(" ");
		}
	}
	
	public int width() {
		return widthRecursen(root);
	}

	private int widthRecursen(NodeTree<E> root) {
		
		if(root.left == null && root.right == null)
			return 1;
		else if(root.left ==null)
			return widthRecursen(root.right);
		else if(root.right == null)
			 return widthRecursen(root.left);
		return widthRecursen(root.left) + widthRecursen(root.right);
	}

	public int height() {
		
		return heigthRecursen(root);
	}

	private int heigthRecursen(NodeTree<E> root) {
		if(root.left == null && root.right == null)
			return 1;
		else if(root.left !=null)
			return 1 + widthRecursen(root.left);
		else if(root.right != null)
			return 1 + widthRecursen(root.right);
		return widthRecursen(root.left) > widthRecursen(root.right)? widthRecursen(root.left):
			widthRecursen(root.right);
	}
	
	public int balance() {
		ArrayList arr = new ArrayList<>();
		
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			arr.add(iter.next());
		}
//		System.out.println(arr.toString());
		int left = 0;
		int right= arr.size()- 1;
		this.root = null;
		size = 0;
		
		recreatedTree(left, right, arr);
//		printRotated();
		
		
		return arr.size();
	}

	private void recreatedTree(int left, int right, ArrayList arr) {
		
		if(left <= right) {
			int mid = (left + right) / 2;
			this.add((E) arr.get(mid));
			recreatedTree(mid + 1, right, arr);
			recreatedTree(left, mid - 1, arr);
			
					
		} 
		
	}
	

}
