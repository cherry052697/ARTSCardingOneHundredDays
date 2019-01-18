package com.cherry.algorithm;


public class BinaryHeap<T extends Comparable<? super T>> {

	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;
	private T[] array;

	public BinaryHeap() {
		super();
	}

	public BinaryHeap(int currentSize) {
		super();
		this.currentSize = currentSize;
	}

	public BinaryHeap(T[] items) {
		currentSize = items.length;
		array= (T[]) new Comparable[(currentSize+2)*11/10];
		int i = 1;
		for (T item:items)
			array[i++] = item;
		buildHeap();
	}

	public void insert(T x) {
		if (currentSize == array.length - 1)
			enlargeArray(array.length * 2 - 1);
		int hole = ++currentSize;
		for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
	}

	public T deleteMin() throws Exception {
		if (isEmpty())
			throw new Exception();
		T minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);
		return minItem;
	}

	private T findMin() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	private void percolateDown(int hole) {
		int child ;
		T tmp = array[hole];
		for(;hole*2<=currentSize;hole=child){
			child = hole*2;
			if (child!=currentSize && array[child+1].compareTo(array[child])<0) 
				child++;
			if (array[child].compareTo(tmp)<0) 
				array[hole]=array[child];
			else
				break;
		}
		array[hole] = tmp;
	}

	private void buildHeap() {
		for (int i = currentSize/2; i > 0; i--) 
			percolateDown(i);
	}

	private void enlargeArray(int newSize) {

	}
}
