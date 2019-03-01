package com.cherry.leetcode;

public class BinarySearchTreeApplication {

	/*
	 * 783. Minimum Distance Between BST Nodes
	 * 
	 * Given a Binary Search Tree (BST) with the root node root, return the
	 * minimum difference between the values of any two different nodes in the
	 * tree.
	 */
	Integer minDiff = Integer.MAX_VALUE, pre = null;

	public int minDiffInBST(TreeNode root) {
		if (root.left != null)
			minDiffInBST(root.left);
		if (pre != null)
			minDiff = Math.min(minDiff, root.val - pre);
		pre = root.val;
		if (root.right != null)
			minDiffInBST(root.right);
		return minDiff;
	}

	/*
	 * 530. Minimum Absolute Difference in BST
	 * 
	 * Given a binary search tree with non-negative values, find the minimum
	 * absolute difference between values of any two nodes.
	 */
	public int getMinimumDifference(TreeNode root) {
		if (root.left != null)
			getMinimumDifference(root.left);
		if (pre != null)
			minDiff = Math.min(minDiff, root.val - pre);
		pre = root.val;
		if (root.right != null)
			getMinimumDifference(root.right);
		return minDiff;
	}

	public int getMinimumDifference1(TreeNode root) {
		int[] a = new int[] { -1, Integer.MAX_VALUE };
		int[] res = inorder(root, a);
		return res[1];
	}

	public int[] inorder(TreeNode root, int[] a) {
		int pre = a[0], res = a[1];
		if (root == null)
			return a;
		int[] left = inorder(root.left, a);
		if (left[0] != -1) {
			res = Math.min(left[1], root.val - left[0]);
		}
		a[0] = root.val;
		a[1] = res;
		return inorder(root.right, a);
	}

	/*
	 * 938. Range Sum of BST
	 * 
	 * Given the root node of a binary search tree, return the sum of values of
	 * all nodes with value between L and R (inclusive).
	 * 
	 * The binary search tree is guaranteed to have unique values.
	 * 
	 */

	int sum = 0;

	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return sum;
		}
		if (root.val <= R && root.val >= L) {
			sum += root.val;
			rangeSumBST(root.left, L, R);
			rangeSumBST(root.right, L, R);
		} else if (root.val < L) {
			rangeSumBST(root.right, L, R);
		} else {
			rangeSumBST(root.left, L, R);
		}
		return sum;
	}

	public int rangeSumBST1(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val > R) return rangeSumBST(root.left, L, R);
        if(root.val < L) return rangeSumBST(root.right, L, R);
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);      
    }
	
	/*
	 * 732. My Calendar III
	 * 
	 * Implement a MyCalendarThree class to store your events. A new event can
	 * always be added.
	 * 
	 * Your class will have one method, book(int start, int end). Formally, this
	 * represents a booking on the half open interval [start, end), the range of
	 * real numbers x such that start <= x < end.
	 * 
	 * A K-booking happens when K events have some non-empty intersection (ie.,
	 * there is some time that is common to all K events.)
	 * 
	 * For each call to the method MyCalendar.book, return an integer K
	 * representing the largest integer such that there exists a K-booking in
	 * the calendar.
	 * 
	 * Your class will be called like this: MyCalendarThree cal = new
	 * MyCalendarThree(); MyCalendarThree.book(start, end)
	 */
	class MyCalendarThree {

	    public MyCalendarThree() {
	        
	    }
	    
	    public int book(int start, int end) {
	        return 0;
	    }
	}

	public static void main(String[] args) {
		BinarySearchTreeApplication asta = new BinarySearchTreeApplication();
		TreeNode root = new TreeNode(10);
		root.right = new TreeNode(15);
		root.right.right = new TreeNode(18);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);
		System.out.println(asta.rangeSumBST(root, 7, 15));
	}

}
