package com.cherry.leetcode;

import java.util.Arrays;
import java.util.List;

import com.cherry.netty.utils.JsonUtil;

public class DynamicProgramming {
	public static void main(String[] args) {
		DynamicProgramming dp = new DynamicProgramming();
		// int[] days = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 }, costs = { 2,
		// 7, 15 };
		// System.out.println(dp.mincostTickets2(days, costs));
		// System.out.println(dp.eval(3));
		// int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		// int[] nums = { 1, 2, 3, 4 };
		// System.out.println(dp.numberOfArithmeticSlices(nums));
		// System.out.println(dp.minimumDeleteSum2("delete", "leet"));

		int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
		// System.out.println(dp.lengthOfLIS(nums));
		System.out.println(dp.integerBreak(10));

	}

	public int fibonacci(int n) {
		if (n <= 1) {
			return 1;
		}
		int last = 1, nextToLast = 1, answer = 1;
		for (int i = 2; i <= n; i++) {
			answer = last + nextToLast;
			nextToLast = last;
			last = answer;
		}
		return answer;
	}

	public double eval(int n) {
		double[] c = new double[n + 1];
		c[0] = 1.0;
		for (int i = 1; i <= n; i++) {
			double sum = 0.0;
			for (int j = 0; j < i; j++)
				sum += c[j];
			c[i] = 2.0 * sum / i + i;
			System.out.println(JsonUtil.toJson(c));
		}
		return c[n];
	}

