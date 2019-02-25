package com.cherry.leetcode;

import java.util.List;
import java.util.Stack;

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
	 * 
	 * Intuition: Consider trust as a graph, all pairs are directed edge. The
	 * point with in-degree - out-degree = N - 1 become the judge.
	 */
	public int findJudge(int N, int[][] trust) {
		int[] count = new int[N + 1];
		for (int[] t : trust) {
			count[t[0]]--;
			count[t[1]]++;
		}
		for (int i = 1; i <= N; ++i) {
			if (count[i] == N - 1)
				return i;
		}
		return -1;
	}

	/*
	 * 841. Keys and Rooms
	 * 
	 * There are N rooms and you start in room 0. Each room has a distinct
	 * number in 0, 1, 2, ..., N-1, and each room may have some keys to access
	 * the next room.
	 * 
	 * Formally, each room i has a list of keys rooms[i], and each key
	 * rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length. A
	 * key rooms[i][j] = v opens the room with number v.
	 * 
	 * Initially, all the rooms start locked (except for room 0).
	 * 
	 * You can walk back and forth between rooms freely.
	 * 
	 * Return true if and only if you can enter every room.
	 */
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		boolean[] seen = new boolean[rooms.size()];
		seen[0] = true;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		while (!stack.isEmpty()) {
			int node = stack.pop();
			for (int nei : rooms.get(node))
				if (!seen[nei]) {
					seen[nei] = true;
					stack.push(nei);
				}
		}
		for (boolean v : seen)
			if (!v)
				return false;
		return true;
	}

	public static void main(String[] args) {
		GraphApplication ga = new GraphApplication();
		int[][] trust = { { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 4, 3 } };
		System.out.println(ga.findJudge(4, trust));
	}

}