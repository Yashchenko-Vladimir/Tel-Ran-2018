package telran.util;

import java.util.Iterator;

public class LinkedListIterator<E> implements Iterator<E> {
	
	NodeList<E> currentNode;
	NodeList<E> previous;
	NodeList<E> current;
	LinkedList<E> list;
	
	public LinkedListIterator(LinkedList<E> list) { 
		this.current=list.head;
		this.list = list;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public E next() {
		previous = currentNode;
		currentNode =current;
		current = current.next;
		return currentNode.object;
	}
	
	@Override
	public void remove() {
		if ((currentNode == list.head) && (currentNode == list.tail)) {
			list.head=list.tail=null;
			list.size = 0;
			return;
			}
		if (currentNode == list.head) {
			list.head = current;
			list.size --;
			currentNode = previous;
			return;
			}
		if (currentNode == list.tail) {
			list.tail= previous;
			previous.next = null;
			list.size --;
			currentNode = previous;
			return;
			}
		previous.next = current;
		list.size --;
		currentNode = previous;
	}
}