	/*
	 * 877. Stone Game
	 * 
	 * Alex and Lee play a game with piles of stones. There are an even number
	 * of piles arranged in a row, and each pile has a positive integer number
	 * of stones piles[i].
	 * 
	 * The objective of the game is to end with the most stones. The total
	 * number of stones is odd, so there are no ties.
	 * 
	 * Alex and Lee take turns, with Alex starting first. Each turn, a player
	 * takes the entire pile of stones from either the beginning or the end of
	 * the row. This continues until there are no more piles left, at which
	 * point the person with the most stones wins.
	 * 
	 * Assuming Alex and Lee play optimally, return True if and only if Alex
	 * wins the game.
	 * 
	 * 解题思路说明： 我们每次只能拿两端的石头堆的石头，但我们又不知道拿完后剩下的石头堆的情况，因此我们考虑先解决子问题。
	 * 例如我们求出2个相邻石头堆的胜负情况，我们可以根据求出的数据求出相邻3个石头堆的胜负情况，
	 * 以此类推，我们可以根据n-1个相邻石头堆的胜负情况，求出n个相邻石头堆的胜负情况，即我们的原问题。
	 * 根据我们的类推我们可以设dp[i][j]为piles[i]~piles[j]Alex最多可以赢Lee的分数。每次取石头堆只能从两端取，
	 * 因此:dp[i][j] = max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1])。
	 * 其中piles[i] - dp[i+1][j]表示Alex取走i上的石头堆，piles[j] -
	 * dp[i][j-1]表示Alex取走的是j上的石头堆。
	 * 注意，为什么dp[i+1][j]表示piles[i+1]~piles[j]之间Alex最多可以赢Lee的分数，
	 * 而piles[i]要减去该值而不是加上该值呢？由于我们的要求是每一步Alex和Lee采取的都是最优策略，
	 * 当取piles[i]时，piles[i+1]~piles[j]中Alex和Lee的走法会调换。
	 * 意即Lee走Alex的走法，Alex走Lee的走法，因此这里要做减法。
	 */
	public boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++)
			dp[i][i] = piles[i];
		for (int j = 1; j < n; j++)
			for (int i = 0; i < n - j; i++) {
				int pickIndexI = piles[i] - dp[i + 1][i + j];
				int pickIndexJ = piles[i + j] - dp[i][i + j - 1];
				dp[i][i + j] = Math.max(pickIndexI, pickIndexJ);
			}
		printArray(dp);
		return dp[0][n - 1] > 0;
	}

	void printArray(int[][] dp) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();

		}
	}

	/*
	 * 983. Minimum Cost For Tickets
	 * 
	 * In a country popular for train travel, you have planned some train
	 * travelling one year in advance. The days of the year that you will travel
	 * is given as an array days. Each day is an integer from 1 to 365.
	 * 
	 * Train tickets are sold in 3 different ways:
	 * 
	 * a 1-day pass is sold for costs[0] dollars; a 7-day pass is sold for
	 * costs[1] dollars; a 30-day pass is sold for costs[2] dollars. The passes
	 * allow that many days of consecutive travel. For example, if we get a
	 * 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7,
	 * and 8.
	 * 
	 * Return the minimum number of dollars you need to travel every day in the
	 * given list of days.
	 * 
	 */

	public int mincostTickets(int[] days, int[] costs) {
		int[] durations = { 1, 7, 30 };
		Integer[] memo = new Integer[days.length];
		return dp(days, costs, 0, memo, durations);
	}

	public int dp(int[] days, int[] costs, int i, Integer[] memo, int[] durations) {
		if (i >= days.length)
			return 0;
		if (memo[i] != null)
			return memo[i];

		int ans = Integer.MAX_VALUE;
		int j = i;
		for (int k = 0; k < 3; ++k) {
			while (j < days.length && days[j] < days[i] + durations[k])
				j++;
			ans = Math.min(ans, dp(days, costs, j, memo, durations) + costs[k]);
		}

		memo[i] = ans;
		return ans;
	}

	public int mincostTickets2(int[] days, int[] costs) {

		int[] dp = new int[days.length + 1];
		int weekPointer = days.length - 1;
		int monthPointer = days.length - 1;
		dp[days.length] = 0;
		for (int currDay = days.length - 1; currDay >= 0; currDay--) {
			while (days[weekPointer] - days[currDay] >= 7)
				weekPointer--;
			while (days[monthPointer] - days[currDay] >= 30)
				monthPointer--;
			dp[currDay] = MinNum(costs[0] + dp[currDay + 1], costs[1] + dp[weekPointer + 1],
					costs[2] + dp[monthPointer + 1]);
		}

		return dp[0];
	}

	public int MinNum(int i, int j, int k) {
		return Math.min(i, Math.min(j, k));
	}

	/*
	 * 980. Unique Paths III
	 * 
	 * On a 2-dimensional grid, there are 4 types of squares:
	 * 
	 * 1 represents the starting square. There is exactly one starting square. 2
	 * represents the ending square. There is exactly one ending square. 0
	 * represents empty squares we can walk over. -1 represents obstacles that
	 * we cannot walk over. Return the number of 4-directional walks from the
	 * starting square to the ending square, that walk over every non-obstacle
	 * square exactly once.
	 * 
	 */
	int res = 0, empty = 1, sx, sy, ex, ey;

	public int uniquePathsIII(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == 0)
					empty++;
				else if (grid[i][j] == 1) {
					sx = i;
					sy = j;
				} else if (grid[i][j] == 2) {
					ex = i;
					ey = j;
				}
			}
		}
		dfs(grid, sx, sy);
		return res;
	}

	public void dfs(int[][] grid, int x0, int y0) {
		if (check(grid, x0, y0) == false)
			return;
		if (x0 == ex && y0 == ey && empty == 0) {
			res++;
			return;
		}
		grid[x0][y0] = -2;
		empty--;
		dfs(grid, x0 + 1, y0);
		dfs(grid, x0 - 1, y0);
		dfs(grid, x0, y0 + 1);
		dfs(grid, x0, y0 - 1);
		grid[x0][y0] = 0;
		empty++;
	}

	public boolean check(int[][] grid, int i, int j) {
		int m = grid.length, n = grid[0].length;
		return 0 <= i && i < m && 0 <= j && j < n && grid[i][j] >= 0;
	}

	/*
	 * 931. Minimum Falling Path Sum
	 * 
	 * Given a square array of integers A, we want the minimum sum of a falling
	 * path through A.
	 * 
	 * A falling path starts at any element in the first row, and chooses one
	 * element from each row. The next row's choice must be in a column that is
	 * different from the previous row's column by at most one.
	 */
	public int minFallingPathSum(int[][] A) {
		int N = A.length;
		for (int r = N - 2; r >= 0; --r) {
			for (int c = 0; c < N; ++c) {
				int best = A[r + 1][c];
				if (c > 0)
					best = Math.min(best, A[r + 1][c - 1]);
				if (c + 1 < N)
					best = Math.min(best, A[r + 1][c + 1]);
				A[r][c] += best;
			}
		}

		int ans = Integer.MAX_VALUE;
		printArray(A);
		for (int x : A[0])
			ans = Math.min(ans, x);
		return ans;
	}

	public int minFallingPathSum2(int[][] A) {
		for (int i = 1; i < A.length; ++i)
			for (int j = 0; j < A.length; ++j)
				A[i][j] += Math.min(A[i - 1][j],
						Math.min(A[i - 1][Math.max(0, j - 1)], A[i - 1][Math.min(A.length - 1, j + 1)]));
		// printArray(A);
		return Arrays.stream(A[A.length - 1]).min().getAsInt();
	}

	/*
	 * 413. Arithmetic Slices
	 * 
	 * A sequence of number is called arithmetic if it consists of at least
	 * three elements and if the difference between any two consecutive elements
	 * is the same.
	 * 
	 */
	public int numberOfArithmeticSlices(int[] A) {
		int count = 0;
		for (int s = 0; s < A.length - 2; s++) {
			int d = A[s + 1] - A[s];
			for (int e = s + 2; e < A.length; e++) {
				if (A[e] - A[e - 1] == d)
					count++;
				else
					break;
			}
		}
		return count;
	}

	/*
	 * 712. Minimum ASCII Delete Sum for Two Strings
	 * 
	 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters
	 * to make two strings equal.
	 */
	public int minimumDeleteSum(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		int sum = 0;
		for (int i = 0; i < s1.length(); i++) {
			sum += (int) s1.charAt(i);
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + (int) s1.charAt(i);
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}
		// printArray(dp);
		for (int j = 0; j < s2.length(); j++) {
			sum += (int) s2.charAt(j);
		}
		return sum - 2 * dp[s1.length()][s2.length()];
	}

	public int minimumDeleteSum2(String s1, String s2) {
		int s1Len = s1.length(), s2Len = s2.length(), MAX = Integer.MAX_VALUE;
		char[] s1Array = s1.toCharArray(), s2Array = s2.toCharArray();
		int[][] dp = new int[s1Len + 1][s2Len + 1];
		for (int i = s1Len; i >= 0; i--) {
			for (int j = s2Len; j >= 0; j--) {
				if (i < s1Len || j < s2Len)
					if (i < s1Len && j < s2Len && s1Array[i] == s2Array[j]) {
						dp[i][j] = dp[i + 1][j + 1];
					} else {
						int s1max = (i < s1Len ? s1Array[i] + dp[i + 1][j] : MAX);
						int s2max = (j < s2Len ? s2Array[j] + dp[i][j + 1] : MAX);
						dp[i][j] = Math.min(s1max, s2max);
					}
			}
		}
		printArray(dp);
		return dp[0][0];
	}

	/*
	 * 714. Best Time to Buy and Sell Stock with Transaction Fee
	 * 
	 * Your are given an array of integers prices, for which the i-th element is
	 * the price of a given stock on day i; and a non-negative integer fee
	 * representing a transaction fee.
	 * 
	 * You may complete as many transactions as you like, but you need to pay
	 * the transaction fee for each transaction. You may not buy more than 1
	 * share of a stock at a time (ie. you must sell the stock share before you
	 * buy again.)
	 * 
	 * Return the maximum profit you can make.
	 */
	public int maxProfit(int[] prices, int fee) {
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i] - fee);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}

	public int maxProfit2(int[] prices, int fee) {
		if (prices == null || prices.length < 2)
			return 0;
		int[] dp = new int[prices.length];
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i] - dp[i - 1]);
			dp[i] = Math.max(dp[i - 1], prices[i] - fee - min);
		}

		return dp[prices.length - 1];

	}

	public int lcs(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int c[][] = new int[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {
				if (i == 0 || j == 0) {
					c[i][j] = 0;
				} else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					c[i][j] = c[i - 1][j - 1] + 1;
				} else {
					c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
				}
			}
		}
		return c[len1][len2];
	}

	/*
	 * 300. Longest Increasing Subsequence
	 * 
	 * Given an unsorted array of integers, find the length of longest
	 * increasing subsequence.
	 */
	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0)
			return 0;
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int maxans = 1, maxval = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					maxval = Math.max(maxval, dp[j]);
				}
			}
			dp[i] = maxval + 1;
			maxans = Math.max(maxans, dp[i]);
			maxval = 0;
		}
		return maxans;
	}

	/*
	 * 638. Shopping Offers
	 * 
	 * In LeetCode Store, there are some kinds of items to sell. Each item has a
	 * price.
	 * 
	 * However, there are some special offers, and a special offer consists of
	 * one or more different kinds of items with a sale price.
	 * 
	 * You are given the each item's price, a set of special offers, and the
	 * number we need to buy for each item. The job is to output the lowest
	 * price you have to pay for exactly certain items as given, where you could
	 * make optimal use of the special offers.
	 * 
	 * Each special offer is represented in the form of an array, the last
	 * number represents the price you need to pay for this special offer, other
	 * numbers represents how many specific items you could get if you buy this
	 * offer.
	 * 
	 * You could use any of special offers as many times as you want.
	 * 
	 */
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		return 0;
	}

	/*
	 * 646. Maximum Length of Pair Chain
	 * 
	 * You are given n pairs of numbers. In every pair, the first number is
	 * always smaller than the second number. Now, we define a pair (c, d) can
	 * follow another pair (a, b) if and only if b < c. Chain of pairs can be
	 * formed in this fashion. Given a set of pairs, find the length longest
	 * chain which can be formed. You needn't use up all the given pairs. You
	 * can select pairs in any order.
	 */
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		int N = pairs.length;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);

		for (int j = 1; j < N; ++j) {
			for (int i = 0; i < j; ++i) {
				if (pairs[i][1] < pairs[j][0])
					dp[j] = Math.max(dp[j], dp[i] + 1);
			}
		}

		int ans = 0;
		for (int x : dp)
			if (x > ans)
				ans = x;
		return ans;
	}

	public int findLongestChain2(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
		int cur = Integer.MIN_VALUE, ans = 0;
		for (int[] pair : pairs)
			if (cur < pair[0]) {
				cur = pair[1];
				ans++;
			}
		return ans;
	}

	/*
	 * 343. Integer Break
	 * 
	 * Given a positive integer n, break it into the sum of at least two
	 * positive integers and maximize the product of those integers. Return the
	 * maximum product you can get
	 */
	public int integerBreak(int n) {
		if (n == 2)
			return 1;
		if (n == 3)
			return 2;
		int product = 1;
		while (n > 4) {
			product *= 3;
			n -= 3;
		}
		product *= n;

		return product;
	}

	public int integerBreak2(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
			}
		}
		return dp[n];
	}

}
