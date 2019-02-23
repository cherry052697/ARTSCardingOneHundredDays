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
	        for(int i = 0; i < nums.length; i++) {
	            TreeNode curr = new TreeNode(nums[i]);
	            while(!stack.isEmpty() && stack.peek().val < nums[i]) {
	                curr.left = stack.pop();
	            }
	            if(!stack.isEmpty()) {
	                stack.peek().right = curr;
	            }
	            stack.push(curr);
	        }
	        
	        return stack.isEmpty() ? null : stack.removeLast();
	}
	 public TreeNode constructMaximumBinaryTree2(int[] nums) {
	        if (nums == null) return null;
	        return build(nums, 0, nums.length - 1);
	    }
	    
	    private TreeNode build(int[] nums, int start, int end) {
	        if (start > end) return null;
	        
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
	
	int treeNodeSize(TreeNode node){
		TreeNode leftnode = node.left;
		TreeNode rightnode = node.right;
		int size = 1;
		if (leftnode != null) 
			size += treeNodeSize(leftnode);
		if(rightnode != null)
			size += treeNodeSize(rightnode);
		return size;
		
	}

	public static void main(String[] args) {
		TreeApplication ta = new TreeApplication();
		int[] nums = {3,2,1,6,0,5};
		System.out.println(ta.constructMaximumBinaryTree(nums));
	}

}
