package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
	 * 169. Majority Element
	 * 
	 * Given an array of size n, find the majority element. The majority element
	 * is the element that appears more than ⌊ n/2 ⌋ times. You may assume that
	 * the array is non-empty and the majority element always exist in the
	 * array.
	 */
	@SuppressWarnings("unused")
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
	 * 538. Convert BST to Greater Tree
	 * 
	 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that
	 * every key of the original BST is changed to the original key plus sum of
	 * all keys greater than the original key in BST.
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
	 * 543. Diameter of Binary Tree(important)
	 * 
	 * Given a binary tree, you need to compute the length of the diameter of
	 * the tree. The diameter of a binary tree is the length of the longest path
	 * between any two nodes in a tree. This path may or may not pass through
	 * the root. Note: The length of path between two nodes is represented by
	 * the number of edges between them.
	 */

	int diameter;

	public int diameterOfBinaryTree(TreeNode root) {
		diameter = 1;
		depth(root);
		return diameter - 1;
	}

	public int depth(TreeNode node) {
		if (node == null)
			return 0;
		int L = depth(node.left);
		int R = depth(node.right);
		diameter = Math.max(diameter, L + R + 1);
		return Math.max(L, R) + 1;
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

	/*
	 * 121. Best Time to Buy and Sell Stock
	 * 
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i. If you were only permitted to complete at most one
	 * transaction (i.e., buy one and sell one share of the stock), design an
	 * algorithm to find the maximum profit. Note that you cannot sell a stock
	 * before you buy one.
	 */
	public int maxProfit(int[] prices) {
		int profit = 0;
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				int buyPrice = prices[i];
				int sellPrice = prices[j];
				if (buyPrice >= sellPrice) {
					continue;
				} else {
					profit = Math.max(profit, (sellPrice - buyPrice));
				}
			}
		}
		return profit;
	}

	public int maxProfit2(int[] prices) {
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minprice)
				minprice = prices[i];
			else if (prices[i] - minprice > maxprofit)
				maxprofit = prices[i] - minprice;
		}
		return maxprofit;
	}

	/*
	 * 101. Symmetric Tree
	 * 
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 */
	public boolean isSymmetric(TreeNode root) {
		return false;
	}

	/*
	 * 198. House Robber
	 * 
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping
	 * you from robbing each of them is that adjacent houses have security
	 * system connected and it will automatically contact the police if two
	 * adjacent houses were broken into on the same night. Given a list of
	 * non-negative integers representing the amount of money of each house,
	 * determine the maximum amount of money you can rob tonight without
	 * alerting the police.
	 * 
	 */
	public int rob(int[] nums) {
		int len = nums.length;
		if (len == 0)
			return 0;
		if (len == 1)
			return nums[0];
		int[] nums2 = new int[len + 1];
		nums2[0] = 0;
		nums2[1] = nums[0];
		for (int i = 2; i <= len; i++) {
			nums2[i] = Math.max(nums2[i - 1], (nums2[i - 2] + nums[i - 1]));
		}
		return nums2[nums.length];
	}

	/*
	 * 338. Counting Bits
	 * 
	 * Given a non negative integer number num. For every numbers i in the range
	 * 0 ≤ i ≤ num calculate the number of 1's in their binary representation
	 * and return them as an array.
	 * 
	 */
	public int[] countBits(int num) {
		int[] array = new int[num + 1];
		if (num >= 0) {
			for (int i = 0; i < array.length; i++) {
				// 求一个镇整数转换成二进制时，1的个数
				int result = i;
				int count = 0;
				while (result != 0) {
					++count;
					result = (result - 1) & result;
				}
				array[i] = count;
			}
		}
		return array;
	}

	/*
	 * 234. Palindrome Linked List
	 * 
	 * Given a singly linked list, determine if it is a palindrome.
	 */
	public boolean isPalindrome(ListNode head) {
		ListNode pre = null;
		ListNode curr = head;
		StringBuffer strHead = new StringBuffer();
		StringBuffer strResverse = new StringBuffer();
		while (curr != null) {
			strHead.append(curr.val);
			strResverse.insert(0, curr.val);
			ListNode temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
		return strHead.toString().equals(strResverse.toString());
	}

	public boolean isPalindrome2(ListNode head) {
		boolean result = false;
		ListNode resNode = reverseList(head);
		while (head != null && resNode != null) {
			if (head.val == resNode.val) {
				head = head.next;
				resNode = resNode.next;
				result = true;
			} else {
				result = false;
				break;
			}
		}
		return result;
	}

	public ListNode reverseListNode(ListNode head) {
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

	// 链表原地转置实现o(1)空间复杂度
	public static boolean isPalindrome3(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		if (fast == null || fast.next == null)// 0个节点或是1个节点
			return true;

		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		// 对链表后半段进行反转
		ListNode midNode = slow;
		ListNode firNode = slow.next;// 后半段链表的第一个节点
		ListNode cur = firNode.next;// 插入节点从第一个节点后面一个开始
		firNode.next = null;// 第一个节点最后会变最后一个节点
		while (cur != null) {
			ListNode nextNode = cur.next;// 保存下次遍历的节点
			cur.next = midNode.next;
			midNode.next = cur;
			cur = nextNode;
		}

		// 反转之后对前后半段进行比较
		slow = head;
		fast = midNode.next;
		while (fast != null) {
			if (fast.val != slow.val)
				return false;
			slow = slow.next;
			fast = fast.next;
		}
		return true;

	}

	/*
	 * 155. Min Stack
	 * 
	 * Design a stack that supports push, pop, top, and retrieving the minimum
	 * element in constant time. push(x) -- Push element x onto stack. pop() --
	 * Removes the element on top of the stack. top() -- Get the top element.
	 * getMin() -- Retrieve the minimum element in the stack.
	 */
	class MinStack {

		long min;
		Stack<Long> stack;

		public MinStack() {
			stack = new Stack<>();
		}

		public void push(int x) {
			if (stack.isEmpty()) {
				stack.push(0L);
				min = x;
			} else {
				stack.push(x - min);
				if (x < min)
					min = x;
			}
		}

		public void pop() {
			if (stack.isEmpty())
				return;

			long pop = stack.pop();

			if (pop < 0)
				min = min - pop;

		}

		public int top() {
			long top = stack.peek();
			if (top > 0) {
				return (int) (top + min);
			} else {
				return (int) (min);
			}
		}

		public int getMin() {
			return (int) min;
		}
	}

	/*
	 * 141. Linked List Cycle
	 * 
	 * Given a linked list, determine if it has a cycle in it. To represent a
	 * cycle in the given linked list, we use an integer pos which represents
	 * the position (0-indexed) in the linked list where tail connects to. If
	 * pos is -1, then there is no cycle in the linked list.
	 * 
	 */
	public boolean hasCycle(ListNode head) {
		return false;
	}

	/*
	 * 494. Target Sum
	 * 
	 * You are given a list of non-negative integers, a1, a2, ..., an, and a
	 * target, S. Now you have 2 symbols + and -. For each integer, you should
	 * choose one from + and - as its new symbol.
	 * 
	 * The length of the given array is positive and will not exceed 20.
	 * The sum of elements in the given array will not exceed 1000.
	 * Your output answer is guaranteed to be fitted in a 32-bit integer.
	 */
	int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }
    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S)
                count++;
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }
    
    public int findTargetSumWays2(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
    }   

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    } 

}
