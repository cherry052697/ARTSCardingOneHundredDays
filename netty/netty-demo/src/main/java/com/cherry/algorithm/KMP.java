package com.cherry.algorithm;

import com.cherry.netty.utils.JsonUtil;

public class KMP {
	int[] buildNext(char[] p) {
		int len = p.length, j = 0;
		int[] next = new int[len];
		int t = next[0] = -1;
		while (j < len - 1) {
			if (0 > t || p[j] == p[t])
				next[++j] = ++t;
			else
				t = next[t];
		}
		return next;
	}
	
	public static void main(String[] args) {
		KMP kmp = new KMP();
		System.out.println(JsonUtil.toJson(kmp.buildNext("abcabcdabc".toCharArray())));
	}

}
