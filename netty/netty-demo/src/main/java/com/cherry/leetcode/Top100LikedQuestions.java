package com.cherry.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

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
	 * 79. Word Search
	 * 
	 * The word can be constructed from letters of sequentially adjacent cell,
	 * where "adjacent" cells are those horizontally or vertically neighboring.
	 * The same letter cell may not be used more than once.
	 */
	public boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (exist(board, y, x, w, 0))
					return true;
			}
		}
		return false;
	}

	private boolean exist(char[][] board, int y, int x, char[] word, int i) {
		if (i == word.length)
			return true;
		if (y < 0 || x < 0 || y == board.length || x == board[y].length)
			return false;
		if (board[y][x] != word[i])
			return false;
		board[y][x] ^= 256;
		boolean exist = exist(board, y, x + 1, word, i + 1) || exist(board, y, x - 1, word, i + 1)
				|| exist(board, y + 1, x, word, i + 1) || exist(board, y - 1, x, word, i + 1);
		board[y][x] ^= 256;
		return exist;
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
				if (i == j) {
					continue;
				} else {
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
		int right = 1, left = 1;
		for (int i = 0; i < nums.length; i++) {
			result[i] = 1;
			result[i] *= left;
			left *= nums[i];
		}
		// System.out.println(JsonUtil.toJson(result));
		for (int i = nums.length - 1; i >= 0; i--) {
			result[i] *= right;
			right *= nums[i];
		}
		// System.out.println(JsonUtil.toJson(result));
		return result;
	}

	/*
	 * 416. Partition Equal Subset Sum(理解力度不够，需要继续深究)
	 * 
	 * Given a non-empty array containing only positive integers, find if the
	 * array can be partitioned into two subsets such that the sum of elements
	 * in both subsets is equal.
	 * 
	 * Note: Each of the array element will not exceed 100. The array size will
	 * not exceed 200.
	 */
	public boolean canPartition(int[] nums) {
		if (nums == null || nums.length == 0) {
			return true;
		}
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % 2 != 0) {
			return false;
		}
		sum /= 2;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		for (int i = 1; i <= nums.length; i++) {
			for (int j = sum; j >= nums[i - 1]; j--) {
				dp[j] = dp[j] || dp[j - nums[i - 1]];
			}
		}
		return dp[sum];
	}

	public boolean canPartition2(int[] nums) {
		int sum = 0;
		for (int num : nums)
			sum += num;
		if (sum % 2 == 1)
			return false;

		int target = sum / 2;
		boolean[][] dp = new boolean[nums.length][target + 1];
		// deal with the first row
		if (nums[0] <= target)
			dp[0][nums[0]] = true;

		// deal with the first col
		for (int i = 0; i < nums.length; i++)
			dp[i][0] = true;

		// deal with the rest
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (j < nums[i]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
				}
			}
		}
		System.out.println(JsonUtil.toJson(dp));
		return dp[dp.length - 1][dp[0].length - 1];
	}

	public boolean canPartition3(int[] nums) {
		int sum = 0;
		for (int num : nums)
			sum += num;
		if (sum % 2 == 1)
			return false;
		else {
			sum /= 2;
			int n = nums.length;
			// dp[i][j] 表示 如果我们取前i个数字，且背包容量为j的情况下，最多能放入多少东西
			int dp[][] = new int[n][sum + 1];
			// dp[0][0] 为初始状态，表示，没有任何没有东西没有体积，其余部分初始化
			for (int i = nums[0]; i <= sum; i++) {
				dp[0][i] = nums[0];
			}
			// 遍历n个数字，即视为n个产品
			for (int i = 1; i < n; i++) {
				// 加入了这种物品后更新状态
				for (int j = nums[i]; j <= sum; j++) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
				}
			}
			printArrays(dp, n);
			// 放满了才能表示正好1/2
			if (dp[n - 1][sum] == sum)
				return true;
			else
				return false;
		}
	}

	private void printArrays(int[][] arrays, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(JsonUtil.toJson(arrays[i]));
		}
	}

	/**
	 * 394. Decode String
	 * 
	 * Given an encoded string, return it's decoded string.
	 * 
	 * The encoding rule is: k[encoded_string], where the encoded_string inside
	 * the square brackets is being repeated exactly k times. Note that k is
	 * guaranteed to be a positive integer.
	 * 
	 * You may assume that the input string is always valid; No extra white
	 * spaces, square brackets are well-formed, etc.
	 * 
	 * Furthermore, you may assume that the original data does not contain any
	 * digits and that digits are only for those repeat numbers, k. For example,
	 * there won't be input like 3a or 2[4].
	 */
	public String decodeString(String s) {
		StringBuffer result = new StringBuffer();
		int left = 0, right = 0, count = 1, intChar = 48;
		for (int i = s.length() - 1; i >= 0; i--) {
			intChar = s.charAt(i);
			if (intChar > 48 && 58 > intChar) {
				continue;
			} else {
				if (s.charAt(i) == ']') {
					right = i;
					continue;
				} else if (s.charAt(i) == '[') {
					left = i + 1;
					count = Integer.valueOf(s.charAt(i - 1)) - 48;
				} else {
					result.insert(0, s.charAt(i));
				}
			}
			if (right > 0 && left > 0) {
				String sStr = s.substring(left, right);
				for (int j = 0; j < count - 1; j++) {
					result.insert(0, sStr);
				}
				right = 0;
				left = 0;
			}
		}
		return result.toString();
	}

	public String decodeString2(String s) {
		String res = "";
		Stack<Integer> countStack = new Stack<>();
		Stack<String> resStack = new Stack<>();
		int idx = 0;
		while (idx < s.length()) {
			if (Character.isDigit(s.charAt(idx))) {
				int count = 0;
				while (Character.isDigit(s.charAt(idx))) {
					count = 10 * count + (s.charAt(idx) - '0');
					idx++;
				}
				countStack.push(count);
			} else if (s.charAt(idx) == '[') {
				resStack.push(res);
				res = "";
				idx++;
			} else if (s.charAt(idx) == ']') {
				StringBuilder temp = new StringBuilder(resStack.pop());
				int repeatTimes = countStack.pop();
				for (int i = 0; i < repeatTimes; i++) {
					temp.append(res);
				}
				res = temp.toString();
				idx++;
			} else {
				res += s.charAt(idx++);
			}
		}
		return res;
	}

	/*
	 * 347. Top K Frequent Elements
	 * 
	 * Given a non-empty array of integers, return the k most frequent elements.
	 * 
	 * Example 1: Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] Example 2:
	 * Input: nums = [1], k = 1 Output: [1]
	 */
	public List<Integer> topKFrequent(int[] nums, int k) {
		// build hash map : character and how often it appears
		HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
		for (int n : nums) {
			count.put(n, count.getOrDefault(n, 0) + 1);
		}

		// init heap 'the less frequent element first'
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

		// keep k top frequent elements in the heap
		for (int n : count.keySet()) {
			heap.add(n);
			if (heap.size() > k)
				heap.poll();
		}

		// build output list
		List<Integer> top_k = new LinkedList<Integer>();
		while (!heap.isEmpty())
			top_k.add(heap.poll());
		Collections.reverse(top_k);
		return top_k;
	}

	/*
	 * 桶排序Bucket Sort， Time: O(n), Space: O(n) 1. 遍历数组nums，利用Hash
	 * map统计每个数字出现的次数。 2. 遍历map，初始化一个行数为len(nums) + 1的二维数组，将出现次数为i ( i∈[1, n]
	 * )的所有数字加到第i行。 3. 逆序遍历二维数组(从频率高的开始)，将其中的前k行的元素输出。
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> topKFrequent2(int[] nums, int k) {
		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}
		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}
		List<Integer> res = new ArrayList<>();
		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}

	/*
	 * 利用Java中的TreeMap， Tree map是一个有序的key-value集合，它是通过红黑树实现的。利用map可统计，又是按key排序的
	 */
	public List<Integer> topKFrequent3(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
		for (int num : map.keySet()) {
			int freq = map.get(num);
			if (!freqMap.containsKey(freq)) {
				freqMap.put(freq, new LinkedList<>());
			}
			freqMap.get(freq).add(num);
		}
		List<Integer> result = new ArrayList<>();
		while (result.size() < k) {
			// Map.Entry<Integer, List<Integer>> entry =
			// freqMap.pollLastEntry();
			result.addAll(freqMap.pollLastEntry().getValue());
		}
		return result;
	}

	/*
	 * 148. Sort List
	 * 
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 * 
	 * Input: 4->2->1->3 Output: 1->2->3->4
	 * 
	 * Input: -1->5->3->4->0 Output: -1->0->3->4->5
	 * 
	 */
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode prev = null, slow = head, fast = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);
		return mergeTwoLists(l1, l2);
	}

	ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		for (ListNode p = dummy; l1 != null || l2 != null; p = p.next) {
			int val1 = l1 == null ? Integer.MAX_VALUE : l1.val;
			int val2 = l2 == null ? Integer.MAX_VALUE : l2.val;
			if (val1 <= val2) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
		}
		return dummy.next;
	}

	/*
	 * 94. Binary Tree Inorder Traversal
	 * 
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 * Input: [1,null,2,3] Output: [1,3,2]
	 */
	List<Integer> inorderresult = new ArrayList<Integer>();

	public List<Integer> inorderTraversal(TreeNode root) {
		inOrderTreeNode(root);
		return inorderresult;
	}

	public void inOrderTreeNode(TreeNode node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			inOrderTreeNode(node.left);
		}
		inorderresult.add(node.val);
		if (node.right != null) {
			inOrderTreeNode(node.right);
		}
	}

	/*
	 * 46. Permutations
	 * 
	 * Given a collection of distinct integers, return all possible
	 * permutations.
	 * 
	 * Input: [1,2,3] Output:[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		dfs(res, nums, 0);
		return res;
	}

	private void dfs(List<List<Integer>> res, int[] nums, int j) {
		if (j == nums.length) {
			List<Integer> temp = new ArrayList<Integer>();
			for (int num : nums)
				temp.add(num);
			res.add(temp);
		}
		for (int i = j; i < nums.length; i++) {
			swapElements(nums, i, j);
			dfs(res, nums, j + 1);
			swapElements(nums, i, j);
		}
	}

	private void swapElements(int[] nums, int m, int n) {
		int temp = nums[m];
		nums[m] = nums[n];
		nums[n] = temp;
	}

	public List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums.length == 0 || nums == null)
			return res;
		List<Integer> list = new ArrayList<Integer>();
		list.add(nums[0]); // Add the first element into the list;
		res.add(list);
		for (int i = 1; i < nums.length; i++) {
			// Keep track of the size of current result;
			int size = res.size();
			for (int j = 0; j < size; j++) {
				int size2 = res.get(0).size();
				for (int k = 0; k <= size2; k++) {
					List<Integer> temp = new ArrayList<>(res.get(0));
					temp.add(k, nums[i]);
					res.add(temp);
				}
				res.remove(0);
			}
		}
		return res;
	}

	/*
	 * 78. Subsets Given a set of distinct integers, nums, return all possible
	 * subsets (the power set). Note: The solution set must not contain
	 * duplicate subsets.
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		backtrack(result, new ArrayList<>(), nums, 0);
		return result;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	/*
	 * 64. Minimum Path Sum
	 * 
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its
	 * path.
	 * 
	 * Note: You can only move either down or right at any point in time.
	 */
	public int minPathSum(int[][] grid) {
		for (int i = 1; i < grid.length; i++)
			grid[i][0] += grid[i - 1][0];
		for (int j = 1; j < grid[0].length; j++)
			grid[0][j] += grid[0][j - 1];
		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length; j++) {
				grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
			}
		}
		return grid[grid.length - 1][grid[0].length - 1];
	}

	public int minPathSum2(int[][] grid) {
		int[] dp = new int[grid.length];
		dp[0] = grid[0][0];
		for (int i = 1; i < grid.length; i++)
			dp[i] = grid[i][0] + dp[i - 1];
		for (int j = 1; j < grid[0].length; j++)
			for (int i = 0; i < grid.length; i++)
				dp[i] = (i == 0 ? dp[i] : Math.min(dp[i], dp[i - 1])) + grid[i][j];

		return dp[grid.length - 1];
	}

	/*
	 * 287. Find the Duplicate Number
	 * 
	 * Given an array nums containing n + 1 integers where each integer is
	 * between 1 and n (inclusive), prove that at least one duplicate number
	 * must exist. Assume that there is only one duplicate number, find the
	 * duplicate one. Note: You must not modify the array (assume the array is
	 * read only). You must use only constant, O(1) extra space. Your runtime
	 * complexity should be less than O(n2). There is only one duplicate number
	 * in the array, but it could be repeated more than once.
	 */
	public int findDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			if (set.contains(num)) {
				return num;
			} else {
				set.add(num);
			}
		}
		return -1;
	}

	public int findDuplicate1(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				return nums[i];
			}
		}
		return -1;
	}

	public int findDuplicate3(int[] nums) {
		int tortoise = nums[0];
		int hare = nums[0];
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		int ptr1 = nums[0];
		int ptr2 = tortoise;
		while (ptr1 != ptr2) {
			ptr1 = nums[ptr1];
			ptr2 = nums[ptr2];
		}
		return ptr1;
	}

	/*
	 * 102. Binary Tree Level Order Traversal
	 * 
	 * Given a binary tree, return the level order traversal of its nodes'
	 * values. (ie, from left to right, level by level).
	 * 
	 * 
	 * 3 / \ 9 20 / \ 15 7
	 * 
	 * [[3],[9,20],[15,7]]
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> levelResult = new LinkedList<List<Integer>>();
		if (root == null)
			return levelResult;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for (int i = 0; i < levelNum; i++) {
				if (queue.peek().left != null)
					queue.offer(queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				subList.add(queue.poll().val);
			}
			levelResult.add(subList);
		}
		return levelResult;
	}

	public List<List<Integer>> levelOrder2(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		levelHelper(root, 0, result);
		return result;
	}

	void levelHelper(TreeNode root, int height, List<List<Integer>> res) {
		if (root == null)
			return;
		if (height >= res.size()) {
			res.add(new LinkedList<Integer>());
		}
		res.get(height).add(root.val);
		levelHelper(root.left, height + 1, res);
		levelHelper(root.right, height + 1, res);
	}

	/*
	 * 337. House Robber III
	 * 
	 * The thief has found himself a new place for his thievery again. There is
	 * only one entrance to this area, called the "root." Besides the root, each
	 * house has one and only one parent house. After a tour, the smart thief
	 * realized that "all houses in this place forms a binary tree". It will
	 * automatically contact the police if two directly-linked houses were
	 * broken into on the same night. Determine the maximum amount of money the
	 * thief can rob tonight without alerting the police.
	 */
	public int rob(TreeNode root) {
		int[] num = dfs(root);
		return Math.max(num[0], num[1]);
	}

	private int[] dfs(TreeNode x) {
		if (x == null)
			return new int[2];
		int[] left = dfs(x.left);
		int[] right = dfs(x.right);
		int[] res = new int[2];
		res[0] = left[1] + right[1] + x.val;
		res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return res;
	}

	/*
	 * 48. Rotate Image
	 * 
	 * You are given an n x n 2D matrix representing an image. Rotate the image
	 * by 90 degrees (clockwise).
	 * 
	 * Note:You have to rotate the image in-place, which means you have to
	 * modify the input 2D matrix directly. DO NOT allocate another 2D matrix
	 * and do the rotation.
	 */
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++)
			for (int j = i; j < n - i - 1; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n - j - 1][i];
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
				matrix[j][n - i - 1] = tmp;
			}
	}

	/*
	 * 215. Kth Largest Element in an Array
	 * 
	 * Find the kth largest element in an unsorted array. Note that it is the
	 * kth largest element in the sorted order, not the kth distinct element.
	 */
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	/*
	 * 96. Unique Binary Search Trees
	 * 
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values 1 ... n?
	 */
	public int numTrees(int n) {
		int[] G = new int[n + 1];
		G[0] = G[1] = 1;

		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {
				G[i] += G[j - 1] * G[i - j];
			}
		}

		return G[n];
	}

	public int numTrees2(int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;

		int result[] = new int[n + 1];
		result[0] = 1;
		result[1] = 1;
		result[2] = 2;
		if (n < 3) {
			return result[n];
		}

		for (int i = 3; i <= n; i++) {
			for (int k = 1; k <= i; k++) {

				result[i] = result[i] + result[k - 1] * result[i - k];
			}
		}

		return result[n];
	}

	/*
	 * 49. Group Anagrams
	 * 
	 * Given an array of strings, group anagrams together.
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0)
			return new ArrayList<List<String>>();
		Map<String, List<String>> ans = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String key = String.valueOf(ca);
			if (!ans.containsKey(key))
				ans.put(key, new ArrayList<String>());
			ans.get(key).add(s);
		}
		return new ArrayList<List<String>>(ans.values());
	}

	public List<List<String>> groupAnagrams2(String[] strs) {
		int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
				103 };

		List<List<String>> res = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (String s : strs) {
			int key = 1;
			for (char c : s.toCharArray()) {
				key *= prime[c - 'a'];
			}
			List<String> t;
			if (map.containsKey(key)) {
				t = res.get(map.get(key));
			} else {
				t = new ArrayList<>();
				res.add(t);
				map.put(key, res.size() - 1);
			}
			t.add(s);
		}
		return res;
	}

	/*
	 * 75. Sort Colors
	 * 
	 * Given an array with n objects colored red, white or blue, sort them
	 * in-place so that objects of the same color are adjacent, with the colors
	 * in the order red, white and blue. Here, we will use the integers 0, 1,
	 * and 2 to represent the color red, white, and blue respectively. Note: You
	 * are not suppose to use the library's sort function for this problem.
	 */
	public void sortColors(int[] nums) {
		Arrays.sort(nums);
	}

	/*
	 * 309. Best Time to Buy and Sell Stock with Cooldown
	 * 
	 * Say you have an array for which the ith element is the price of a given
	 * stock on day i. Design an algorithm to find the maximum profit. You may
	 * complete as many transactions as you like (ie, buy one and sell one share
	 * of the stock multiple times) with the following restrictions: You may not
	 * engage in multiple transactions at the same time (ie, you must sell the
	 * stock before you buy again). After you sell your stock, you cannot buy
	 * stock on next day. (ie, cooldown 1 day)
	 */
	public int maxProfit3(int[] prices) {
		int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
		for (int price : prices) {
			prev_buy = buy;
			buy = Math.max(prev_sell - price, prev_buy);
			prev_sell = sell;
			sell = Math.max(prev_buy + price, prev_sell);
		}
		return sell;
	}

	/*
	 * 114. Flatten Binary Tree to Linked List
	 * 
	 * Given a binary tree, flatten it to a linked list in-place.
	 */
	private TreeNode prev = null;

	public void flatten(TreeNode root) {
		if (root == null)
			return;
		flatten(root.right);
		flatten(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
		System.out.println(JsonUtil.toJson(root));
	}

	void flatten2(TreeNode root) {
		TreeNode now = root;
		while (now != null) {
			if (now.left != null) {
				TreeNode pre = now.left;
				while (pre.right != null) {
					pre = pre.right;
				}
				// 将当前节点的右孩子连接到当前节点左孩子的的最右孩子
				pre.right = now.right;

				// 当前节点的左孩子变为右孩子，左孩子置为NULL
				now.right = now.left;
				now.left = null;
			}
			now = now.right;
		}
	}

	/*
	 * 279. Perfect Squares
	 * 
	 * Given a positive integer n, find the least number of perfect square
	 * numbers (for example, 1, 4, 9, 16, ...) which sum to n.
	 */
	public int numSquares(int n) {
		int n1 = (int) (Math.sqrt(n));
		if (n1 * n1 == n) {
			return 1;
		}

		while ((n & 3) == 0) // n%4 == 0
		{
			n >>= 2;
		}
		if ((n & 7) == 7) // n%8 == 7
		{
			return 4;
		}

		int sqrt_n = (int) (Math.sqrt(n));
		for (int i = 1; i <= sqrt_n; i++) {
			n1 = (int) (Math.sqrt(n - i * i));
			if (n1 * n1 == (n - i * i)) {
				return 2;
			}
		}

		return 3;
	}

	public int numSquares2(int n) {
		int[] record = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			record[i] = i;
			for (int j = 1; j * j <= i; j++) {
				record[i] = Math.min(record[i - j * j] + 1, record[i]);
			}
		}
		return record[n];
	}

	/*
	 * 200. Number of Islands
	 * 
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
	 * islands. An island is surrounded by water and is formed by connecting
	 * adjacent lands horizontally or vertically. You may assume all four edges
	 * of the grid are all surrounded by water.
	 */
	int n, m;

	public int numIslands(char[][] grid) {
		int count = 0;
		n = grid.length;
		if (n == 0)
			return 0;
		m = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				if (grid[i][j] == '1') {
					DFSMarking(grid, i, j);
					++count;
				}
		}
		return count;
	}

	private void DFSMarking(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1')
			return;
		grid[i][j] = '0';
		DFSMarking(grid, i + 1, j);
		DFSMarking(grid, i - 1, j);
		DFSMarking(grid, i, j + 1);
		DFSMarking(grid, i, j - 1);
	}

	public int numIslands2(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					bfsFill(grid, i, j);
					count++;
				}
			}
		return count;
	}

	private void bfsFill(char[][] grid, int x, int y) {
		grid[x][y] = '0';
		int n = grid.length;
		int m = grid[0].length;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int code = x * m + y;
		queue.offer(code);
		while (!queue.isEmpty()) {
			code = queue.poll();
			int i = code / m;
			int j = code % m;
			if (i > 0 && grid[i - 1][j] == '1') // search upward and mark
												// adjacent '1's as '0'.
			{
				queue.offer((i - 1) * m + j);
				grid[i - 1][j] = '0';
			}
			if (i < n - 1 && grid[i + 1][j] == '1') // down
			{
				queue.offer((i + 1) * m + j);
				grid[i + 1][j] = '0';
			}
			if (j > 0 && grid[i][j - 1] == '1') // left
			{
				queue.offer(i * m + j - 1);
				grid[i][j - 1] = '0';
			}
			if (j < m - 1 && grid[i][j + 1] == '1') // right
			{
				queue.offer(i * m + j + 1);
				grid[i][j + 1] = '0';
			}
		}
	}

	/*
	 * 33. Search in Rotated Sorted Array
	 * 
	 * Suppose an array sorted in ascending order is rotated at some pivot
	 * unknown to you beforehand.
	 * 
	 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
	 * 
	 * You are given a target value to search. If found in the array return its
	 * index, otherwise return -1.
	 * 
	 * You may assume no duplicate exists in the array.
	 * 
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 */
	public int search(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (target == nums[i]) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * 312. Burst Balloons
	 * 
	 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
	 * number on it represented by array nums. You are asked to burst all the
	 * balloons. If the you burst balloon i you will get nums[left] * nums[i] *
	 * nums[right] coins. Here left and right are adjacent indices of i. After
	 * the burst, the left and right then becomes adjacent.
	 * 
	 * Find the maximum coins you can collect by bursting the balloons wisely.
	 * 
	 * Note:
	 * 
	 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you
	 * can not burst them. 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
	 */
	public int maxCoins(int[] nums) {
		int sum = 0;
		List<Integer> list = new ArrayList<Integer>();
		for (int num : nums)
			list.add(num);

		while (list.size() > 0) {
			if (list.size() == 0) {
				return sum;
			} else if (list.size() == 1) {
				sum += 1 * list.get(0) * 1;
				list.remove(list.get(0));
			} else if (list.size() == 2) {
				sum += 1 * list.get(0) * list.get(1);
				list.remove(list.get(0));
			} else {
				sum += list.get(0) * list.get(1) * list.get(2);
				list.remove(1);
			}

		}
		return sum;
	}

	public int maxCoins2(int[] nums) {
		int[] inums = new int[nums.length + 2];
		int n = 1;
		for (int x : nums)
			if (x > 0)
				inums[n++] = x;
		inums[0] = inums[n++] = 1;

		int[][] dp = new int[n][n];
		for (int k = 2; k < n; ++k)
			for (int left = 0; left < n - k; ++left) {
				int right = left + k;
				for (int i = left + 1; i < right; ++i)
					dp[left][right] = Math.max(dp[left][right],
							inums[left] * inums[i] * inums[right] + dp[left][i] + dp[i][right]);
			}

		return dp[0][n - 1];
	}

	/*
	 * 34. Find First and Last Position of Element in Sorted Array
	 * 
	 * Given an array of integers nums sorted in ascending order, find the
	 * starting and ending position of a given target value.
	 * 
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * 
	 * If the target is not found in the array, return [-1, -1].
	 */
	public int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				result[0] = result[0] == -1 ? i : result[0];
				result[1] = result[1] == -1 ? i : (result[1] < i ? i : result[1]);
			}
		}
		return result;
	}

	private int extremeInsertionIndex(int[] nums, int target, boolean left) {
		int lo = 0;
		int hi = nums.length;

		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] > target || (left && target == nums[mid])) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}

		return lo;
	}

	public int[] searchRange2(int[] nums, int target) {
		int[] targetRange = { -1, -1 };

		int leftIdx = extremeInsertionIndex(nums, target, true);

		if (leftIdx == nums.length || nums[leftIdx] != target) {
			return targetRange;
		}

		targetRange[0] = leftIdx;
		targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

		return targetRange;
	}

	/*
	 * 56. Merge Intervals
	 * 
	 * Given a collection of intervals, merge all overlapping intervals. Input:
	 * [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
	 */
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<Interval>();
		if (intervals.size() == 0) {
			return result;
		} else if (intervals.size() == 1) {
			result.add(intervals.get(0));
			return result;
		}
		Interval temp = intervals.get(0);
		for (int i = 0; i < intervals.size(); i++) {
			if (temp.end >= intervals.get(i).start) {
				result.add(new Interval(temp.start, intervals.get(i).end));
				temp = intervals.get(i);
			} else {
				result.add(intervals.get(i - 1));
			}
		}
		return result;
	}

	public List<Interval> merge2(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;

		intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

		List<Interval> result = new LinkedList<Interval>();
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;

		for (Interval interval : intervals) {
			if (interval.start <= end)
				end = Math.max(end, interval.end);
			else {
				result.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}

		result.add(new Interval(start, end));
		return result;
	}

	public List<Interval> merge3(List<Interval> intervals) {
		int n = intervals.size();
		int[] starts = new int[n];
		int[] ends = new int[n];
		for (int i = 0; i < n; i++) {
			starts[i] = intervals.get(i).start;
			ends[i] = intervals.get(i).end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		List<Interval> res = new ArrayList<Interval>();
		for (int i = 0, j = 0; i < n; i++) {
			if (i == n - 1 || starts[i + 1] > ends[i]) {
				res.add(new Interval(starts[j], ends[i]));
				j = i + 1;
			}
		}
		return res;
	}

	/*
	 * 105. Construct Binary Tree from Preorder and Inorder Traversal
	 * 
	 * Given preorder and inorder traversal of a tree, construct the binary
	 * tree.
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}

		TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
		return root;
	}

	public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> inMap) {
		if (preStart > preEnd || inStart > inEnd)
			return null;

		TreeNode root = new TreeNode(preorder[preStart]);
		int inRoot = inMap.get(root.val);
		int numsLeft = inRoot - inStart;

		root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
		root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

		return root;
	}

	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);
		int inIndex = 0; // Index of current root in inorder
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
		return root;
	}

	/*
	 * 47. Permutations II
	 * 
	 * Given a collection of numbers that might contain duplicates, return all
	 * possible unique permutations.
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {

		return null;
	}

	/*
	 * 139. Word Break
	 * 
	 * Given a non-empty string s and a dictionary wordDict containing a list of
	 * non-empty words, determine if s can be segmented into a space-separated
	 * sequence of one or more dictionary words.
	 * 
	 * Note:
	 * 
	 * The same word in the dictionary may be reused multiple times in the
	 * segmentation. You may assume the dictionary does not contain duplicate
	 * words.
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		boolean[] f = new boolean[s.length() + 1];

		f[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (f[j] && wordDict.contains(s.substring(j, i))) {
					f[i] = true;
					break;
				}
			}
		}

		return f[s.length()];
	}

	public boolean wordBreak2(String s, List<String> wordDict) {
		// DFS
		Set<Integer> set = new HashSet<Integer>();
		return dfs(s, 0, wordDict, set);
	}

	private boolean dfs(String s, int index, List<String> dict, Set<Integer> set) {
		// base case
		if (index == s.length())
			return true;
		// check memory
		if (set.contains(index))
			return false;
		// recursion
		for (int i = index + 1; i <= s.length(); i++) {
			String t = s.substring(index, i);
			if (dict.contains(t))
				if (dfs(s, i, dict, set))
					return true;
				else
					set.add(i);
		}
		set.add(index);
		return false;
	}

	/*
	 * 208. Implement Trie (Prefix Tree)
	 * 
	 * Implement a trie with insert, search, and startsWith methods.
	 */
	class Trie {

		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if (!node.containsKey(currentChar)) {
					node.put(currentChar, new TrieNode());
				}
				node = node.get(currentChar);
			}
			node.setEnd();
		}

		private TrieNode searchPrefix(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char curLetter = word.charAt(i);
				if (node.containsKey(curLetter)) {
					node = node.get(curLetter);
				} else {
					return null;
				}
			}
			return node;
		}

		// Returns if the word is in the trie.
		public boolean search(String word) {
			TrieNode node = searchPrefix(word);
			return node != null && node.isEnd();
		}

		// Returns if there is any word in the trie
		// that starts with the given prefix.
		public boolean startsWith(String prefix) {
			TrieNode node = searchPrefix(prefix);
			return node != null;
		}

	}

	public class Trie2 {
		private TrieNode2 root;

		public Trie2() {
			root = new TrieNode2();
			root.val = ' ';
		}

		public void insert(String word) {
			TrieNode2 ws = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (ws.children[c - 'a'] == null) {
					ws.children[c - 'a'] = new TrieNode2(c);
				}
				ws = ws.children[c - 'a'];
			}
			ws.isWord = true;
		}

		public boolean search(String word) {
			TrieNode2 ws = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (ws.children[c - 'a'] == null)
					return false;
				ws = ws.children[c - 'a'];
			}
			return ws.isWord;
		}

		public boolean startsWith(String prefix) {
			TrieNode2 ws = root;
			for (int i = 0; i < prefix.length(); i++) {
				char c = prefix.charAt(i);
				if (ws.children[c - 'a'] == null)
					return false;
				ws = ws.children[c - 'a'];
			}
			return true;
		}
	}

	/*
	 * 42. Trapping Rain Water
	 * 
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 */
	public int trap(int[] height) {
		int left = 0, right = height.length - 1;
		int ans = 0;
		int left_max = 0, right_max = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				if (height[left] >= left_max)
					left_max = height[left];
				else
					ans += (left_max - height[left]);
				++left;
			} else {
				if (height[right] >= right_max)
					right_max = height[right];
				else
					ans += (right_max - height[right]);
				--right;
			}
		}
		return ans;
	}

	/*
	 * 76. Minimum Window Substring
	 * 
	 * Given a string S and a string T, find the minimum window in S which will
	 * contain all the characters in T in complexity O(n).
	 * 
	 * Example:
	 * 
	 * Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC" Note:
	 * 
	 * If there is no such window in S that covers all characters in T, return
	 * the empty string "". If there is such window, you are guaranteed that
	 * there will always be only one unique minimum window in S.
	 */
	public String minWindow(String s, String t) {
		int[] arr = new int[128];
		for (int i = 0; i < t.length(); i++) {
			arr[t.charAt(i)]++;
		}

		int counter = t.length(), left = 0, right = 0, window = Integer.MAX_VALUE, head = -1;
		while (right < s.length()) {
			char rc = s.charAt(right++);
			if (arr[rc] > 0) {
				counter--;
			}
			arr[rc]--;

			while (counter == 0) {
				if (right - left < window) {
					window = right - (head = left);
				}

				char lc = s.charAt(left++);
				if (arr[lc] == 0) {
					counter++;
				}
				arr[lc]++;
			}
		}
		return head == -1 ? "" : s.substring(head, head + window);
	}

	/*
	 * 239. Sliding Window Maximum
	 * 
	 * Given an array nums, there is a sliding window of size k which is moving
	 * from the very left of the array to the very right. You can only see the k
	 * numbers in the window. Each time the sliding window moves right by one
	 * position. Return the max sliding window.
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0 || (k <= 0 && k > nums.length)) {
			return new int[0];
		}
		int[] result = new int[nums.length - k + 1];
		for (int i = 0; i < nums.length - k + 1; i++) {
			int maxNum = Integer.MIN_VALUE;
			for (int j = i; j < i + k; j++) {
				maxNum = Math.max(maxNum, nums[j]);
			}
			result[i] = maxNum;
		}
		return result;
	}

	public int[] maxSlidingWindow2(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return new int[0];
		}
		int n = nums.length;
		int[] r = new int[n - k + 1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<Integer>();
		for (int i = 0; i < nums.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = nums[q.peek()];
			}
		}
		return r;
	}

	/*
	 * 124. Binary Tree Maximum Path Sum
	 * 
	 * Given a non-empty binary tree, find the maximum path sum.
	 * 
	 * For this problem, a path is defined as any sequence of nodes from some
	 * starting node to any node in the tree along the parent-child connections.
	 * The path must contain at least one node and does not need to go through
	 * the root.
	 */
	int maxValue;

	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}

	private int maxPathDown(TreeNode node) {
		if (node == null)
			return 0;
		int left = Math.max(0, maxPathDown(node.left));
		int right = Math.max(0, maxPathDown(node.right));
		maxValue = Math.max(maxValue, left + right + node.val);
		return Math.max(left, right) + node.val;
	}

	/*
	 * 142. Linked List Cycle II
	 * 
	 * Given a linked list, return the node where the cycle begins. If there is
	 * no cycle, return null.
	 * 
	 * To represent a cycle in the given linked list, we use an integer pos
	 * which represents the position (0-indexed) in the linked list where tail
	 * connects to. If pos is -1, then there is no cycle in the linked list.
	 * 
	 * Note: Do not modify the linked list.
	 */
	public ListNode detectCycle(ListNode head) {
		  ListNode slow = head;
          ListNode fast = head;
  
          while (fast!=null && fast.next!=null){
              fast = fast.next.next;
              slow = slow.next;
              
              if (fast == slow){
                  ListNode slow2 = head; 
                  while (slow2 != slow){
                      slow = slow.next;
                      slow2 = slow2.next;
                  }
                  return slow;
              }
          }
          return null;
	}

}
