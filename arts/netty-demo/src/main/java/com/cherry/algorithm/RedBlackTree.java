package com.cherry.algorithm;


import org.hibernate.type.AnyType;

public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {

	// 参考hashmap.treeNode

	private static class RedBlackNode<AnyType> {
		AnyType element;
		RedBlackNode<AnyType> left;
		RedBlackNode<AnyType> right;
		RedBlackNode<AnyType> root;
		int color;

		RedBlackNode(AnyType theElement) {
			this(theElement, null, null);
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

	public RedBlackTree() {
		nullNode = new RedBlackNode<AnyType>(null);
		nullNode.left = nullNode.right = nullNode;
		header = new RedBlackNode<AnyType>(null);
		header.left = header.right = nullNode;
	}

	private RedBlackNode<AnyType> rotate(AnyType item, RedBlackNode<AnyType> parent) {
		if (compare(item, parent) < 0)
			return parent.left = compare(item, parent.left) < 0 ? rotateWithLeftChild(parent.left)
					: rotateWithRightChild(parent.left);
		else
			return parent.right = compare(item, parent.right) < 0 ? rotateWithLeftChild(parent.right)
					: rotateWithRightChild(parent.right);

	}

	private RedBlackNode<AnyType> rotateWithRightChild(RedBlackNode<AnyType> h) {
		RedBlackNode<AnyType> x = h.left;
		h.left = x.right;
		x.right = h;

		x.color = h.color;
		h.color = RED;
		/*
		 * x.N = h.N; h.N = 1+ size(h.left) + size(h.right);
		 */
		return x;
	}

	private RedBlackNode<AnyType> rotateWithLeftChild(RedBlackNode<AnyType> h) {
		RedBlackNode<AnyType> x = h.right;
		// 把x的左结点赋值给h的右结点
		h.right = x.left;
		// 把h赋值给x的左结点
		x.left = h;
		//
		x.color = h.color;
		h.color = RED;
		// x.N = h.N;
		// h.N = 1+ size(h.left) + size(h.right);

		return x;

	}
	
	private void flipColors(RedBlackNode<AnyType> h){
	    h.color = RED;//父结点颜色变红
	    h.left.color = BLACK;//子结点颜色变黑
	    h.right.color = BLACK;//子结点颜色变黑
	}

	private int compare(AnyType item, RedBlackNode<AnyType> parent) {
		if (parent == item)
			return 1;
		else
			return item.compareTo(parent.element);
	}

}
