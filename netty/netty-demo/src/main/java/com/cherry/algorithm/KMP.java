package com.cherry.algorithm;

import com.cherry.netty.utils.JsonUtil;

public class KMP {
	int[] buildNext(char[] p) {
		int len = p.length, j = 0;
		int[] next = new int[len];
		int t = next[0] = -1;
		while (j < len - 1) {
//			System.out.print("j="+j+",t="+t);
			if (0 > t || p[j] == p[t]){
				//版本1.0
//				next[++j] = ++t;
				// 版本2.0
				j++;t++;
				next[j] = p[j]!=p[t]?t:next[t];
			}
			else
				t = next[t];
//			System.out.println(",after  j="+j+",t="+t+",next["+j+"]="+next[j]);
		}
		return next;
	}
	
	int kmpMatcher(String t,String p){
		int n = t.length(),i=0,j=0,m=p.length();
		int[] next = buildNext(p.toCharArray());
		while(i < n && j< m){
			if(j==-1||t.charAt(i)==p.charAt(j)){
				i++;j++;
			}else{
				j=next[j];
			}
		}
		if (j == m) 
			return i-j;
		else
			return -1;
			
	}
	public static void main(String[] args) {
		KMP kmp = new KMP();
		System.out.println(JsonUtil.toJson(kmp.buildNext("ababaca".toCharArray())));
		System.out.println(JsonUtil.toJson(kmp.buildNext("abab".toCharArray())));
		System.out.println(kmp.kmpMatcher("abcabcababadabcbdf", "abab"));
	}

}
