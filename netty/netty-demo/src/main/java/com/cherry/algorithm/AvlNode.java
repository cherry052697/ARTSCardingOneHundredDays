package com.cherry.algorithm;

public class AvlNode<T> {
	T element;
	AvlNode<T> left;
	AvlNode<T> right;
	int height;
	public AvlNode(T element) {
		this(element, null, null);
	}
	public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
		super();
		this.element = element;
		this.left = left;
		this.right = right;
	}
	private int height(AvlNode<T> t){
		return t==null?-1:t.height;
	}
	
	@SuppressWarnings("unused")
	private  AvlNode<T> insert(T x, AvlNode<T> t){
		if(t == null){
			return new AvlNode<T>(x, null, null);
		}
		int compareResult = compare(x,t.element);
		if(compareResult < 0){
			t.left = insert(x, t.left);
			if((height(t.left)-height(t.right))==2){
				if(compare(x, t.left.element) < 0 ){
					t =  routateWithLeftChild(t);
				}else{
					t = doubleWithLeftChild(t);
				}
			}
		}else if(compareResult > 0){
			t.right = insert(x, t.right);
			if((height(t.right)-height(t.left))==2){
				if(compare(x, t.right.element) > 0){
					t =  routateWithRightChild(t);
				}else{
					t = doubleWithRightChild(t);
				}
			}
		}else{
			t.height = Math.max(height(t.left), height(t.right))+1;
		}
		return t;
	}
	private AvlNode<T> doubleWithRightChild(AvlNode<T> t) {
		// TODO Auto-generated method stub
		return null;
	}
	private AvlNode<T> routateWithRightChild(AvlNode<T> t) {
		// TODO Auto-generated method stub
		return null;
	}
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> t) {
		t.left = routateWithRightChild(t.left);
		return routateWithLeftChild(t);
	}
	private AvlNode<T> routateWithLeftChild(AvlNode<T> t) {
		AvlNode<T> avl = t.left;
		t.left = avl.right;
		avl.right = t;
		t.height= Math.max(height(t.left), height(t.right))+1;
		avl.height = Math.max(height(avl.left), t.height)+1;
		return avl;
	}
	private int compare(T x, T element2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
