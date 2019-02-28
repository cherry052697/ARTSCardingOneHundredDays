package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import com.cherry.netty.utils.JsonUtil;

import java.util.List;

public class TestEasyProblems {
	public static void main(String[] args) {
		TestEasyProblems test = new TestEasyProblems();
		TreeNode root = new TreeNode(5);
		TreeNode r1 = new TreeNode(1);
		TreeNode l1 = new TreeNode(4);
		TreeNode lr = new TreeNode(3);
		TreeNode ll = new TreeNode(6);
		root.left = r1;
		root.right = l1;
		l1.left = lr;
		l1.right = ll;
		// System.out.println(test.isValidBST(root));

		String s = "XXCL";
		// System.out.println(test.romanToInt(s));
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(test.maxSubArray(nums));

	}

	/*
	 * The count-and-say sequence is the sequence of integers with the first
	 * five terms as following: 1. 1 2. 11 3. 21 4. 1211 5. 111221
	 */
	public String countAndSay(int n) {
		return null;
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
	 * every important Given an integer array, you need to find one continuous
	 * subarray that if you only sort this subarray in ascending order, then the
	 * whole array will be sorted in ascending order, too.
	 */

	public int findUnsortedSubarray(int[] nums) {
		int n = nums.length;
		int beginInt = -1;
		int endInt = -2;

		int min = nums[n - 1];
		int max = nums[0];

		for (int i = 0; i < n; i++) {
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[n - 1 - i]);
			if (nums[i] < max)
				endInt = i;
			if (nums[n - 1 - i] > min)
				beginInt = n - 1 - i;
			System.out.println("i=" + i + ",max=" + max + ",min=" + min + ",end=" + endInt + ",beg=" + beginInt);
		}
		return endInt - beginInt + 1;
	}

	/*
	 * very important Given two sorted integer arrays nums1 and nums2, merge
	 * nums2 into nums1 as one sorted array
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		m = m - 1;
		n = n - 1;
		int i = m + n + 1;
		while (m >= 0 || n >= 0) {
			if (m < 0) {
				nums1[i--] = nums2[n--];
			} else if (n < 0) {
				nums1[i--] = nums1[m--];
			} else {
				nums1[i--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
			}
		}
	}

	/*
	 * very important You are climbing a stair case. It takes n steps to reach
	 * to the top. Each time you can either climb 1 or 2 steps. In how many
	 * distinct ways can you climb to the top? Note: Given n will be a positive
	 * integer.
	 */
	public int climbStairs2(int n) {
		if (n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public int climbStairs(int n) {
		return climb_Stairs(0, n);
	}

	public int climb_Stairs(int i, int n) {
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}
		return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
	}

	/*
	 * Compute and return the square root of x, where x is guaranteed to be a
	 * non-negative integer. Since the return type is an integer, the decimal
	 * digits are truncated and only the integer part of the result is returned.
	 */
	public int mySqrt(int x) {
		double xd = x;
		return (int) Math.sqrt(xd);
	}

	/*
	 * Given a non-empty array of digits representing a non-negative integer,
	 * plus one to the integer. The digits are stored such that the most
	 * significant digit is at the head of the list, and each element in the
	 * array contain a single digit. You may assume the integer does not contain
	 * any leading zero, except the number 0 itself.
	 */
	public int[] plusOne(int[] digits) {
		int len = digits.length;
		int sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum = digits[i] + 1;
			if (sum == 10) {
				digits[i] = 0;
				continue;
			} else {
				digits[i] = sum;
				break;
			}
		}
		if (sum == 10) {
			int[] result = new int[len + 1];
			for (int j = 0; j < len; j++)
				result[j + 1] = digits[j];
			result[0] = 1;
			return result;
		} else {
			return digits;
		}
	}

	/*
	 * Given an integer array nums, find the contiguous subarray (containing at
	 * least one number) which has the largest sum and return its sum.
	 */
	public int maxSubArray(int[] nums) {
		int[] orderNums = new int[nums.length];
		orderNums[0] = nums[0];
		int max = orderNums[0];
		for (int i = 1; i < nums.length; i++) {
			orderNums[i] = Math.max(orderNums[i - 1] + nums[i], nums[i]);
			max = Math.max(max, orderNums[i]);
			System.out.println(JsonUtil.toJson(orderNums) + "——" + max);
		}
		return max;
	}

