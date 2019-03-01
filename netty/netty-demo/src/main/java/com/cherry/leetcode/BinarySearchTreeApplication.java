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

	public int rangeSumBST(TreeNode root, int L, int R) {
		return 0;
	}

	public static void main(String[] args) {
		BinarySearchTreeApplication asta = new BinarySearchTreeApplication();
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);
		System.out.println(asta.getMinimumDifference(root));
	}

}
