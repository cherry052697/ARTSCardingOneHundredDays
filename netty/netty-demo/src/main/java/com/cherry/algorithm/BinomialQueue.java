package com.cherry.algorithm;

public class BinomialQueue<T extends Comparable<? super T>> {

	public BinomialQueue() {

	}

	public BinomialQueue(T item) {

	}
	public void merge(BinomialQueue<T> rhs){
		if(this == rhs)
			return;
		currentSize += this.currentSize;
		if(currentSize > capacity()){
			int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
			expandTheTress(maxLength+1);
		}
		Node<T> carry = null;
		for (int i = 0,j=1; j <= currentSize; i++,j*=2) {
			Node<T> t1 = theTrees[i];
			Node<T> t2 = i<rhs.theTrees.length?rhs.theTrees[i]:null;
			int whichCase = t1==null?0:1;
			whichCase += t2==null?0:2;
			whichCase += carry==null?0:4;
			switch (whichCase) {
			case 0:
			case 1:
				break;
			case 2:
				theTrees[i] = t2;
				rhs.theTrees[i] = null;
				break;

			case 4:
				theTrees[i] = carry;
				carry = null;
				break;
			
			case 3:
				carry = combineTrees(t1, t2);
				theTrees[i] = rhs.theTrees[i]=null;
				break;
				
			case 5:
				carry = combineTrees(t1, carry);
				theTrees[i] = null;
				break;
			case 6:
				carry = combineTrees(t2, carry);
				rhs.theTrees[i]=null;
				break;
			case 7:
				theTrees[i] = carry;
				carry = combineTrees(t1, t2);
				rhs.theTrees[i] = null;
				break;
			}
		}
		for(int k=0;k<rhs.theTrees.length;k++)
			rhs.theTrees[k] = null;
		rhs.currentSize =0;
	}
	public void insert(T x){
		merge(new BinomialQueue<T>(x));
	}

	public T findMin(){
		return null;
	}
	public T deleteMin() throws Exception{
		if(isEmpty())
			throw new Exception();
		int minIndex = findMinIndex();
		T minItem = theTrees[minIndex].element;
		Node<T> deletedTree = theTrees[minIndex].leftChild;
		BinomialQueue<T> deleteQueue = new BinomialQueue<T>();
		deleteQueue.expandTheTress(minIndex+1);
		deleteQueue.currentSize = (1<<minIndex)-1;
		for (int j = minIndex-1; j >=0; j--) {
			deleteQueue.theTrees[j]=deletedTree;
			deletedTree = deletedTree.nextSibling;
			deleteQueue.theTrees[j].nextSibling=null;
		}
		theTrees[minIndex]=null;
		currentSize -= deleteQueue.currentSize+1;
		merge(deleteQueue);
		return minItem;
	}
	
	public boolean isEmpty(){
		return currentSize == 0;
	}
	
	public void makeEmpty(){
		
	}
	
	private static final int DEFAULT_TREES = 1;
	private int currentSize;
	private Node<T>[] theTrees;
	private void expandTheTress(int newNumTrees){
		
	}
	private Node<T> combineTrees(Node<T> t1,Node<T> t2){
		if(t1.element.compareTo(t2.element)>0)
			return combineTrees(t2, t1);
		t2.nextSibling = t1.leftChild;
		t1.leftChild = t2;
		return t1;
	}
	
	private int capacity(){
		return (1<<theTrees.length)-1;
	}
	
	private int findMinIndex(){
		return 0;
	}
	
	private static class Node<T> {
		T element;
		Node<T> leftChild;
		Node<T> nextSibling;
		int npl;

		Node(T theElement) {
			this(theElement, null, null);
		}

		Node(T theElement, Node<T> lt, Node<T> rt) {
			element = theElement;
			leftChild = lt;
			nextSibling = rt;
		}
	}
}
