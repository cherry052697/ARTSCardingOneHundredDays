package com.cherry.leetcode;

import com.cherry.netty.utils.JsonUtil;

public class DynamicProgramming {
	public static void main(String[] args) {
		DynamicProgramming dp = new DynamicProgramming();
		int[] nums = { 5, 3, 4, 5 };
		dp.stoneGame(nums);
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
	
	void printArray(int[][] dp){
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) 
				System.out.print(dp[i][j]+" ");
			System.out.println();
			
		}
	}


}
