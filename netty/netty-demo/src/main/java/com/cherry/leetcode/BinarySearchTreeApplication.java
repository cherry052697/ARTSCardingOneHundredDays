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

	public static void main(String[] args) {
		BinarySearchTreeApplication asta = new BinarySearchTreeApplication();
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);
		System.out.println(asta.getMinimumDifference(root));
	}

}
