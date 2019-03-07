package com.cherry.algorithm;

import org.hibernate.type.AnyType;


public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {
	
	private static class RedBlackNode<AnyType>{
		AnyType element;
		RedBlackNode<AnyType> left;
		RedBlackNode<AnyType> right;
		int color;
		RedBlackNode(AnyType theElement){
			this(theElement,null,null);
		}

		RedBlackNode(AnyType theElement, RedBlackNode<AnyType> left, RedBlackNode<AnyType> right) {
			element = theElement;
			left = left;
			right = right;
		}
	}
	private RedBlackNode<AnyType> header;
	private RedBlackNode<AnyType> nullNode;
	
	private static final int BLACK = 1;
	private static final int RED = 0;
	
	
	
	public RedBlackTree(){
		nullNode = new RedBlackNode<AnyType>(null);
		nullNode.left = nullNode.right = nullNode;
		header = new RedBlackNode<AnyType>(null);
		header.left = header.right = nullNode;
	}
	private RedBlackNode<AnyType> rotate(AnyType item,RedBlackNode<AnyType> parent){
		if(compare(item,parent) < 0 )
			return parent.left = compare(item,parent.left)<0?rotateWithLeftChild(parent.left):rotateWithRightChild(parent.left);
		else
			return parent.right = compare(item,parent.right)<0?rotateWithLeftChild(parent.right):rotateWithRightChild(parent.right);
			
	}


	private RedBlackNode<AnyType> rotateWithRightChild(RedBlackNode<AnyType> left) {
		// TODO Auto-generated method stub
		return null;
	}


	private RedBlackNode<AnyType> rotateWithLeftChild(RedBlackNode<AnyType> h) {
		    return null;
		
	}


	private int compare(AnyType item, RedBlackNode<AnyType> parent) {
		if(parent == item)
			return 1;
		else
			return item.compareTo(parent.element);
	}
	
}
