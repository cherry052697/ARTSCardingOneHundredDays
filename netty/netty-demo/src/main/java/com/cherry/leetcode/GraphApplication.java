package com.cherry.leetcode;

public class GraphApplication {

	/*
	 * 959. Regions Cut By Slashes
	 * 
	 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of
	 * a /, \, or blank space. These characters divide the square into
	 * contiguous regions.
	 * 
	 * (Note that backslash characters are escaped, so a \ is represented as
	 * "\\".)
	 * 
	 * Return the number of regions.
	 */
	public int regionsBySlashes(String[] grid) {

		return 0;
	}

	/*
	 * 997. Find the Town Judge
	 * 
	 * In a town, there are N people labelled from 1 to N. There is a rumor that
	 * one of these people is secretly the town judge.
	 * 
	 * If the town judge exists, then:
	 * 
	 * The town judge trusts nobody. Everybody (except for the town judge)
	 * trusts the town judge. There is exactly one person that satisfies
	 * properties 1 and 2. You are given trust, an array of pairs trust[i] = [a,
	 * b] representing that the person labelled a trusts the person labelled b.
	 * 
	 * If the town judge exists and can be identified, return the label of the
	 * town judge. Otherwise, return -1.
	 */
	public int findJudge(int N, int[][] trust) {
		int[] count = new int[N+1];
        for (int[] t: trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i <= N; ++i) {
            if (count[i] == N - 1) return i;
        }
        return -1;
	}

	public static void main(String[] args) {

	}

}
