package telran.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HashTable<E> implements Set<E> {
	private static final int INTIAL_LENGTH = 16;
	private static final int ADD_LENGTH = 2;
	List<E>[] hashTable;
	float factor = 0.75f; // size/ hashTable.length
	int length;
	int size; // number of elements
	
	
	
	public HashTable() {
	//	hashTable = new List[INITIAL_LENGTH];
		this(INTIAL_LENGTH);
	}
	public HashTable (int length) {
		hashTable = new List[length];
		this.length = hashTable.length;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		int index = getIndex(o);
		if (hashTable[index] == null)
			return false;
		
		return hashTable[index].contains(o);
	}

	private int getIndex(Object o) {
		int hashCode = o.hashCode();
		int res = Math.abs(hashCode)%length;
		return res;
	}

	@Override
	public Iterator<E> iterator() {
		return new HashTableIterator<E>(this);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		if (contains(e))
			return false;
		if ((float) size / hashTable.length > factor) 
			tableRecreation();
		int ind = getIndex(e);
		if (hashTable[ind] == null)
			hashTable[ind] = new LinkedList<>();
		size++;
		return hashTable[ind].add(e);
	}

	private void tableRecreation() {
		List<E>[] newHashTable = new List [hashTable.length * ADD_LENGTH];
		length = newHashTable.length;
		for(E e : this) {
			int ind = getIndex(e);
			if (newHashTable[ind] == null)
				newHashTable[ind] = new LinkedList<>();
			newHashTable[ind].add(e);
		}
		hashTable = newHashTable;
	}
		
		
	
	@Override
	public boolean remove(Object o) {
		
		int ind = getIndex(o);
		boolean res = hashTable[ind].remove(o);
		size--;
		if(hashTable[ind].isEmpty())
			hashTable[ind] = null;
		return res;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean res = true;
		for(Object it : c) {
			if(contains(it))
				res=false;
		}
		
		return res;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if(c.containsAll(this))
			return false;
		
		removeIf(x->!c.contains(x));
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		if(!c.containsAll(this))
			return false;
		
		removeIf(x -> c.contains(x));
		return true;
	}

	@Override
	public void clear() {
		removeIf(E -> true);
		
	}
	
//	public static void main(String[] args) {
//		HashTable<Integer>  HT = new HashTable<>();
//		HT.add(15);
//		HT.add(47);
//		HT.add(88);
//		for(Integer aa: HT) {
//			System.out.println(aa);
//		}
//	}
//	

}
