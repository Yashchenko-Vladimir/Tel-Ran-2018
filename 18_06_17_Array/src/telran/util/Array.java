package telran.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
@SuppressWarnings("unchecked")
public class Array<E> implements List<E>{
private static final int INITIAL_CAPACITY = 16;
private static final int FACTOR = 2;
 Object ar[]=new Object[INITIAL_CAPACITY];
 int size;
 
	public boolean add(E obj) {
		if(size==ar.length)
			
			allocateArray();
		ar[size++]=obj;
		return true;
		
	}

	private void allocateArray() {
		ar=Arrays.copyOf(ar, ar.length*FACTOR);
		
	}

	public int size() {
		return size;
	}

	
	public E get(int i) {
		E res=null;
		if(i>=0&&i<size)
			res=(E)ar[i];
		return res;
	}
	protected  E removeAt(int index) {
		E res=(E) ar[index];
		for(int i=index+1;i<size;i++) {
			ar[i-1]=ar[i];
		}
		size--;
		ar[size]=null;
		return res;
	}
	/**
	 * removes number at a given index
	 * @param index
	 * @return true if index is correct
	 */
	public E remove(int index) {
		E res=null;
		if(index>=0&&index<size) {
			res=removeAt(index);
			
		}
		return res;
	}
	private boolean add(E obj, int index) {
		boolean res=false;
		if (index>=0 && index<=size) {
			for (int i = size; i > index; i--)
				ar[i] = ar[i - 1];
			ar[index] = obj;
			size++;
			res=true;
		}
		return res;
		
	}
	public void shuffle() {
		Object tmp[]=new Object[ar.length];
		int[]indexes=getRandomUniqueIndexes();
		for(int i=0;i<size;i++) {
			tmp[i]=ar[indexes[i]];
		}
		ar=tmp;
	}
	private int[] getRandomUniqueIndexes() {
		boolean history[]=new boolean[size];
		int[]res=new int[size];
		int ind=0;
		int size=this.size;
		while(size>0) {
			int number=(int) (Math.random()*this.size);
			if(history[number])
				continue;
			res[ind++]=number;
			history[number]=true;
			size--;
			
		}
		return res;
	}
	public int indexOf(Object pattern) {
		for(int i=0;i<size;i++) {
			if(ar[i].equals(pattern))
				return i;
		}
		return -1;
	}
	public int indexOf(Predicate<E> predicate) {
		for(int i=0;i<size;i++) {
			if(predicate.test((E)ar[i]))
				return i;
		}
		return -1;
	}
	
	public boolean remove(Object pattern) {
		// remove first occurrence of an object
		//equaled a given pattern
		int ind=indexOf(pattern);
		if (ind<0)
			return false;
		removeAt(ind);
		return true;
		
	}
	public boolean removeIf(Predicate<? super E> predicate) {
//		boolean res=false;
//		int tmpInd=0;
//		Object[]tmp=new Object[ar.length];
//		for(int i=0;i<size;i++) {
//			if(!predicate.test((E) ar[i])) {
//				tmp[tmpInd++]=ar[i];
//			}
//			else {
//				res=true;
//			}
//		}
//		size=tmpInd;
//		ar=tmp;
//		return res;
		
		  boolean res = false;
		    Iterator<E> it = this.iterator();
		    while (it.hasNext()) {
		      E obj = it.next();
		      if (predicate.test(obj)) {
		        res = true;        
		        it.remove();      
		      }
		    }
		    return res;
	}
	public int indexOfLast(Object pattern) {
		for(int i=size-1;i>=0;i--)
			if(ar[i].equals(pattern))
				return i;
		return -1;
	}
	public void sort() {
		sort(new ComparatorComparable<E>());
	}

	private void swap(int i, int j) {
		E tmp=(E) ar[i];
		ar[i]=ar[j];
		ar[j]=tmp;
		
	}
public void sort(Comparator<? super E> comp) {
	int n=size;
	boolean flSort=true;
	do {
		flSort=true;
		n--;
		for(int i=0;i<n;i++) {
			if(comp.compare((E)ar[i], (E)ar[i+1])>0) {
				swap(i,i+1);
				flSort=false;
			}
		}
		
	}while(!flSort);
}

@Override
public boolean isEmpty() {
	return false;
}

@Override
public boolean contains(Object o) {
	return false;
}

@Override
public Iterator<E> iterator() {
	return new ArrayIterator<E>(this);
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
public boolean containsAll(Collection<?> c) {
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
public E set(int index, E element) {
	return null;
}

@Override
public void add(int index, E element) {
	add(element,index);
	
}

@Override
public int lastIndexOf(Object o) {
	return indexOfLast(o);
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

}