	/*
	 * 28. Implement strStr()
	 * 
	 * Return the index of the first occurrence of needle in haystack, or -1 if
	 * needle is not part of haystack.
	 */
	public int strStr(String haystack, String needle) {
		if ("".equals(needle) && "".equals(haystack)) {
			return 0;
		}
		for (int i = 0; i < haystack.length(); i++) {
			if (i + needle.length() > haystack.length()) {
				return -1;
			}
			if (haystack.substring(i, i + needle.length()).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

	public int removeElement(int[] nums, int val) {
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
		}
		return i;
	}

	public int removeDuplicates2(int[] nums) {
		if (nums.length == 0)
			return 0;
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[j] != nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i + 1;
	}

	public int removeDuplicates(int[] nums) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				int value = map.get(nums[i]);
				map.put(nums[i], ++value);
			} else {
				map.put(nums[i], 1);
			}
		}
		if (nums.length > map.size()) {
			int index = 0;
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				nums[index] = it.next();
				index++;
			}
		}
		return map.size();
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}
		if (l1 == null) {
			curr.next = l2;
		} else if (l2 == null) {
			curr.next = l1;
		}
		return dummy.next;
	}

	/*
	 * Valid Parentheses Given a string containing just the characters '(', ')',
	 * '{', '}', '[' and ']', determine if the input string is valid. An input
	 * string is valid if: Open brackets must be closed by the same type of
	 * brackets. Open brackets must be closed in the correct order. Note that an
	 * empty string is also considered valid.
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
	 * Longest Common Prefix Write a function to find the longest common prefix
	 * string amongst an array of strings. If there is no common prefix, return
	 * an empty string "".
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";
			}
		return prefix;
	}

	public boolean checkPossibility(int[] nums) {
		if (nums == null)
			return false;
		if (nums.length <= 2)
			return true;
		int len = nums.length, i = 0, modify = 1;
		while (i < len - 2) {
			if (Math.min(nums[i], nums[i + 1]) > nums[i + 2]) {
				if (nums[i] > nums[i + 1]) {
					nums[i] = nums[i + 2];
					nums[i + 1] = nums[i + 2];
					modify -= 2;
				} else {
					nums[i + 2] = nums[i + 1];
					modify--;
				}
			} else {
				if (nums[i] > nums[i + 1]) {
					nums[i] = nums[i + 1];
					modify--;
				} else if (nums[i + 1] > nums[i + 2]) {
					nums[i + 1] = nums[i];
					modify--;
				}
			}
			if (modify < 0)
				return false;
			i++;
		}

		return modify >= 0;
	}

	/*
	 * I - 1 V - 5 X - 10 L - 50 C - 100 D - 500 M - 1000
	 * 
	 * If I comes before V or X, subtract 1 eg: IV = 4 and IX = 9 If X comes
	 * before L or C, subtract 10 eg: XL = 40 and XC = 90 If C comes before D or
	 * M, subtract 100 eg: CD = 400 and CM = 900
	 */
	public int romanToInt(String s) {
		int result = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("I", 1);
		map.put("V", 5);
		map.put("X", 10);
		map.put("L", 50);
		map.put("C", 100);
		map.put("D", 500);
		map.put("M", 1000);
		map.put("IV", 4);
		map.put("IX", 9);
		map.put("XL", 40);
		map.put("XC", 90);
		map.put("CD", 400);
		map.put("CM", 900);
		for (int i = 0; i < s.length(); i++) {
			String s1 = String.valueOf(s.charAt(i));
			String s2 = null;
			if (i + 1 == s.length()) {
				s2 = "";
			} else {
				s2 = String.valueOf(s.charAt(i + 1));
			}
			String ss12 = s1 + s2;
			if ((s1.equals("I") || s1.equals("X") || s1.equals("C")) && map.containsKey(ss12)) {
				result += map.get(ss12);
				++i;
			} else if (map.containsKey(s1)) {
				result += map.get(s1);
			}
		}
		return result;
	}

	public boolean isPalindrome2(int x) {
		boolean result = false;
		int numInput = x;
		int num = 0;
		int dig = 0;
		if (x > 0) {
			while (x > 0) {
				dig = x % 10;
				num = num * 10 + dig;
				x = x / 10;
			}
			if (num == numInput) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Determine whether an integer is a palindrome. An integer is a palindrome
	 * when it reads the same backward as forward.
	 */
	public boolean isPalindrome(int x) {
		boolean result = false;
		try {
			StringBuffer sb = new StringBuffer(String.valueOf(x));
			int num = Integer.valueOf(sb.reverse().toString());
			if (num == x) {
				result = true;
				;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return result;
	}

	public int reverse2(int x) {
		int symbol = x > 0 ? 1 : -1;
		int result = 1;
		x = x * symbol;
		int len = String.valueOf(x).length();
		String strReverse = new String();
		try {
			for (int i = 1; i < len; i++) {
				strReverse += (x % 10);
				x = (x / 10) == 0 ? (x % 10) : (x / 10);
			}
			strReverse += x;
			result = Integer.valueOf(strReverse);
			result = result * symbol;
		} catch (NumberFormatException e) {
			result = 0;
		}
		return result;
	}

	public int reverse(int x) {
		int symbol = x > 0 ? 1 : -1;
		int result = 1;
		x = x * symbol;
		try {
			String strX = String.valueOf(x);
			String strReverse = new String();
			for (int i = 0; i < strX.length(); i++) {
				strReverse += (strX.charAt(strX.length() - 1 - i));
			}
			result = Integer.valueOf(strReverse);
			result = result * symbol;
		} catch (NumberFormatException e) {
			result = 0;
			e.getStackTrace();
		}
		return result;
	}

	/*
	 * Given an array of integers, return indices of the two numbers such that
	 * they add up to a specific target. You may assume that each input would
	 * have exactly one solution, and you may not use the same element twice.
	 */
	public int[] twoSum(int[] nums, int target) {
		int[] result = null;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result = new int[] { i, j };
					break;
				}
			}
			if (result != null) {
				break;
			}
		}
		return result;
	}

}
