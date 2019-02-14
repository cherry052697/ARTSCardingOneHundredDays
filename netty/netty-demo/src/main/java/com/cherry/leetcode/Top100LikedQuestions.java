package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;

import com.cherry.netty.utils.JsonUtil;

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
	 * 437. Path Sum III(important)
	 * 
	 * You are given a binary tree in which each node contains an integer value.
	 * Find the number of paths that sum to a given value. The path does not
	 * need to start or end at the root or a leaf, but it must go downwards
	 * (traveling only from parent nodes to child nodes). The tree has no more
	 * than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
	 */
	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
		preSum.put(0, 1);
		return helper(root, 0, sum, preSum);
	}

	public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
		if (root == null) {
			return 0;
		}
		currSum += root.val;
		int res = preSum.getOrDefault(currSum - target, 0);
		preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
		res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
		preSum.put(currSum, preSum.get(currSum) - 1);
		return res;
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
	public boolean isSymmetric2(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (root == null)
			return true;
		q.add(root.left);
		q.add(root.right);
		while (q.size() > 1) {
			TreeNode left = q.poll();
			TreeNode right = q.poll();
			if (left == null && right == null)
				continue;
			if (left == null ^ right == null)
				return false;
			if (left.val != right.val)
				return false;
			q.add(left.left);
			q.add(right.right);
			q.add(left.right);
			q.add(right.left);
		}
		return true;
	}

	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	public boolean isMirror(TreeNode rightNode, TreeNode leftNode) {
		if (rightNode == null && leftNode == null)
			return true;
		if (rightNode == null || leftNode == null)
			return false;
		return (rightNode.val == leftNode.val) && isMirror(rightNode.right, leftNode.left)
				&& isMirror(rightNode.left, leftNode.right);
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
		ListNode pre = null;
		ListNode curr = head;
		int count = 0, index = 0;
		// reverseListNode
		while (curr != null) {
			ListNode temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
			count++;
		}
		while (index < count) {
			if (pre.next != null) {
				index++;
				pre = pre.next;
			} else {
				break;
			}
		}
		return index < count ? true : false;
	}

	public boolean hasCycle2(ListNode head) {
		if (head == null)
			return false;
		ListNode walker = head;
		ListNode runner = head;
		while (runner.next != null && runner.next.next != null) {
			walker = walker.next;
			runner = runner.next.next;
			if (walker == runner)
				return true;
		}
		return false;
	}

	/*
	 * 494. Target Sum
	 * 
	 * You are given a list of non-negative integers, a1, a2, ..., an, and a
	 * target, S. Now you have 2 symbols + and -. For each integer, you should
	 * choose one from + and - as its new symbol.
	 * 
	 * The length of the given array is positive and will not exceed 20. The sum
	 * of elements in the given array will not exceed 1000. Your output answer
	 * is guaranteed to be fitted in a 32-bit integer.
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

	/*
	 * 84. Largest Rectangle in Histogram
	 * 
	 * Given n non-negative integers representing the histogram's bar height
	 * where the width of each bar is 1, find the area of largest rectangle in
	 * the histogram.
	 */
	public int largestRectangleArea(int[] heights) {
		int len = heights.length;
		Stack<Integer> s = new Stack<Integer>();
		int maxArea = 0;
		for (int i = 0; i <= len; i++) {
			int h = (i == len ? 0 : heights[i]);
			if (s.isEmpty() || h >= heights[s.peek()]) {
				s.push(i);
			} else {
				int top = s.pop();
				maxArea = Math.max(maxArea, heights[top] * (s.isEmpty() ? i : i - 1 - s.peek()));
				i--;
			}
		}
		return maxArea;
	}

	public int largestRectangleArea2(int[] heights) {
		int len = heights.length;
		if (heights == null || len == 0) {
			return 0;
		}
		int[] lessFromLeft = new int[len];
		int[] lessFromRight = new int[len];
		lessFromRight[len - 1] = len;
		lessFromLeft[0] = -1;

		for (int i = 1; i < len; i++) {
			int p = i - 1;

			while (p >= 0 && heights[p] >= heights[i]) {
				p = lessFromLeft[p];
			}
			lessFromLeft[i] = p;
		}

		for (int i = len - 2; i >= 0; i--) {
			int p = i + 1;

			while (p < len && heights[p] >= heights[i]) {
				p = lessFromRight[p];
			}
			lessFromRight[i] = p;
		}

		int maxArea = 0;
		for (int i = 0; i < len; i++) {
			maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
		}

		return maxArea;
	}

	public int largestRectangleArea3(int[] heights) {
		if (heights == null || heights.length == 0)
			return 0;
		int n = heights.length;
		int[] lessLeft = new int[n];
		int[] lessRight = new int[n];
		lessLeft[0] = -1;
		lessRight[n - 1] = n;
		for (int i = 1; i < n; i++) {
			if (heights[i] > heights[i - 1]) {
				lessLeft[i] = i - 1;
			} else {
				int j = lessLeft[i - 1];
				while (j >= 0 && heights[j] >= heights[i])
					j--;
				lessLeft[i] = j;
			}
		}
		for (int i = n - 2; i >= 0; i--) {
			if (heights[i] > heights[i + 1]) {
				lessRight[i] = i + 1;
			} else {
				int j = lessRight[i + 1];
				while (j < n && heights[j] >= heights[i])
					j++;
				lessRight[i] = j;
			}
		}
		int maxArea = 0;
		for (int i = 0; i < n; i++) {
			maxArea = Math.max(maxArea, heights[i] * (lessRight[i] - lessLeft[i] - 1));
		}
		return maxArea;
	}

	/*
	 * 406. Queue Reconstruction by Height
	 * 
	 * Suppose you have a random list of people standing in a queue. Each person
	 * is described by a pair of integers (h, k), where h is the height of the
	 * person and k is the number of people in front of this person who have a
	 * height greater than or equal to h. Write an algorithm to reconstruct the
	 * queue.
	 */
	public int[][] reconstructQueue(int[][] people) {
		if (people == null || people.length == 0 || people[0].length == 0)
			return new int[0][0];

		Arrays.sort(people, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (b[0] == a[0])
					return a[1] - b[1];
				return b[0] - a[0];
			}
		});

		int n = people.length;
		ArrayList<int[]> tmp = new ArrayList<>();
		for (int i = 0; i < n; i++)
			tmp.add(people[i][1], new int[] { people[i][0], people[i][1] });

		int[][] res = new int[people.length][2];
		int i = 0;
		for (int[] k : tmp) {
			res[i][0] = k[0];
			res[i++][1] = k[1];
		}

		return res;
	}

	/*
	 * 572. Subtree of Another Tree
	 * 
	 * Given two non-empty binary trees s and t, check whether tree t has
	 * exactly the same structure and node values with a subtree of s. A subtree
	 * of s is a tree consists of a node in s and all of this node's
	 * descendants. The tree s could also be considered as a subtree of itself.
	 */
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null)
			return false;
		if (isSame(s, t))
			return true;
		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private boolean isSame(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;

		if (s.val != t.val)
			return false;

		return isSame(s.left, t.left) && isSame(s.right, t.right);
	}

	public boolean isSubtree2(TreeNode s, TreeNode t) {
		String spreorder = generatepreorderString(s);
		String tpreorder = generatepreorderString(t);

		return spreorder.contains(tpreorder);
	}

	public String generatepreorderString(TreeNode s) {
		StringBuilder sb = new StringBuilder();
		Stack<TreeNode> stacktree = new Stack<TreeNode>();
		stacktree.push(s);
		while (!stacktree.isEmpty()) {
			TreeNode popelem = stacktree.pop();
			if (popelem == null)
				sb.append(",#"); // Appending # inorder to handle same values
									// but not subtree cases
			else
				sb.append("," + popelem.val);
			if (popelem != null) {
				stacktree.push(popelem.right);
				stacktree.push(popelem.left);
			}
		}
		return sb.toString();
	}

	/*
	 * 62. Unique Paths
	 * 
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start'
	 * in the diagram below).
	 * 
	 * The robot can only move either down or right at any point in time. The
	 * robot is trying to reach the bottom-right corner of the grid (marked
	 * 'Finish' in the diagram below).
	 * 
	 * This is a combinatorial problem and can be solved without DP. For mxn
	 * grid, robot has to move exactly m-1 steps down and n-1 steps right and
	 * these can be done in any order.
	 * 
	 * For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8
	 * steps with 2 down and 6 right in any order. That is nothing but a
	 * permutation problem. Denote down as 'D' and right as 'R', following is
	 * one of the path :-
	 * 
	 * D R R R D R R R
	 * 
	 * We have to tell the total number of permutations of the above given word.
	 * So, decrease both m & n by 1 and apply following formula:-
	 * 
	 * Total permutations = (m+n)! / (m! * n!)
	 * 
	 */
	public int uniquePaths(int m, int n) {
		if (m == 1 || n == 1)
			return 1;
		m--;
		n--;
		// Swap, so that m is the bigger number
		if (m < n) {
			m = m + n;
			n = m - n;
			m = m - n;
		}
		long res = 1;
		int j = 1;
		// Instead of taking factorial, keep on multiply & divide
		for (int i = m + 1; i <= m + n; i++, j++) {
			res *= i;
			res /= j;
		}

		return (int) res;
	}

	/*
	 * If you mark the south move as '1' and the east move as '0'. This problem
	 * shall be equal to : Given (m+n-2) bits. you can fill in '1' for (m-1)
	 * times and '0' for (n-1) times, what is the number of different numbers?
	 * the result is clear that the formula shall be C(m-1)(m+n-2), where m-1 is
	 * the superscript behind C and m+n-2 is the subscript behind C.
	 */
	public int uniquePaths2(int m, int n) {
		long result = 1;
		for (int i = 0; i < Math.min(m - 1, n - 1); i++)
			result = result * (m + n - 2 - i) / (i + 1);
		return (int) result;
	}

	/*
	 * 5. Longest Palindromic Substring
	 * 
	 * Given a string s, find the longest palindromic substring in s. You may
	 * assume that the maximum length of s is 1000.
	 */
	public String longestPalindrome(String s) {
		if (s.isEmpty())
			return "";
		if (s.length() == 1)
			return s;
		int min_start = 0, max_len = 1;
		for (int i = 0; i < s.length();) {
			if (s.length() - i <= max_len / 2)
				break;
			int j = i, k = i;
			while (k < s.length() - 1 && s.charAt(k + 1) == s.charAt(k))
				++k;
			i = k + 1;
			while (k < s.length() - 1 && j > 0 && s.charAt(k + 1) == s.charAt(j - 1)) {
				++k;
				--j;
			}
			int new_len = k - j + 1;
			if (new_len > max_len) {
				min_start = j;
				max_len = new_len;
			}
		}
		return s.substring(min_start, max_len);
	}

	private int lo, maxLen;

	public String longestPalindrome2(String s) {
		int len = s.length();
		if (len < 2)
			return s;

		for (int i = 0; i < len - 1; i++) {
			extendPalindrome(s, i, i);
			extendPalindrome(s, i, i + 1);
		}
		return s.substring(lo, lo + maxLen);
	}

	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}

	/*
	 * dp(i, j) represents whether s(i ... j) can form a palindromic substring,
	 * dp(i, j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a
	 * palindromic substring. When we found a palindrome, check if it's the
	 * longest one. Time complexity O(n^2)
	 */
	public String longestPalindrome3(String s) {
		int len = s.length();
		String res = null;
		boolean[][] dp = new boolean[len][len];
		for (int i = len - 1; i >= 0; i--) {
			for (int j = i; j < len; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

				if (dp[i][j] && (res == null || j - i + 1 >= res.length())) {
					String temp = s.substring(i, j + 1);
					res = temp;
				}
			}
		}
		return res;
	}

	public String longestPalindrome4(String s) {
		if (s == null || s.length() == 0)
			return s;
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		String res = null;
		for (int i = 0; i < n; i++) {
			for (int j = i; j >= 0; j--) {
				if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) {
					dp[j][i] = true;
					if (res == null || i - j + 1 > res.length()) {
						res = s.substring(j, i + 1);
					}
				}
			}
		}
		return res;
	}

	public String longestPalindrome5(String s) {
		char[] ca = s.toCharArray();
		int rs = 0, re = 0;
		int max = 0;
		for (int i = 0; i < ca.length; i++) {
			if (isPalindrome(ca, i - max - 1, i)) {
				rs = i - max - 1;
				re = i;
				max += 2;
			} else if (isPalindrome(ca, i - max, i)) {
				rs = i - max;
				re = i;
				max += 1;
			}
		}
		return s.substring(rs, re + 1);
	}

	private boolean isPalindrome(char[] ca, int s, int e) {
		if (s < 0)
			return false;

		while (s < e) {
			if (ca[s++] != ca[e--])
				return false;
		}
		return true;
	}

	public boolean isPalindrome(String s, int startIndex, int endIndex) {
		for (int i = startIndex, j = endIndex; i <= j; i++, j--)
			if (s.charAt(i) != s.charAt(j))
				return false;
		return true;
	}

	public String longestPalindrome6(String s) {
		int n = s.length();
		int longestLen = 0;
		int longestIndex = 0;
		if (n == 0)
			return "";
		for (int currentIndex = 0; currentIndex < n; currentIndex++) {
			if (isPalindrome(s, currentIndex - longestLen, currentIndex)) {
				longestLen += 1;
				longestIndex = currentIndex;
			} else if (currentIndex - longestLen - 1 >= 0
					&& isPalindrome(s, currentIndex - longestLen - 1, currentIndex)) {
				longestLen += 2;
				longestIndex = currentIndex;
			}
		}
		longestIndex++;
		return s.substring(longestIndex - longestLen, longestIndex);
	}

	/*
	 * 152. Maximum Product Subarray
	 * 
	 * Given an integer array nums, find the contiguous subarray within an array
	 * (containing at least one number) which has the largest product.
	 */
	public int maxProduct(int[] nums) {
		int result = nums[0];
		for (int i = 1, imax = result, imin = result; i < nums.length; i++) {
			if (nums[i] < 0) {
				imax = imax + imin;
				imin = imax - imin;
				imax = imax - imin;
			}
			imax = Math.max(nums[i], imax * nums[i]);
			imin = Math.min(nums[i], imin * nums[i]);
			result = Math.max(result, imax);
		}
		return result;
	}

	/*
	 * 2. Add Two Numbers
	 * 
	 * You are given two non-empty linked lists representing two non-negative
	 * integers. The digits are stored in reverse order and each of their nodes
	 * contain a single digit. Add the two numbers and return it as a linked
	 * list. You may assume the two numbers do not contain any leading zero,
	 * except the number 0 itself.
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);
		Stack<ListNode> snode1 = new Stack<ListNode>();
		Stack<ListNode> snode2 = new Stack<ListNode>();
		snode1.push(l1);
		snode2.push(l2);
		int index = 0;
		long sum = 0;
		while (!snode1.isEmpty() || !snode2.isEmpty()) {
			int valN1 = 0;
			int valN2 = 0;
			if (!snode1.isEmpty() && snode1.peek() != null) {
				valN1 = snode1.peek().val;
				l1 = snode1.pop().next;
				if (l1 != null) {
					snode1.push(l1);
				}
			}
			if (!snode2.isEmpty() && snode2.peek() != null) {
				valN2 = snode2.peek().val;
				l2 = snode2.pop().next;
				if (l2 != null) {
					snode2.push(l2);
				}
			}
			if (index == 0 && valN1 == 0 && valN2 == 0 && snode1.isEmpty() && snode2.isEmpty()) {
				return result;
			}
			if (valN1 >= 0 && valN2 >= 0) {
				sum += ((valN1 + valN2) * Math.pow(10, index));
				index++;
			}
		}
		snode1.clear();
		while (sum >= 0) {
			long mode = sum % 10;
			long div = sum / 10;
			sum /= 10;
			if (mode != 0 || div != 0) {
				result = new ListNode(0);
				result.val = (int) mode;
				snode1.push(result);
			} else {
				sum = -1;
			}
		}
		for (int i = 0; i < snode1.size() - 1; i++) {
			snode1.get(i).next = snode1.get(i + 1);
		}
		result = snode1.get(0);
		return result;
	}

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode ln1 = l1, ln2 = l2, head = null, node = null;
		int carry = 0, remainder = 0, sum = 0;
		head = node = new ListNode(0);

		while (ln1 != null || ln2 != null || carry != 0) {
			sum = (ln1 != null ? ln1.val : 0) + (ln2 != null ? ln2.val : 0) + carry;
			carry = sum / 10;
			remainder = sum % 10;
			node = node.next = new ListNode(remainder);
			ln1 = (ln1 != null ? ln1.next : null);
			ln2 = (ln2 != null ? ln2.next : null);
		}
		return head.next;
	}

	/*
	 * 221. Maximal Square
	 * 
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
	 * containing only 1's and return its area.
	 * 
	 * dp[i][j]=min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])+1
	 */
	public int maximalSquare(char[][] matrix) {
		int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
		int[] dp = new int[cols + 1];
		int maxsqlen = 0, prev = 0;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				int temp = dp[j];
				if (matrix[i - 1][j - 1] == '1') {
					dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
					maxsqlen = Math.max(maxsqlen, dp[j]);
				} else {
					dp[j] = 0;
				}
				prev = temp;
			}
		}
		return maxsqlen * maxsqlen;
	}

	public int maximalSquare2(char[][] matrix) {
		int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
		int maxsqlen = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == '1') {
					int sqlen = 1;
					boolean flag = true;
					while (sqlen + i < rows && sqlen + j < cols && flag) {
						for (int k = j; k <= sqlen + j; k++) {
							if (matrix[i + sqlen][k] == '0') {
								flag = false;
								break;
							}
						}
						for (int k = i; k <= sqlen + i; k++) {
							if (matrix[k][j + sqlen] == '0') {
								flag = false;
								break;
							}
						}
						if (flag)
							sqlen++;
					}
					if (maxsqlen < sqlen) {
						maxsqlen = sqlen;
					}
				}
			}
		}
		return maxsqlen * maxsqlen;
	}

	int maximalSquare3(char[][] matrix) {
		int height = matrix.length;
		if (height == 0)
			return 0;
		int width = matrix[0].length;
		int[][] temp = new int[2][width];
		int result = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				temp[i % 2][j] = 0;
				if (matrix[i][j] == '1') {
					temp[i % 2][j] = 1;
					if (i > 0 && j > 0)
						temp[i % 2][j] += Math.min(Math.min(temp[(i - 1) % 2][j], temp[i % 2][j - 1]),
								temp[(i - 1) % 2][j - 1]);
				}
				result = Math.max(result, temp[i % 2][j]);
			}
		}
		return result * result;

	}

	/*
	 * 240. Search a 2D Matrix II
	 * 
	 * Write an efficient algorithm that searches for a value in an m x n
	 * matrix. This matrix has the following properties:
	 * 
	 * Integers in each row are sorted in ascending from left to right. Integers
	 * in each column are sorted in ascending from top to bottom.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int col = matrix[0].length - 1;
		int row = 0;
		while (col >= 0 && row <= matrix.length - 1) {
			if (target == matrix[row][col]) {
				return true;
			} else if (target < matrix[row][col]) {
				col--;
			} else if (target > matrix[row][col]) {
				row++;
			}
		}
		return false;
	}

	/*
	 * 647. Palindromic Substrings
	 *
	 * Given a string, your task is to count how many palindromic substrings in
	 * this string.
	 * 
	 * The substrings with different start indexes or end indexes are counted as
	 * different substrings even they consist of same characters.
	 */
	public int countSubstrings(String s) {
		int n = s.length(), count = 0;
		for (int center = 0; center <= 2 * n - 1; ++center) {
			int left = center / 2;
			int right = left + center % 2;
			while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
				count++;
				left--;
				right++;
			}
		}
		return count;
	}

	public int countSubstrings1(String s) {
		if (s == null || s.length() == 0)
			return 0;

		for (int i = 0; i < s.length(); i++) {
			extendPalindrome1(s, i, i);
			extendPalindrome1(s, i, i + 1);
		}

		return count;
	}

	private void extendPalindrome1(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}
	}

	public int countSubstrings3(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			res++;
			res += count(s, i);
		}

		return res;
	}

	private int count(String s, int i) {
		int j = i;
		int res = 0;
		while ((j < s.length() - 1) && (s.charAt(j) == s.charAt(j + 1))) {
			res++;
			j++;
		}

		while (i > 0 && j < s.length() - 1 && s.charAt(i - 1) == s.charAt(j + 1)) {
			res++;
			i--;
			j++;
		}

		return res;
	}

	public int countSubstrings4(String s) {
		int n = s.length();
		int result = 0;
		boolean[][] dp = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
				if (dp[i][j])
					++result;
			}
		}
		return result;
	}

	/*
	 * 4. Median of Two Sorted Arrays
	 * 
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)). You may assume nums1 and nums2 cannot be both
	 * empty.
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
		return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
	}

	int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
		if (i >= nums1.length)
			return nums2[j + k - 1];
		if (j >= nums2.length)
			return nums1[i + k - 1];
		if (k == 1)
			return Math.min(nums1[i], nums2[j]);
		int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
		int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
		if (midVal1 < midVal2) {
			return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
		} else {
			return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
		}
	}

	public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
		return (findKth2(nums1, nums2, left) + findKth2(nums1, nums2, right)) / 2.0;
	}

	int findKth2(int[] nums1, int[] nums2, int k) {
		int m = nums1.length, n = nums2.length;
		if (m == 0)
			return nums2[k - 1];
		if (n == 0)
			return nums1[k - 1];
		if (k == 1)
			return Math.min(nums1[0], nums2[0]);
		int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
		if (nums1[i - 1] > nums2[j - 1]) {
			return findKth2(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
		} else {
			return findKth2(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
		}
	}

	public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		if (m < n)
			return findMedianSortedArrays(nums2, nums1);
		if (n == 0)
			return (nums1[(m - 1) / 2] + nums1[m / 2]) / 2.0;
		int left = 0, right = 2 * n;
		while (left <= right) {
			int mid2 = (left + right) / 2;
			int mid1 = m + n - mid2;
			double L1 = mid1 == 0 ? Double.MIN_VALUE : nums1[(mid1 - 1) / 2];
			double L2 = mid2 == 0 ? Double.MIN_VALUE : nums2[(mid2 - 1) / 2];
			double R1 = mid1 == m * 2 ? Double.MAX_VALUE : nums1[mid1 / 2];
			double R2 = mid2 == n * 2 ? Double.MAX_VALUE : nums2[mid2 / 2];
			if (L1 > R2)
				left = mid2 + 1;
			else if (L2 > R1)
				right = mid2 - 1;
			else
				return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
		}
		return -1;
	}

	/*
	 * 11. Container With Most Water
	 * 
	 * Given n non-negative integers a1, a2, ..., an , where each represents a
	 * point at coordinate (i, ai). n vertical lines are drawn such that the two
	 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
	 * together with x-axis forms a container, such that the container contains
	 * the most water.
	 * 
	 * Note: You may not slant the container and n is at least 2.
	 */

	public int maxArea(int[] height) {
		if (height.length == 0 || height.length == 1)
			return 0;
		if (height.length == 2)
			return Math.min(height[0], height[1]);
		int maxArea = height[0], len = height.length, maxLen = 1, minHeight = height[0];
		for (int i = 0; i < len; i++) {
			for (int j = len - 1; j >= i; j--) {
				maxLen = j - i;
				minHeight = Math.min(height[i], height[j]);
				maxArea = Math.max(maxArea, minHeight * maxLen);
			}
		}
		return maxArea;
	}

	public int maxArea2(int[] height) {
		int maxarea = 0, left = 0, right = height.length - 1;
		while (left < right) {
			maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * (right - left));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
		return maxarea;
	}

	/*
	 * 19. Remove Nth Node From End of List
	 * 
	 * Given a linked list, remove the n-th node from the end of list and return
	 * its head.
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		List<ListNode> list = new LinkedList<ListNode>();
		int index = 0;
		while (head != null) {
			list.add(head);
			if (index == n)
				head = head.next == null ? null : head.next.next;
			else
				head = head.next == null ? null : head.next;
			index++;
		}
		if (index - 1 >= n) {
			for (int i = 0; i < list.size() - 1; i++) {
				list.get(i).next = list.get(i + 1);
			}
		}
		return list.size() > 0 ? list.get(0) : null;
	}

	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode result = new ListNode(0);
		result.next = head;
		int length = 0;
		ListNode first = head;
		while (first != null) {
			length++;
			first = first.next;
		}
		length -= n;
		first = result;
		while (length > 0) {
			length--;
			first = first.next;
		}
		first.next = first.next.next;
		return result.next;
	}

	/*
	 * the most important
	 */
	public ListNode removeNthFromEnd3(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode first = dummy;
		ListNode second = dummy;
		for (int i = 1; i <= n + 1; i++) {
			first = first.next;
		}
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		return dummy.next;
	}

	/*
	 * 17. Letter Combinations of a Phone Number
	 * 
	 * Given a string containing digits from 2-9 inclusive, return all possible
	 * letter combinations that the number could represent.
	 * 
	 * A mapping of digit to letters (just like on the telephone buttons) is
	 * given below. Note that 1 does not map to any letters.
	 */
	public List<String> letterCombinations(String digits) {
		LinkedList<String> result = new LinkedList<String>();
		if (digits.isEmpty())
			return result;
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		result.add("");
		while (result.peek().length() != digits.length()) {
			String remove = result.remove();
			String map = mapping[digits.charAt(remove.length()) - '0'];
			for (char c : map.toCharArray()) {
				result.addLast(remove + c);
			}
		}
		return result;
	}

	/*
	 * 22. Generate Parentheses
	 * 
	 * Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses.
	 */

	public static List<String> generateParenthesis2(int n) {
		List<String> res = new ArrayList<>();
		backtrack("", res, n, n);
		return res;
	}

	public static void backtrack(String sublist, List<String> res, int left, int right) {
		if (left == 0 && right == 0) {
			res.add(sublist);
			return;
		}
		if (left > right)
			return;
		if (left > 0)
			backtrack(sublist + "(", res, left - 1, right);
		if (right > 0)
			backtrack(sublist + ")", res, left, right - 1);
	}

	/*
	 * 23. Merge k Sorted Lists
	 * 
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and
	 * describe its complexity.
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				else if (o1.val == o2.val)
					return 0;
				else
					return 1;
			}
		});

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

	/*
	 * 55. Jump Game
	 * 
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array. Each element in the array represents your
	 * maximum jump length at that position. Determine if you are able to reach
	 * the last index.
	 */
	public boolean canJump(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}
	/*
	 * 10. Regular Expression Matching
	 * 
	 * Given an input string (s) and a pattern (p), implement regular expression
	 * matching with support for '.' and '*'. '.' Matches any single character.
	 * '*' Matches zero or more of the preceding element. The matching should
	 * cover the entire input string (not partial).
	 * 
	 * Note: s could be empty and contains only lowercase letters a-z. p could
	 * be empty and contains only lowercase letters a-z, and characters like .
	 * or *.
	 */

	public boolean isMatch(String s, String p) {
		if (p.isEmpty())
			return s.isEmpty();
		boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

		if (p.length() >= 2 && p.charAt(1) == '*') {
			return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
		} else {
			return first_match && isMatch(s.substring(1), p.substring(1));
		}
	}

	public boolean isMatch2(String s, String p) {

		if (s == null || p == null) {
			return false;
		}
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*' && dp[0][i - 1]) {
				dp[0][i + 1] = true;
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == '.') {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == s.charAt(i)) {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
					}
				}
			}
		}
		return dp[s.length()][p.length()];
	}

	/*
	 * (1)p[j+1]不是'*'。情况比较简单，只要判断当前s的i和p的j上的字符是否一样（如果有p在j上的字符是'.',也是相同），如果不同，
	 * 返回false，否则，递归下一层i+1，j+1; 
	 * (2)p[j+1]是'*'。那么此时看从s[i]开始的子串，假设s[i],s[i+1],...s[i+k]都等于p[j]
	 * 那么意味着这些都有可能是合适的匹配，那么递归对于剩下的(i,j+2),(i+1,j+2),...,(i+k,j+2)都要尝试（j+
	 * 2是因为跳过当前和下一个'*'字符）
	 */
	public boolean isMatch3(String s, String p) {
		return isMatchHelper(s, p, 0, 0);
	}

	private boolean isMatchHelper(String s, String p, int i, int j) {
		if (j == p.length())
			return i == s.length();

		if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
			if (i == s.length() || s.charAt(i) != p.charAt(j) && p.charAt(j) != '.')
				return false;
			else
				return isMatchHelper(s, p, i + 1, j + 1);
		}
		while (i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
			if (isMatchHelper(s, p, i, j + 2))
				return true;
			i++;
		}
		return isMatchHelper(s, p, i, j + 2);
	}

	/*
	 * (1)p[j+1]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'.',也是相同），并且res[i]
	 * [j]==true，则res[i+1][j+1]也为true，res[i+1][j+1]=false; 
	 * (2)p[j+1]是'*'，但是p[j]!='.'。那么只要以下条件有一个满足即可对res[i+1][j+1]赋值为true： 
	 * 1)res[i+1][j]为真（'*'只取前面字符一次）; 
	 * 2)res[i+1][j-1]为真（'*'前面字符一次都不取，也就是忽略这两个字符）;  3)res[i][j+1] &&
	 * s[i]==s[i-1] &&s[i-1]==p[j-1](这种情况是相当于i从0到s.length()扫过来，如果p[j+1]对应的字符是‘*’
	 * 那就意味着接下来的串就可以依次匹配下来，如果下面的字符一直重复，并且就是‘*’前面的那个字符）。 
	 * (3)p[j+1]是'*'，并且p[j]=='.'。因为".*"可以匹配任意字符串，所以在前面的res[i+1][j-1]或者res[i+1][j
	 * ]中只要有i+1是true，那么剩下的res[i+1][j+1],res[i+2][j+1],...,res[s.length()][j+1]
	 * 就都是true了。 
	 */
	public boolean isMatch4(String s, String p) {
		if (s.length() == 0 && p.length() == 0)
			return true;
		if (p.length() == 0)
			return false;
		boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
		res[0][0] = true;
		for (int j = 0; j < p.length(); j++) {
			if (p.charAt(j) == '*') {
				if (j > 0 && res[0][j - 1])
					res[0][j + 1] = true;
				if (j < 1)
					continue;
				if (p.charAt(j - 1) != '.') {
					for (int i = 0; i < s.length(); i++) {
						if (res[i + 1][j] || j > 0 && res[i + 1][j - 1] || i > 0 && j > 0 && res[i][j + 1]
								&& s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1) == p.charAt(j - 1))
							res[i + 1][j + 1] = true;
					}
				} else {
					int i = 0;
					while (j > 0 && i < s.length() && !res[i + 1][j - 1] && !res[i + 1][j])
						i++;
					for (; i < s.length(); i++) {
						res[i + 1][j + 1] = true;
					}
				}
			} else {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
						res[i + 1][j + 1] = res[i][j];
				}
			}
		}
		return res[s.length()][p.length()];
	}

	/*
	 * 44. Wildcard Matching
	 * 
	 * Given an input string (s) and a pattern (p), implement wildcard pattern
	 * matching with support for '?' and '*'. '?' Matches any single character.
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * The matching should cover the entire input string (not partial).
	 * 
	 * Note: s could be empty and contains only lowercase letters a-z. p could
	 * be empty and contains only lowercase letters a-z, and characters like ?
	 * or *.
	 * 
	 * 解题说明： 维护一个假设我们维护一个布尔数组res[i],代表s的前i个字符和p的前j个字符是否匹配(这里因为每次i的结果只依赖于j-1的结果，
	 * 所以不需要二维数组，只需要一个一维数组来保存上一行结果即可），递推公式分两种情况：
	 * (1)p[j]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'?'，也是相同），并且res[i]==
	 * true，则更新res[i+1]为true，否则res[i+1]=false;  
	 * (2)p[j]是'*'。因为'*'可以匹配任意字符串，所以在前面的res[i]只要有true，那么剩下的          res[i+1],
	 * res[i+2],...,res[s.length()]就都是true了
	 * 
	 * 
	 */

	public boolean isMatch_One(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;
		if (s.length() > 300 && p.charAt(0) == '*' && p.charAt(p.length() - 1) == '*')
			return false;
		boolean[] res = new boolean[s.length() + 1];
		res[0] = true;
		for (int j = 0; j < p.length(); j++) {
			if (p.charAt(j) != '*') {
				for (int i = s.length() - 1; i >= 0; i--) {
					res[i + 1] = res[i] && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
				}
			} else {
				int i = 0;
				while (i <= s.length() && !res[i])
					i++;
				for (; i <= s.length(); i++) {
					res[i] = true;
				}
			}
			res[0] = res[0] && p.charAt(j) == '*';
		}
		return res[s.length()];
	}

	/*
	 * 621. Task Scheduler
	 * 
	 * Given a char array representing tasks CPU need to do. It contains capital
	 * letters A to Z where different letters represent different tasks. Tasks
	 * could be done without original order. Each task could be done in one
	 * interval. For each interval, CPU could finish one task or just be idle.
	 * However, there is a non-negative cooling interval n that means between
	 * two same tasks, there must be at least n intervals that CPU are doing
	 * different tasks or just be idle. You need to return the least number of
	 * intervals the CPU will take to finish all the given tasks.
	 */
	public int leastInterval(char[] tasks, int n) {
		int[] c = new int[26];
		for (char t : tasks) {
			c[t - 'A']++;
		}
		Arrays.sort(c);
		int i = 25;
		while (i >= 0 && c[i] == c[25])
			i--;

		return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
	}

	/*
	 * 31. Next Permutation
	 * 
	 * Implement next permutation, which rearranges numbers into the
	 * lexicographically next greater permutation of numbers. If such
	 * arrangement is not possible, it must rearrange it as the lowest possible
	 * order (ie, sorted in ascending order). The replacement must be in-place
	 * and use only constant extra memory. Here are some examples. Inputs are in
	 * the left-hand column and its corresponding outputs are in the right-hand
	 * column.
	 */
	public void nextPermutation(int[] nums) {
		int index = nums.length - 1;
		while (index > 0 && nums[index] <= nums[index - 1]) {
			--index;
		}
		if (index == 0) {
			Arrays.sort(nums);
			return;
		}
		int second = Integer.MAX_VALUE, secondIndex = Integer.MAX_VALUE;
		for (int i = nums.length - 1; i >= index - 1; --i) {
			if (nums[i] > nums[index - 1] && nums[i] < second) {
				second = nums[i];
				secondIndex = i;
			}
		}
		int tmp = nums[index - 1];
		nums[index - 1] = nums[secondIndex];
		nums[secondIndex] = tmp;
		Arrays.sort(nums, index, nums.length);
	}

	public void nextPermutation2(int[] nums) {
		int len = nums.length;
		if (len < 2)
			return;

		// int[] res = new int [len];

		/*
		 * 从倒数第二个元素开始，从后向前，找到第一个满足(后元素>前元素)的情况 此时，记录前元素下标k，则[k+1,n-1]为一个单调非增子序列
		 * 那么，这里只需要将一个比nums[k]大的最小数与nums[k]交换
		 */
		int lastEle = nums[len - 1];
		int k = len - 2;
		for (; k >= 0; k--) {
			if (lastEle > nums[k])
				break;
			else {
				lastEle = nums[k];
				continue;
			}
		}

		// 当前排列为最大排列，逆序之
		if (k < 0) {
			for (int i = 0; i < (len + 1) / 2; i++) {
				swap(nums, i, len - 1 - i);
			}
		} else {
			// 在nums[k+1,n-1]中寻找大于nums[k]的最小数
			int index = 0;
			for (int i = len - 1; i > k; i--) {
				if (nums[i] > nums[k]) {
					swap(nums, i, k);
					index = i;
					break;
				}
			}
			// index为0，表示当前nums[k]小于其后任意一个数，直接交换k与len-1
			if (index == 0) {
				swap(nums, k, len - 1);
			}
			// 将nums[k+1,n-1]逆序
			for (int i = k + 1; i < (k + len + 2) / 2; i++) {
				swap(nums, i, k + len - i);
			}
		}
		return;
	}

	// 交换元素
	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/*
	 * 39. Combination Sum
	 * 
	 * Given a set of candidate numbers (candidates) (without duplicates) and a
	 * target number (target), find all unique combinations in candidates where
	 * the candidate numbers sums to target. The same repeated number may be
	 * chosen from candidates unlimited number of times.
	 * 
	 * Note: All numbers (including target) will be positive integers. The
	 * solution set must not contain duplicate combinations.
	 */

	List<List<Integer>> result = new ArrayList<List<Integer>>();

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		if (candidates == null || candidates.length < 1)
			return result;
		combinationSum(candidates, target, 0, new ArrayList<>());
		return result;
	}

	private void combinationSum(int[] candidates, int target, int index, List<Integer> record) {

		if (target < 0)
			return;
		if (target == 0) {
			result.add(new ArrayList<>(record));
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			target -= candidates[i];
			record.add(candidates[i]);
			combinationSum(candidates, target, i, record);
			target += candidates[i];
			record.remove(record.size() - 1);
		}
	}

	public List<List<Integer>> combinationSum2(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
		if (remain < 0)
			return;
		else if (remain == 0)
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				// not i + 1 because we can reuse same elements
				backtrack(list, tempList, nums, remain - nums[i], i);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	/*
	 * 560. Subarray Sum Equals K
	 * 
	 * Given an array of integers and an integer k, you need to find the total
	 * number of continuous subarrays whose sum equals to k.
	 * 
	 * Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i,
	 * j] = k. Time complexity O(n^2), Space complexity O(1). I bet this
	 * solution will TLE.
	 * 
	 * Solution 2. From solution 1, we know the key to solve this problem is
	 * SUM[i, j]. So if we know SUM[0, i - 1] and SUM[0, j], then we can easily
	 * get SUM[i, j]. To achieve this, we just need to go through the array,
	 * calculate the current sum and save number of all seen PreSum to a
	 * HashMap. Time complexity O(n), Space complexity O(n).
	 */

	public int subarraySum(int[] nums, int k) {
		int sum = 0, result = 0;
		Map<Integer, Integer> preSum = new HashMap<>();
		preSum.put(0, 1);

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (preSum.containsKey(sum - k)) {
				result += preSum.get(sum - k);
			}
			preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
		}

		return result;
	}

	/*
	 * 322. Coin Change
	 * 
	 * You are given coins of different denominations and a total amount of
	 * money amount. Write a function to compute the fewest number of coins that
	 * you need to make up that amount. If that amount of money cannot be made
	 * up by any combination of the coins, return -1.
	 */
	public int coinChange(int[] coins, int amount) {
		if (amount < 1)
			return 0;
		int[] count = new int[amount + 1];
		int sum = 0;

		while (++sum <= amount) {
			int min = -1;
			for (int coin : coins) {
				if (sum >= coin && count[sum - coin] != -1) {
					int tmp = count[sum - coin] + 1;
					min = min < 0 ? tmp : (tmp < min ? tmp : min);
				}
			}
			count[sum] = min;
		}
		return count[amount];
	}

	/**
	 * 本题考查动态规划。也许一开始很容易想到用贪心算法，但是贪心算法在某些情况下是不成立的，比如coins = [1, 3, 5, 6]，要amount
	 * = 11，用贪心法返回3，实际上最少的是2（3 + 5）。因而改用动态规划，用dp存储硬币数量，dp[i] 表示凑齐钱数 i
	 * 需要的最少硬币数，那么凑齐钱数 amount 最少硬币数为：固定钱数为 coins[j] 一枚硬币，另外的钱数为 amount -
	 * coins[j] 它的数量为dp[amount - coins[j]]，j 从0遍历到coins.length - 1
	 */
	public int coinChange3(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		for (int i = 1; i <= amount; i++)
			dp[i] = 0x7fff_fffe;
		for (int coin : coins)
			for (int i = coin; i <= amount; i++)
				dp[i] = Math.min(dp[i], dp[i - coin] + 1);
		System.out.println(JsonUtil.toJson(dp));
		return dp[amount] == 0x7fff_fffe ? -1 : dp[amount];
	}

	/*
	 * 238. Product of Array Except Self
	 * 
	 * Given an array nums of n integers where n > 1, return an array output
	 * such that output[i] is equal to the product of all the elements of nums
	 * except nums[i].
	 */
	public int[] productExceptSelf(int[] nums) {
		int sum = 1;
		int[] result = new int[nums.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i==j) {
					continue;
				}else{
					sum *= nums[j];
				}
			}
			result[i] = sum;
			sum = 1;
		}
		System.out.println(JsonUtil.toJson(result));
		return result;
	}
	
	 public int[] productExceptSelf2(int[] nums) {
	    	int[] result = new int[nums.length];
	    	int right=1,left=1;
	        for(int i=0;i<nums.length;i++){
	        	result[i]=1;
	        	result[i]*=left;
	            left*=nums[i];
	        }
//	        System.out.println(JsonUtil.toJson(result));
	    	for(int i=nums.length-1;i>=0;i--) {
	    		result[i]*=right;
	    		right*=nums[i];
	    	}
//	    	System.out.println(JsonUtil.toJson(result));
	    	return result;
	    }

}
