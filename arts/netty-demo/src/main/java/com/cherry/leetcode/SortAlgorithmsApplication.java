package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import com.cherry.netty.utils.JsonUtil;

public class SortAlgorithmsApplication {

	/*
	 * 977. Squares of a Sorted Array
	 * 
	 * Given an array of integers A sorted in non-decreasing order, return an
	 * array of the squares of each number, also in sorted non-decreasing order.
	 */
	public int[] sortedSquares(int[] A) {
		int[] result = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			result[i] = A[i] * A[i];
		}
		Arrays.sort(result);
		return result;
	}

	public int[] sortedSquares2(int[] A) {
		int N = A.length;
		int j = 0;
		while (j < N && A[j] < 0)
			j++;
		int i = j - 1;

		int[] ans = new int[N];
		int t = 0;

		while (i >= 0 && j < N) {
			if (A[i] * A[i] < A[j] * A[j]) {
				ans[t++] = A[i] * A[i];
				i--;
			} else {
				ans[t++] = A[j] * A[j];
				j++;
			}
		}

		while (i >= 0) {
			ans[t++] = A[i] * A[i];
			i--;
		}
		while (j < N) {
			ans[t++] = A[j] * A[j];
			j++;
		}

		return ans;
	}

	/*
	 * 922. Sort Array By Parity II
	 * 
	 * Given an array A of non-negative integers, half of the integers in A are
	 * odd, and half of the integers are even.
	 * 
	 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i]
	 * is even, i is even.
	 * 
	 * You may return any answer array that satisfies this condition.
	 */
	public int[] sortArrayByParityII(int[] A) {
		int N = A.length;
		int[] ans = new int[N];

		int t = 0;
		for (int x : A)
			if (x % 2 == 0) {
				ans[t] = x;
				t += 2;
			}

		t = 1;
		for (int x : A)
			if (x % 2 == 1) {
				ans[t] = x;
				t += 2;
			}

		return ans;
	}

	public int[] sortArrayByParityII2(int[] A) {
		int j = 1;
		for (int i = 0; i < A.length; i += 2)
			if (A[i] % 2 == 1) {
				while (A[j] % 2 == 1)
					j += 2;

				int tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;
			}

		return A;
	}

	/*
	 * 973. K Closest Points to Origin
	 * 
	 * We have a list of points on the plane. Find the K closest points to the
	 * origin (0, 0).
	 * 
	 * (Here, the distance between two points on a plane is the Euclidean
	 * distance.)
	 * 
	 * You may return the answer in any order. The answer is guaranteed to be
	 * unique (except for the order that it is in.)
	 * 
	 */
	public int[][] kClosest(int[][] points, int K) {
		int N = points.length;
		int[] dists = new int[N];
		for (int i = 0; i < N; ++i)
			dists[i] = dist(points[i]);

		Arrays.sort(dists);
		int distK = dists[K - 1];

		int[][] ans = new int[K][2];
		int t = 0;
		for (int i = 0; i < N; ++i)
			if (dist(points[i]) <= distK)
				ans[t++] = points[i];
		return ans;
	}

	public int dist(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}

	public int[][] kClosest2(int[][] points, int K) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
				(p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
		for (int[] p : points) {
			pq.offer(p);
			if (pq.size() > K) {
				pq.poll();
			}
		}
		int[][] res = new int[K][2];
		while (K > 0) {
			res[--K] = pq.poll();
		}
		return res;
	}

	public int[][] kClosest3(int[][] points, int K) {
		int len = points.length, l = 0, r = len - 1;
		while (l <= r) {
			int mid = helper(points, l, r);
			if (mid == K)
				break;
			if (mid < K) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int helper(int[][] A, int l, int r) {
		int[] pivot = A[l];
		while (l < r) {
			while (l < r && compare(A[r], pivot) >= 0)
				r--;
			A[l] = A[r];
			while (l < r && compare(A[l], pivot) <= 0)
				l++;
			A[r] = A[l];
		}
		A[l] = pivot;
		return l;
	}

	private int compare(int[] p1, int[] p2) {
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}

	/*
	 * 969. Pancake Sorting
	 * 
	 * Given an array A, we can perform a pancake flip: We choose some positive
	 * integer k <= A.length, then reverse the order of the first k elements of
	 * A. We want to perform zero or more pancake flips (doing them one after
	 * another in succession) to sort the array A.
	 * 
	 * Return the k-values corresponding to a sequence of pancake flips that
	 * sort A. Any valid answer that sorts the array within 10 * A.length flips
	 * will be judged as correct.
	 * 
	 */
	public List<Integer> pancakeSort(int[] A) {
		List<Integer> ans = new ArrayList<Integer>();
		int N = A.length;

		Integer[] B = new Integer[N];
		for (int i = 0; i < N; ++i)
			B[i] = i + 1;
		Arrays.sort(B, (i, j) -> A[j - 1] - A[i - 1]);

		for (int i : B) {
			for (int f : ans)
				if (i <= f)
					i = f + 1 - i;
			ans.add(i);
			ans.add(N--);
		}

		return ans;
	}

	/*
	 * 164. Maximum Gap
	 * 
	 * Given an unsorted array, find the maximum difference between the
	 * successive elements in its sorted form. Return 0 if the array contains
	 * less than 2 elements.
	 * 
	 */
	public int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int maxval = Integer.MIN_VALUE;
		int minval = Integer.MAX_VALUE;
		// 求解数组最值
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > maxval)
				maxval = nums[i];
			if (nums[i] < minval)
				minval = nums[i];
		}

		// 数组内的元素值相同
		if (minval == maxval) {
			return 0;
		}

		// 数组仅有两个元素
		if (nums.length == 2) {
			return maxval - minval;
		}

		int len = (int) Math.ceil((double) (maxval - minval) / (nums.length - 1)); // 求解桶间差值,向上取整
		int n = (maxval - minval) / len;

		int maxBuk[] = new int[n + 1];
		int minBuk[] = new int[n + 1];

		Arrays.fill(maxBuk, Integer.MIN_VALUE);
		Arrays.fill(minBuk, Integer.MAX_VALUE);

		// 桶映射
		for (int val : nums) {
			int temp = (val - minval) / len;
			maxBuk[temp] = Math.max(val, maxBuk[temp]);
			minBuk[temp] = Math.min(val, minBuk[temp]);
		}

		// 求解最大gap,最大差值位于后桶的min-前桶的max
		int gap = 0;
		int pre = maxBuk[0];
		for (int i = 1; i <= n; i++) {
			if (maxBuk[i] == Integer.MIN_VALUE && minBuk[i] == Integer.MAX_VALUE) { // 忽略空桶
				continue;
			}
			gap = Math.max(gap, minBuk[i] - pre);
			pre = maxBuk[i];
		}

		return gap;
	}

	/*
	 * 976. Largest Perimeter Triangle
	 * 
	 * Given an array A of positive lengths, return the largest perimeter of a
	 * triangle with non-zero area, formed from 3 of these lengths.
	 * 
	 * If it is impossible to form any triangle of non-zero area, return 0.
	 */
	public int largestPerimeter(int[] A) {
		Arrays.sort(A);
		for (int i = A.length - 3; i >= 0; --i)
			if (A[i] + A[i + 1] > A[i + 2])
				return A[i] + A[i + 1] + A[i + 2];
		return 0;
	}

	/*
	 * 179. Largest Number
	 * 
	 * Given a list of non negative integers, arrange them such that they form
	 * the largest number.
	 */
	public String largestNumber(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++)
			strs[i] = Integer.toString(nums[i]);
		Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length())
					return s2.compareTo(s1);
				return (s2 + s1).compareTo(s1 + s2);
			}
		});
		if ("0".equals(strs[0]))
			return "0";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			sb.append(strs[i]);
		}
		return sb.toString();
	}

	/*
	 * 324. Wiggle Sort II
	 * 
	 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] >
	 * nums[2] < nums[3]....
	 */
	public void wiggleSort(int[] nums) {
		int n = nums.length, m = (n + 1) >> 1;
		int[] copy = Arrays.copyOf(nums, n);
		Arrays.sort(copy);

		for (int i = m - 1, j = 0; i >= 0; i--, j += 2)
			nums[j] = copy[i];
		for (int i = n - 1, j = 1; i >= m; i--, j += 2)
			nums[j] = copy[i];
	}

	public void wiggleSort2(int[] nums) {
		int n = nums.length, m = (n + 1) >> 1;
		int median = kthSmallestNumber(nums, m);

		for (int i = 0, j = 0, k = n - 1; j <= k;) {
			if (nums[A(j, n)] > median) {
				swap(nums, A(i++, n), A(j++, n));
			} else if (nums[A(j, n)] < median) {
				swap(nums, A(j, n), A(k--, n));
			} else {
				j++;
			}
		}
	}

	public void wiggleSort3(int[] nums) {
		int n = nums.length, m = (n + 1) >> 1;
		int[] copy = Arrays.copyOf(nums, n);
		int median = kthSmallestNumber(nums, m);

		for (int i = 0, j = 0, k = n - 1; j <= k;) {
			if (copy[j] < median) {
				swap(copy, i++, j++);
			} else if (copy[j] > median) {
				swap(copy, j, k--);
			} else {
				j++;
			}
		}

		for (int i = m - 1, j = 0; i >= 0; i--, j += 2)
			nums[j] = copy[i];
		for (int i = n - 1, j = 1; i >= m; i--, j += 2)
			nums[j] = copy[i];
	}

	private int kthSmallestNumber(int[] nums, int k) {
		Random random = new Random();

		for (int i = nums.length - 1; i >= 0; i--) {
			swap(nums, i, random.nextInt(i + 1));
		}

		int l = 0, r = nums.length - 1;
		k--;

		while (l < r) {
			int m = getMiddle(nums, l, r);

			if (m < k) {
				l = m + 1;
			} else if (m > k) {
				r = m - 1;
			} else {
				break;
			}
		}

		return nums[k];
	}

	private int getMiddle(int[] nums, int l, int r) {
		int i = l;

		for (int j = l + 1; j <= r; j++) {
			if (nums[j] < nums[l])
				swap(nums, ++i, j);
		}

		swap(nums, l, i);
		return i;
	}

	private void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}

	private int A(int i, int n) {
		return (2 * i + 1) % (n | 1);
	}

	/*
	 * 57. Insert Interval
	 * 
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to
	 * their start times.
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<Interval>();
		List<Integer> slist = new ArrayList<Integer>();
		List<Integer> elist = new ArrayList<Integer>();
		slist.add(newInterval.start);
		elist.add(newInterval.end);
		for (int i = 0; i < intervals.size(); i++) {
			slist.add(intervals.get(i).start);
			elist.add(intervals.get(i).end);
		}
		Collections.sort(slist);
		Collections.sort(elist);
		// System.out.println(JsonUtil.toJson(slist));
		// System.out.println(JsonUtil.toJson(elist));
		for (int i = 0; i < slist.size(); i++) {
			Interval temp = new Interval(slist.get(i), elist.get(i));
			if (result.size() == 0) {
				result.add(temp);
			} else {
				Interval last = result.get(result.size() - 1);
				if (last.end >= temp.start) {
					last.end = temp.end;
				} else {
					result.add(temp);
				}
			}

		}
		return result;
	}

	public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new LinkedList<>();
		int i = 0;
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			result.add(intervals.get(i++));
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
			newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start),
					Math.max(newInterval.end, intervals.get(i).end));
			i++;
		}
		result.add(newInterval);
		while (i < intervals.size())
			result.add(intervals.get(i++));
		return result;
	}

	/*
	 * 710. Random Pick with Blacklist
	 * 
	 * Given a blacklist B containing unique integers from [0, N), write a
	 * function to return a uniform random integer from [0, N) which is NOT in
	 * B.
	 * 
	 * Optimize it such that it minimizes the call to system’s Math.random().
	 */
	class Solution {
		int M;
		Map<Integer, Integer> map;
		Random r;

		public Solution(int N, int[] blacklist) {
			M = N - blacklist.length;
			map = new HashMap<>();
			r = new Random();
			for (int tmp : blacklist) {
				map.put(tmp, -1);
			}

			for (int tmp : blacklist) {
				if (tmp < M) {
					while (map.containsKey(N - 1)) {
						N--;
					}
					map.put(tmp, --N);
				}
			}
		}

		public int pick() {
			int p = r.nextInt(M);
			if (map.containsKey(p))
				return map.get(p);
			return p;
		}

	}

	/*
	 * 274. H-Index
	 * 
	 * Given an array of citations (each citation is a non-negative integer) of
	 * a researcher, write a function to compute the researcher's h-index.
	 * 
	 * According to the definition of h-index on Wikipedia:
	 * "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
	 * 
	 */
	public int hIndex(int[] citations) {
		if (citations.length == 0) {
			return 0;
		}
		Arrays.sort(citations);
		int h = 0;
		for (int i = citations.length - 1; i >= 0; i--) {
			if (citations.length - i > citations[i]) {
				h = citations.length - i;
				break;
			}
			if (i == 0) {
				h = citations.length + 1;
			}
		}
		return h - 1;
	}

	/*
	 * 147. Insertion Sort List
	 * 
	 * Sort a linked list using insertion sort.
	 * 
	 * A graphical example of insertion sort. The partial sorted list (black)
	 * initially contains only the first element in the list. With each
	 * iteration one element (red) is removed from the input data and inserted
	 * in-place into the sorted list
	 * 
	 * 
	 * Algorithm of Insertion Sort:
	 * 
	 * Insertion sort iterates, consuming one input element each repetition, and
	 * growing a sorted output list. At each iteration, insertion sort removes
	 * one element from the input data, finds the location it belongs within the
	 * sorted list, and inserts it there. It repeats until no input elements
	 * remain.
	 */
	public ListNode insertionSortList(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode helper = new ListNode(0);
		ListNode cur = head;
		ListNode pre = helper;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			while (pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
			}
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}

		return helper.next;
	}

	public ListNode insertionSortList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode sortedHead = head, sortedTail = head;
		head = head.next;
		sortedHead.next = null;

		while (head != null) {
			ListNode temp = head;
			head = head.next;
			temp.next = null;

			if (temp.val <= sortedHead.val) {
				temp.next = sortedHead;
				sortedTail = sortedHead.next == null ? sortedHead : sortedTail;
				sortedHead = temp;
			} else if (temp.val >= sortedTail.val) {
				sortedTail.next = temp;
				sortedTail = sortedTail.next;
			} else {
				ListNode current = sortedHead;
				while (current.next != null && current.next.val < temp.val) {
					current = current.next;
				}

				temp.next = current.next;
				current.next = temp;
			}
		}

		return sortedHead;
	}

	/*
	 * 767. Reorganize String
	 * 
	 * Given a string S, check if the letters can be rearranged so that two
	 * characters that are adjacent to each other are not the same.
	 * 
	 * If possible, output any possible result. If not possible, return the
	 * empty string.
	 */
	public String reorganizeString(String S) {
		int N = S.length();
		int[] counts = new int[26];
		for (char c : S.toCharArray())
			counts[c - 'a'] += 100;
		for (int i = 0; i < 26; ++i)
			counts[i] += i;
		Arrays.sort(counts);

		char[] ans = new char[N];
		int t = 1;
		for (int code : counts) {
			int ct = code / 100;
			char ch = (char) ('a' + (code % 100));
			if (ct > (N + 1) / 2)
				return "";
			for (int i = 0; i < ct; ++i) {
				if (t >= N)
					t = 0;
				ans[t] = ch;
				t += 2;
			}
		}

		return String.valueOf(ans);
	}

	/*
	 * 350. Intersection of Two Arrays II
	 * 
	 * Given two arrays, write a function to compute their intersection.
	 */
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int pnt1 = 0;
		int pnt2 = 0;
		ArrayList<Integer> myList = new ArrayList<Integer>();
		while ((pnt1 < nums1.length) && (pnt2 < nums2.length)) {
			if (nums1[pnt1] < nums2[pnt2]) {
				pnt1++;
			} else {
				if (nums1[pnt1] > nums2[pnt2]) {
					pnt2++;
				} else {
					myList.add(nums1[pnt1]);
					pnt1++;
					pnt2++;
				}
			}
		}
		int[] res = new int[myList.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (Integer) myList.get(i);
		}
		return res;
	}

	/*
	 * 524. Longest Word in Dictionary through Deleting
	 * 
	 * Given a string and a string dictionary, find the longest string in the
	 * dictionary that can be formed by deleting some characters of the given
	 * string. If there are more than one possible results, return the longest
	 * word with the smallest lexicographical order. If there is no possible
	 * result, return the empty string.
	 */
	public String findLongestWord(String s, List<String> d) {
		HashSet<String> set = new HashSet<>(d);
		List<String> l = new ArrayList<>();
		generate(s, "", 0, l);
		String max_str = "";
		for (String str : l) {
			if (set.contains(str))
				if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
					max_str = str;
		}
		return max_str;
	}

	public void generate(String s, String str, int i, List<String> l) {
		if (i == s.length())
			l.add(str);
		else {
			generate(s, str + s.charAt(i), i + 1, l);
			generate(s, str, i + 1, l);
		}
	}

	public String findLongestWord2(String s, List<String> d) {
		String longest = "";
		for (String dictWord : d) {
			int i = 0;
			for (char c : s.toCharArray())
				if (i < dictWord.length() && c == dictWord.charAt(i))
					i++;

			if (i == dictWord.length() && dictWord.length() >= longest.length())
				if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
					longest = dictWord;
		}
		return longest;
	}

	public String findLongestWord3(String s, List<String> d) {
		String longest = null;
		Iterator<String> itr = d.iterator();
		while (itr.hasNext()) {
			String dd = itr.next();
			int start = -1;
			boolean flag = true;
			for (int i = 0; i < dd.length(); i++) {
				start = s.indexOf(dd.charAt(i), start + 1);
				if (start < 0) {
					flag = false;
					break;
				}
			}
			if (!flag)
				continue;
			if (longest == null)
				longest = dd;
			else {
				if (dd.length() > longest.length())
					longest = dd;
				if (dd.length() == longest.length() && dd.compareTo(longest) < 0)
					longest = dd;
			}
		}
		return longest == null ? "" : longest;
	}

	public static void main(String[] args) {
		SortAlgorithmsApplication saa = new SortAlgorithmsApplication();
		// int[] a = { 1, 5, 1, 1, 6, 4 };
		// System.out.println(JsonUtil.toJson(saa.sortedSquares(a)));
		/*
		 * saa.wiggleSort(a); System.out.println(JsonUtil.toJson(a)); int[] a2 =
		 * { 1, 5, 1, 1, 6, 4 }; saa.wiggleSort2(a2);
		 * System.out.println(JsonUtil.toJson(a2)); int[] a3 = { 1, 5, 1, 1, 6,
		 * 4 }; saa.wiggleSort3(a3); System.out.println(JsonUtil.toJson(a3));
		 */

		// [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
		/*
		 * Interval in1 = new Interval(1, 2); Interval in2 = new Interval(3, 5);
		 * Interval in3 = new Interval(6, 7); Interval in4 = new Interval(8,
		 * 10); Interval in5 = new Interval(12, 16); Interval newInterval = new
		 * Interval(4, 8); List<Interval> intervals = new ArrayList<Interval>();
		 * intervals.add(in1); intervals.add(in2); intervals.add(in3);
		 * intervals.add(in4); intervals.add(in5);
		 * 
		 * System.out.println(JsonUtil.toJson(saa.insert(intervals,
		 * newInterval)));
		 */
		// 4->2->1->3 Output: 1->2->3->4
		/*
		 * ListNode node = new ListNode(4); node.next = new ListNode(2);
		 * node.next.next = new ListNode(1); node.next.next.next = new
		 * ListNode(3); System.out.println(saa.insertionSortList2(node));
		 */
		// System.out.println(saa.reorganizeString("aab"));
		// nums1 = [4,9,5], nums2 = [9,4,9,8,4]
		/*
		 * int[] nums1 = { 4, 9, 5 }, nums2 = { 9, 4, 9, 8, 4 };
		 * System.out.println(JsonUtil.toJson(saa.intersect(nums1, nums2)));
		 */
		// s = "abpcplea", d = ["ale","apple","monkey","plea"]
		List<String> list = new ArrayList<String>();
		list.add("ale");
		list.add("apple");
		list.add("monkey");
		list.add("plea");
		System.out.println(saa.findLongestWord("abpcplea", list));
	}

	public static void testFloat() {
		double x;
		x = 10 / 4;
		System.out.println("10/4=" + x);
		x = 10 / 4.0;
		System.out.println("10/4.0=" + x);
		x = 10.0 / 4;
		System.out.println("10.0/4=" + x);
		x = 10.0 / 4.0;
		System.out.println("10.0/4.0=" + x);

	}

}
