package telran.util;

public class NodeList<E> {
	public E object;
	public NodeList<E> next;
	public NodeList(E object) {
		super();
		this.object = object;
	}
}
