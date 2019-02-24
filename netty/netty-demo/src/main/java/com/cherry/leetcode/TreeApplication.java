package com.cherry.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class TreeApplication {
	/*
	 * 654. Maximum Binary Tree
	 * 
	 * Given an integer array with no duplicates. A maximum tree building on
	 * this array is defined as follow:
	 * 
	 * The root is the maximum number in the array. The left subtree is the
	 * maximum tree constructed from left part subarray divided by the maximum
	 * number. The right subtree is the maximum tree constructed from right part
	 * subarray divided by the maximum number. Construct the maximum tree by the
	 * given array and output the root node of this tree.
	 */
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		Deque<TreeNode> stack = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			TreeNode curr = new TreeNode(nums[i]);
			while (!stack.isEmpty() && stack.peek().val < nums[i]) {
				curr.left = stack.pop();
			}
			if (!stack.isEmpty()) {
				stack.peek().right = curr;
			}
			stack.push(curr);
		}

		return stack.isEmpty() ? null : stack.removeLast();
	}

	public TreeNode constructMaximumBinaryTree2(int[] nums) {
		if (nums == null)
			return null;
		return build(nums, 0, nums.length - 1);
	}

	private TreeNode build(int[] nums, int start, int end) {
		if (start > end)
			return null;

		int idxMax = start;
		for (int i = start + 1; i <= end; i++) {
			if (nums[i] > nums[idxMax]) {
				idxMax = i;
			}
		}

		TreeNode root = new TreeNode(nums[idxMax]);

		root.left = build(nums, start, idxMax - 1);
		root.right = build(nums, idxMax + 1, end);

		return root;
	}

	int treeNodeSize(TreeNode node) {
		TreeNode leftnode = node.left;
		TreeNode rightnode = node.right;
		int size = 1;
		if (leftnode != null)
			size += treeNodeSize(leftnode);
		if (rightnode != null)
			size += treeNodeSize(rightnode);
		return size;

	}

	TreeNode inorderTreeNode(TreeNode root) {
		if (root == null)
			return null;
		inorderTreeNode(root.left);
		inorderTreeNode(root.right);
		return root;
	}

	/*
	 * 814. Binary Tree Pruning
	 * 
	 * We are given the head node root of a binary tree, where additionally
	 * every node's value is either a 0 or a 1.
	 * 
	 * Return the same tree where every subtree (of the given tree) not
	 * containing a 1 has been removed.
	 * 
	 * (Recall that the subtree of a node X is X, plus every node that is a
	 * descendant of X.)
	 * 
	 */

	public TreeNode pruneTree(TreeNode root) {
		return containsOne(root) ? root : null;
	}

	public boolean containsOne(TreeNode node) {
		if (node == null)
			return false;
		boolean a1 = containsOne(node.left);
		boolean a2 = containsOne(node.right);
		if (!a1)
			node.left = null;
		if (!a2)
			node.right = null;
		return node.val == 1 || a1 || a2;
	}
	/*
	 * 669. Trim a Binary Search Tree
	 * 
	 * Given a binary search tree and the lowest and highest boundaries as L and
	 * R, trim the tree so that all its elements lies in [L, R] (R >= L). You
	 * might need to change the root of the tree, so the result should return
	 * the new root of the trimmed binary search tree.
	 */

	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null)
			return root;
		if (root.val > R)
			return trimBST(root.left, L, R);
		if (root.val < L)
			return trimBST(root.right, L, R);

		root.left = trimBST(root.left, L, R);
		root.right = trimBST(root.right, L, R);
		return root;
	}

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

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TreeApplication ta = new TreeApplication();
		int[] nums = { 3, 2, 1, 6, 0, 5 };
		// System.out.println(ta.constructMaximumBinaryTree(nums));

		TreeNode node = new TreeNode(4);
		node.left = new TreeNode(2);
		node.right = new TreeNode(6);
		node.left.left = new TreeNode(1);
		node.left.right = new TreeNode(3);
		System.out.println(ta.minDiffInBST(node));

	}

}
