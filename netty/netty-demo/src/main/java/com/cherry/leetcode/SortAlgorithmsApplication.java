package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

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
		for(int i=0;i<intervals.size();i++){
			slist.add(intervals.get(i).start);
			elist.add(intervals.get(i).end);
		}
		Collections.sort(slist);
		Collections.sort(elist);
//		System.out.println(JsonUtil.toJson(slist));
//		System.out.println(JsonUtil.toJson(elist));
		for (int i = 0; i < slist.size(); i++) {
			Interval temp = new Interval(slist.get(i), elist.get(i));
			if (result.size()==0) {
				result.add(temp);
			}else{
				Interval last = result.get(result.size()-1);
				if (last.end >= temp.start) {
					last.end = temp.end;
				}else{
					result.add(temp);
				}
			}
			
		}
		return result;
	}

	public static void main(String[] args) {
		SortAlgorithmsApplication saa = new SortAlgorithmsApplication();
//		int[] a = { 1, 5, 1, 1, 6, 4 };
		// System.out.println(JsonUtil.toJson(saa.sortedSquares(a)));
		/*saa.wiggleSort(a);
		System.out.println(JsonUtil.toJson(a));
		int[] a2 = { 1, 5, 1, 1, 6, 4 };
		saa.wiggleSort2(a2);
		System.out.println(JsonUtil.toJson(a2));
		int[] a3 = { 1, 5, 1, 1, 6, 4 };
		saa.wiggleSort3(a3);
		System.out.println(JsonUtil.toJson(a3));*/
		
//		[[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
		Interval in1 = new Interval(1, 2);
		Interval in2 = new Interval(3,5);
		Interval in3 = new Interval(6,7);
		Interval in4 = new Interval(8,10);
		Interval in5 = new Interval(12,16);
		Interval newInterval = new Interval(4, 8);
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(in1);
		intervals.add(in2);
		intervals.add(in3);
		intervals.add(in4);
		intervals.add(in5);
		
		System.out.println(JsonUtil.toJson(saa.insert(intervals, newInterval)));
	}

}
