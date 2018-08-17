package telran.util;

import java.util.Iterator;

import java.util.List;

public class HashTableIterator<E> implements Iterator<E>{
	
	HashTable<E> hashTable;
	List<E> current;
	Iterator<E> iteratorList; 
	int index = 0, indexPr = 0;
	E previous, result;
		
	public HashTableIterator(HashTable<E> hashTable) {
		this.hashTable =  hashTable;
		findNoNull();
	}
	
	private void findNoNull() {
		while ( index < hashTable.hashTable.length) {
		current = hashTable.hashTable[index];
		if (current != null)
			break;
		else 
			index++;
		}
	if (index >= hashTable.hashTable.length ) 
		iteratorList = null;
	else 
		iteratorList = current.iterator();	
}

	@Override
	public boolean hasNext() {
		return index < hashTable.length; // or current != null
	}

	@Override
	public E next() {
		
		result = iteratorList.next();
		if(indexPr != index)
			indexPr = index;
		if(!iteratorList.hasNext()) {
			index++;
			findNoNull();
		}
		return result;
	}
	
	@Override
	public void remove() {
	//	rem.remove(result);
		hashTable.hashTable[indexPr].remove(result);
		hashTable.size--;
		if (hashTable.hashTable[indexPr].isEmpty())
			hashTable.hashTable[indexPr] = null;
		else 
			iteratorList = current.iterator();
		}

}
