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

	public static void main(String[] args) {

	}

}
