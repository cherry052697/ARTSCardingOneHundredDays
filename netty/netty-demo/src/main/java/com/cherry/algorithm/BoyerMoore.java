package com.cherry.algorithm;

import java.util.Arrays;

//字符串查找算法，Boyer-Moore算法
public class BoyerMoore {
	private String pat;
	private int[] right;

	public BoyerMoore(String pat) {
		this.pat = pat;
		int R = 256;
		right = new int[R];
		Arrays.fill(right, -1);
		int patLen = pat.length();
		for (int i = 0; i < patLen; i++) {
			right[pat.charAt(i)] = i;
		}
	}

	public int search(String str) {
		int i, j, N = str.length(), M = pat.length();
		int skip;
		for (i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (j = M - 1; j >= 0; j--) {
				if (str.charAt(i + j) != pat.charAt(j)) {
					skip = j - right[str.charAt(i + j)];
					if (skip < 1) {// 保证模式字符串时不断向后移动的
						skip = 1;
					}
					break;
				}
			}
			if (skip == 0)
				return i;
		}
		return N;
	}

	public static void main(String[] args) {
		String t1 = "bcbaabacaababacaa";
		String t2 = "ababac";
		BoyerMoore bm = new BoyerMoore(t2);
		System.out.println(bm.search(t1));
	}
}