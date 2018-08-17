package telran.util;

import java.util.Comparator;

public class ComparatorComparable<T> implements Comparator<T> {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(T o1, T o2) {
		return ((Comparable<T>)o1).compareTo(o2);
	}

}
