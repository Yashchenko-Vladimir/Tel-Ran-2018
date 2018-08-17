package telran.util;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayIterator<E> implements Iterator<E> {
private int current=0;
private Object[]ar;
//private int size;
private Array<E> array;

	public ArrayIterator(/*Object[] */ Array<E> ar) {
		this.ar= ar.ar;
	//	this.size = ar.size;
		array = ar;
	}

	@Override
	public boolean hasNext() {
		return current < array.size();
	}
	
	@Override
	public E next() {
		return (E)ar[current++];
	}
	
	@Override
	public void remove() {
		if (array.size == 1) {
			ar = null;
			array.size = 0;
			return;
		} 
//		if (current == size ){
//			ar[current] = null;
//			size--;
//			return;
//		}
		int b = 0;
		for( int i = current; i <= array.size; i++) {
			ar [i-1] = ar[i];
			b = i;
			}
		ar[b] = null;
		array.size--;
		current --;
	}
}

