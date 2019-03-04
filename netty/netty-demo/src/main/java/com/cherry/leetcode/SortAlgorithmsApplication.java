package com.cherry.leetcode;

import java.util.Arrays;

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

	public static void main(String[] args) {
		SortAlgorithmsApplication saa = new SortAlgorithmsApplication();
		int[] a = { -4, -1, 0, 3, 10 };
		System.out.println(JsonUtil.toJson(saa.sortedSquares(a)));
	}

}
