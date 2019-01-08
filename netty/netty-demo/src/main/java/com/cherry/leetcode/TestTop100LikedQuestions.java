package com.cherry.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TestTop100LikedQuestions {
	public static void main(String[] args) {

		TestTop100LikedQuestions test = new TestTop100LikedQuestions();
		
		int[] nums = {2,1,2};
		System.out.println(test.singleNumber2(nums));
		
	}

	/*
	 * Given a non-empty array of integers, every element appears twice except
	 * for one. Find that single one.
	 */
	public int singleNumber2(int[] nums) {
		int a = 0 ;
		for (int i = 0; i < nums.length; i++) {
			a  = a^nums[i];
		}
		return a;
	}
	public int singleNumber(int[] nums) {
		int result = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			}else{
				map.put(num, 1);
			}
		}
		for (Integer key : map.keySet()) {
			if (map.get(key)%2==1) {
				result = key;
				break;
			}
		}
		return result;

	}

	
	
	
	
	
	
	
	
	
	
	
	public List<Integer> findAnagrams2(String s, String p) {
		List<Integer> result = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return result;
		int[] hash = new int[256];
		char[] pp = p.toCharArray();
		for (char i : pp) {
			hash[i]++;
		}
		int left = 0, right = 0, count = p.length();
		while (right < s.length()) {
			if (hash[s.charAt(right++)]-- > 0) // 窗口右移；相应的hash值减小；如果这个位置的Hash值是正的，表示p字符串也包含这个，所以count做减法
				count--;
			if (count == 0)
				result.add(left);// count指示器，为0表示和p对应的hash值完全一致
			if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
				// 如果当窗口大小一定的时候即窗口大小和需要比较的字符串大小一致的时候，将窗口左边的指针向右边移动，移动的同时左边的字符计数因为在第一个if的地方hash值减小过，所以需要执行对应恢复操作，即：hash值增加，count计数值增加。
				count++;
		}
		return result;

	}

	List<String> showCombResult = new ArrayList<String>();
	int listSize = 0;

	public List<Integer> findAnagrams(String s, String p) {
		listSize = p.length();
		Set<Integer> result = new TreeSet<Integer>();
		if (s.length() >= p.length() && s.length() <= 20100 && p.length() <= 20100) {
			String[] array = p.split("");
			showComb(Arrays.asList(array), "");
			for (Iterator iterator = showCombResult.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				if (s.contains(string)) {
					for (int i = 0; i < s.length(); i++) {
						if (i + string.length() <= s.length()) {
							String temp = s.substring(i, i + string.length());
							if (temp.equalsIgnoreCase(string)) {
								result.add(i);
							}
						}
					}
				}
			}

		}
		return new ArrayList<>(result);
	}

	public void showComb(List<String> list, String prefix) {
		if (prefix.length() == listSize) {
			showCombResult.add(prefix);
		}
		for (int i = 0; i < list.size(); i++) {
			List<String> tmp = new LinkedList<String>(list);
			showComb(tmp, prefix + tmp.remove(i));
		}
	}

}
