package com.cherry.algorithm;


public class LeftistHeap<T extends Comparable<? super T>> {

	private Node<T> root;
	private static class Node<T>{
		T element;
		Node<T> left;
		Node<T> right;
		int npl;
		 Node(T theElement) {
			 this(theElement,null,null);
		}

		Node(T theElement, Node<T> lt, Node<T> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}
	}
	public LeftistHeap(){
		root = null;
	}
	
	public void merge(LeftistHeap<T> rhs){
		if(this == rhs)
			return;
		root = merge(root, rhs.root);
		rhs.root = null;
	}
	public void insert(T x){
		root = merge(new Node<T>(x), root);
	}
	public T findMin(){
		return null;
	}
	public T deleteMin() throws Exception{
		if(isEmpty())
			throw new Exception();
		T minItem = root.element;
		root = merge(root.left, root.right);
		return minItem;
	}
	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	private Node<T> merge(Node<T> h1,Node<T> h2){
		if (h1 == null) 
			return h2;
		if(h2 == null)
			return h1;
		if(h1.element.compareTo(h2.element)<0)
			return merge1(h1, h2);
		else
			return merge1(h2, h1);
			
	}
	private Node<T> merge1(Node<T> h1,Node<T> h2){
		if(h1.left == null)
			h1.left = h2;
		else{
			h1.right = merge(h1.right, h2);
			if(h1.left.npl < h1.right.npl)
				swapChildren(h1);
			h1.npl = h1.right.npl+1;
		}
		return h1;
	}
	private Node<T> swapChildren(Node<T> t){
		return null;
	}
}
