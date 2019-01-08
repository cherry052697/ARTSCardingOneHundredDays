package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Top100LikedQuestions {
	/*
	 * Given an array nums of n integers, are there elements a, b, c in nums
	 * such that a + b + c = 0? Find all unique triplets in the array which
	 * gives the sum of zero.
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i <= nums.length - 3; i++)
			if (nums[i] <= 0) {
				if (i >= 1 && nums[i] == nums[i - 1])
					continue;
				int j = i + 1;
				int k = nums.length - 1;
				while (j < k) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[k]);
						result.add(list);
						j++;
						k--;
						while (j < k && nums[j] == nums[j - 1])
							j++;
						while (j < k && nums[k] == nums[k + 1])
							k--;
					} else {
						boolean flag = true;
						while (flag) {
							while (j < k && nums[j] + nums[k] > (-1) * nums[i])
								k--;
							while (j < k && nums[j] + nums[k] < (-1) * nums[i])
								j++;
							if (j >= k || nums[j] + nums[k] == (-1) * nums[i])
								flag = false;
						}
					}
				}
			}
		return result;
	}

	/*
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * Assume a BST is defined as follows: The left subtree of a node contains
	 * only nodes with keys less than the node's key. The right subtree of a
	 * node contains only nodes with keys greater than the node's key. Both the
	 * left and right subtrees must also be binary search trees.
	 */
	TreeNode pre = null;

	public boolean isValidBST(TreeNode root) {
		if (root != null) {
			if (!isValidBST(root.left))
				return false;
			if (pre != null && root.val <= pre.val)
				return false;
			pre = root;
			return isValidBST(root.right);
		}
		return true;
	}

	/*
	 * Given a string s and a non-empty string p, find all the start indices of
	 * p's anagrams in s. Strings consists of lowercase English letters only and
	 * the length of both strings s and p will not be larger than 20,100. The
	 * order of output does not matter.
	 */
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return result;
		int[] hash = new int[256];
		char[] pp = p.toCharArray();
		for (char i : pp) {
			hash[i]++;
		}
		int left = 0, right = 0, count = p.length();
		while (right < s.length()) {
			if (hash[s.charAt(right++)]-- > 0)
				count--;
			if (count == 0)
				result.add(left);
			if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
				count++;
		}
		return result;

	}

	/*
	 * 771. Jewels and Stones 
	 * You're given strings J representing the types of
	 * stones that are jewels, and S representing the stones you have. Each
	 * character in S is a type of stone you have. You want to know how many of
	 * the stones you have are also jewels. The letters in J are guaranteed
	 * distinct, and all characters in J and S are letters. Letters are case
	 * sensitive, so "a" is considered a different type of stone from "A".
	 */
	public int numJewelsInStones(String J, String S) {
		int result = 0;
		int jlen = J.length();
		int slen = S.length();
		for (int i = 0; i < slen; i++) {
			for (int j = 0; j < jlen; j++) {
				if (S.charAt(i) == J.charAt(j)) {
					result++;
					break;
				}
			}
		}
		return result;
	}

	/*
	 * 136. Single Number
	 * Given a non-empty array of integers, every element
	 * appears twice except for one. Find that single one.
	 */
	public int singleNumber2(int[] nums) {
		int a = 0;
		for (int i = 0; i < nums.length; i++) {
			a = a ^ nums[i];
		}
		return a;
	}

	/*
	 * 79. Word Search
	 *  The word can be constructed from letters of sequentially
	 * adjacent cell, where "adjacent" cells are those horizontally or
	 * vertically neighboring. The same letter cell may not be used more than
	 * once.
	 */
	public boolean exist(char[][] board, String word) {
		
		return false;
	}
}
