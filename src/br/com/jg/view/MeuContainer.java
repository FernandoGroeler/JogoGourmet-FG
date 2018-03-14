package br.com.jg.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeuContainer<T> implements Iterator<T> {
	List<T> lista = new ArrayList<>();
	private Iterator<T> iterator;
	
	int itemCorrente = 0;
	
	public void add(T t) {
		lista.add(t);
	}
	
	public void resetIterator( ) {
		this.iterator = null;
	}

	@Override
	public boolean hasNext() {
		if (this.iterator == null) {
			this.iterator = lista.iterator();
		}
		
		return this.iterator.hasNext();
	}

	@Override
	public T next() {
		return this.iterator.next();
	}
}
