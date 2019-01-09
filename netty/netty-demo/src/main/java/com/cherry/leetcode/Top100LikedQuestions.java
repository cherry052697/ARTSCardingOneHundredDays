package com.cherry.leetcode;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

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
	 * 771. Jewels and Stones You're given strings J representing the types of
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
	 * 136. Single Number Given a non-empty array of integers, every element
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
	 * 79. Word Search The word can be constructed from letters of sequentially
	 * adjacent cell, where "adjacent" cells are those horizontally or
	 * vertically neighboring. The same letter cell may not be used more than
	 * once.
	 */
	public boolean exist(char[][] board, String word) {

		return false;
	}

	/*
	 * 461. Hamming Distance The Hamming distance between two integers is the
	 * number of positions at which the corresponding bits are different. Given
	 * two integers x and y, calculate the Hamming distance.
	 */
	public int hammingDistance(int x, int y) {
		int result = x ^ y;
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((result & 1) != 0)
				count++;
			result >>= 1;
		}
		return count;
	}

	int hammingDistance2(int x, int y) {
		int result = x ^ y;
		int count = 0;
		while (result != 0) {
			++count;
			result = (result - 1) & result;
		}
		return count;
	}

	/*
	 * 283. Move Zeroes Given an array nums, write a function to move all 0's to
	 * the end of it while maintaining the relative order of the non-zero
	 * elements.
	 */
	public void moveZeroes(int[] nums) {
		int[] nums2 = Arrays.copyOf(nums, nums.length);
		int count1 = 0, count2 = 0;
		for (int i = 0; i < nums2.length; i++) {
			if (nums2[i] == 0) {
				nums[nums2.length - 1 - count2] = 0;
				count2++;
			} else {
				nums[count1] = nums2[i];
				count1++;
			}
		}
	}

	/*
	 * 3. Longest Substring Without Repeating Characters Given a string, find
	 * the length of the longest substring without repeating characters.
	 */
	public int lengthOfLongestSubstring(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of
														// character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

	public int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	/*
	 * 647. Palindromic Substrings Given a string, your task is to count how
	 * many palindromic substrings in this string. The substrings with different
	 * start indexes or end indexes are counted as different substrings even
	 * they consist of same characters.
	 */
	public int countSubstrings(String s) {
		return 0;
	}

	/*
	 * 617. Merge Two Binary Trees Given two binary trees and imagine that when
	 * you put one of them to cover the other, some nodes of the two trees are
	 * overlapped while the others are not. You need to merge them into a new
	 * binary tree. The merge rule is that if two nodes overlap, then sum node
	 * values up as the new value of the merged node. Otherwise, the NOT null
	 * node will be used as the node of new tree.
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

		if (t1 != null && t2 != null) {
			t1.val += t2.val;
			t1.left = mergeTrees(t1.left, t2.left);
			t1.right = mergeTrees(t1.right, t2.right);
			return t1;
		} else {
			return (t1 != null && t2 == null) ? t1 : ((t1 == null && t2 != null) ? t2 : null);
		}
	}

	/*
	 * 160. Intersection of Two Linked Lists Write a program to find the node at
	 * which the intersection of two singly linked lists begins.
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		ListNode a = headA, b = headB;
		while (a != b) {
			a = (a != null) ? a.next : headB;
			b = (b != null) ? b.next : headA;
		}
		return a;
	}

	/*
	 * 448. Find All Numbers Disappeared in an Array Given an array of integers
	 * where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and
	 * others appear once. Find all the elements of [1, n] inclusive that do not
	 * appear in this array. Could you do it without extra space and in O(n)
	 * runtime? You may assume the returned list does not count as extra space.
	 * 
	 */
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		if (nums == null || nums.length == 0)
			return res;
		int n = nums.length;
		int[] num = new int[n];
		Arrays.fill(num, -1);
		for (int i = 0; i < n; i++)
			num[nums[i] - 1] = nums[i];
		for (int i = 0; i < n; i++) {
			if (num[i] == -1)
				res.add(i + 1);
		}
		return res;
	}

	/*
	 * 20. Valid Parentheses Given a string containing just the characters '(',
	 * ')', '{', '}', '[' and ']', determine if the input string is valid.
	 */

	public boolean isValid(String s) {
		HashMap<Character, Character> mappings = new HashMap<Character, Character>();
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (mappings.containsKey(c)) {
				char topElement = stack.empty() ? '#' : stack.pop();
				if (topElement != mappings.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	/*
	 * 226. Invert Binary Tree Invert a binary tree.
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		} else if (root.left != null && root.right == null) {
			root.right = invertTree(root.left);
			root.left = null;
		} else if (root.left == null && root.right != null) {
			root.left = invertTree(root.right);
			;
			root.right = null;
		} else if (root.left != null && root.right != null) {
			TreeNode treeNode = root.left;
			root.left = invertTree(root.right);
			root.right = invertTree(treeNode);
		}
		return root;
	}

	/*
	 * 104. Maximum Depth of Binary Tree Given a binary tree, find its maximum
	 * depth. The maximum depth is the number of nodes along the longest path
	 * from the root node down to the farthest leaf node. Note: A leaf is a node
	 * with no children.
	 */
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
		}
	}

	/*
	 * 206. Reverse Linked List Reverse a singly linked list.
	 */
	public ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
		return pre;
	}

	/*
	 * 169. Majority Element Given an array of size n, find the majority
	 * element. The majority element is the element that appears more than ⌊ n/2
	 * ⌋ times. You may assume that the array is non-empty and the majority
	 * element always exist in the array.
	 */
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int len = nums.length;
		if (nums == null)
			return 0;
		for (int i = 0; i < len; i++) {
			int key = nums[i];
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		for (int key : map.keySet()) {
			if (map.get(key) > len / 2) {
				return key;
			}
		}
		return 0;
	}

	public int majorityElement2(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}

	/*
	 * 538. Convert BST to Greater Tree Given a Binary Search Tree (BST),
	 * convert it to a Greater Tree such that every key of the original BST is
	 * changed to the original key plus sum of all keys greater than the
	 * original key in BST.
	 */
	int sum = 0;

	public TreeNode convertBST(TreeNode root) {
		if (root != null) {
			convertBST(root.right);
			sum += root.val;
			root.val = sum;
			convertBST(root.left);
		}
		return root;
	}
	/*
	 * 543. Diameter of Binary Tree
	 * 
	 * Given a binary tree, you need to compute the length of the diameter of
	 * the tree. The diameter of a binary tree is the length of the longest path
	 * between any two nodes in a tree. This path may or may not pass through
	 * the root. Note: The length of path between two nodes is represented by
	 * the number of edges between them.
	 */

	public int diameterOfBinaryTree(TreeNode root) {
		return 0;
	}

	/*
	 * 437. Path Sum III
	 * 
	 * You are given a binary tree in which each node contains an integer value.
	 * Find the number of paths that sum to a given value. The path does not
	 * need to start or end at the root or a leaf, but it must go downwards
	 * (traveling only from parent nodes to child nodes). The tree has no more
	 * than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
	 */
	public int pathSum(TreeNode root, int sum) {
		return 0;
	}

}
