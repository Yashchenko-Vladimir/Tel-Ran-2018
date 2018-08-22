package telran.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class LinkedList<E> implements List<E> {
public  NodeList<E> head;
 NodeList<E> tail;
 int size;
 
  public boolean hasLoop() {
	  boolean res = false;
	  HashMap<NodeList<E>, Boolean>  has = new HashMap<>(); 
	  NodeList<E> current = head;
	  while (true) {
		  if (current == null)
			  break;
		  if (!has.containsKey(current)) {
			  has.put(current, true);
		  } else {
			  res = true;
			  break;
		  }
		  current = current.next;
	  }
	  return res;
  }
 
  public void getNode() {
	   head.next.next.next.next.next.next = head.next.next;
	  }
 
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		
		return size==0;
	}

	@Override
	public boolean contains(Object o) {
		
		return indexOf(o)>=0;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(E e) {
		NodeList<E> newNode=new NodeList<>(e);
		if(head==null) {
			head=tail=newNode;
		}
		else {
			tail.next=newNode;
			tail=newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int ind=indexOf(o);
		if (ind<0)
			return false;
		if (ind==0)
			return removeFirst()!=null;
		if(ind==size-1)
			return removeLast()!=null;
		return removeAtIndex(ind)!=null;
			
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		//TODO 
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public E get(int index) {
		if(index<0 || index>=size)
			return null;
		if(index==0)
			return head.object;
		if(index==size-1)
			return tail.object;
		return getPreviousNode(index+1).object;
		
	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	@Override
	public void add(int index, E element) {
		if(index<0 || index>size)
			return;
		if(index==size)
			add(element);
		else {
			if(index==0) {
				addHead(element);
			}
			else {
				NodeList<E> newElement=
						new NodeList<>(element);
				NodeList<E> previous=getPreviousNode(index);
				newElement.next=previous.next;
				previous.next=newElement;
				size++;
				
			}
		
		}

	}

	private void addHead(E element) {
		NodeList<E> newNode=new NodeList<>(element);
		newNode.next=head;
		head=newNode;
		size++;
		
	}

	private NodeList<E> getPreviousNode(int index) {
		NodeList<E>current=head;
		int lim=index-1;
		for(int i=0;i<lim;i++,current=current.next) {}
			
		return current;
	}

	@Override
	public E remove(int index) {
		if(index<0||index>=size)
			return null;
		if(index==0)
			return removeFirst();
		if (index==size-1)
			return removeLast();
		return removeAtIndex(index);
	}

	private E removeAtIndex(int index) {
		NodeList<E>previous=getPreviousNode(index);
		E res=previous.next.object;
		previous.next.object=null;
		previous.next=previous.next.next;
		size--;
		return res;
	}

	public E removeLast() {
		if(head==null)
			return null;
		E res=tail.object;
		if(head==tail) {
			head=tail=null;
			
		}
		else {
			tail=getPreviousNode(size-1);
			tail.next=null;
		}
		size--;
		
		return res;
	}

	public E removeFirst() {
		if(head==null)
			return null;
		E res=head.object;
		head=head.next;
		if(head==null)
			tail=null;
		size--;
		return res;
	}

	@Override
	public int indexOf(Object o) {
		if(o==null)
			return indexOfNull();
		return indexOfNonNull(o);
	}

	private int indexOfNonNull(Object o) {
		int index=0;
		NodeList<E> current=null;
		for(current=head;current!=null&&
				!o.equals(current.object);
				current=current.next,index++) {}
		return current==null?-1:index;
	}

	private int indexOfNull() {
		int index=0;
		NodeList<E> current=null;
		for( current=head;
				current!=null && current.object!=null;
			current=current.next,index++) {}
		return current==null?-1:index;
	}

	@Override
	public int lastIndexOf(Object o) {
		return o==null?lastIndexNull():lastIndexNonNull(o);
	}
	private int lastIndexNonNull(Object o) {
		int res=-1;
		int index=0;
		for(NodeList<E>current=head;current!=null;
				current=current.next,index++) {
			if(o.equals(current.object))
				res=index;
				
		}
		return res;
	}
	private int lastIndexNull() {
		int res=-1;
		int index=0;
		for(NodeList<E>current=head;current!=null;
				current=current.next,index++) {
			if(current.object==null)
				res=index;
				
		}
		return res;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}
//	@Override
//	public boolean removeIf(Predicate<? super E> predicate) {
//		NodeList<E> current = null, previous = null;
//		boolean res = false;
//		for (current = head; current != null; current=current.next) {
//			if (!predicate.test(current.object)) {
//				previous = current;
//				continue;
//			} 
//			if(current == head) {
//				removeIfFirst(current); // head = current.next; 	size--;
//				res= true;
//				continue;
//			}
//			if (current == tail) {
//				removeIfLast(previous); // tail = previous;	previous.next = null; size--;
//				res= true;
//				continue;
//			}
//			removeIfMiddle(previous, current); // previous.next = current.next; size--;
//			res = true;
//		}	
//		return res;
////		
////		Так писать не нужно!!!!!!
////		Получается не совместисомть методов, итератор не знает о том что он
////		удаляет и идет дальше, при этом нарушается индексация 
//		
////		for(E obj: this) {
////			if(predicate.test(obj))
////				remove(obj);
////		res = true;
////		}
////		return res;
//		
////		Должны написать метод remove()  в  классе Iterator
//		
////		Метод по умолчанию, сдандартная реализация
////		Iterator<E> it = this.iterator();
////		while(it.hasNext()) {
////			E obj = it.next();
////			if(predicate.test(obj)) {
////				res=true;
////				it.remove();
////			}
////		}
////		return res;
//	}
//	
//	private void removeIfMiddle(NodeList<E> previous, NodeList<E> current) {
//		previous.next = current.next;
//		size--;
//	}
//	private void removeIfLast(NodeList<E> previous) {
//		tail = previous;
//		previous.next = null;
//		size--;
//	}
//	private void removeIfFirst(NodeList <E> current) {
//		head = current.next;
//		size--;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator<? super E> comp) {
		E [] obj = (E[]) new  Object [size];
		NodeList<E> current = head;
		for(int i=0;i<size;i++,current=current.next) {
			obj[i] = current.object;
		}
		Arrays.sort(obj, comp);
		head =null;
		size = 0;
	   	
		for(int i=0;i<obj.length;i++) {
			add((E)obj[i]);
		}
	}

}
