package com.cherry.leetcode;

import java.util.HashMap;
import java.util.Stack;

public class StackApplication {

	String binaryConversion(int count, int n) {
		StringBuffer str = new StringBuffer();
		while (count > 0) {
			str.append(count % n);
			count = count / n;
		}
		return str.toString();
	}

	boolean parenthesisMatching(String str) {
		Stack<Character> stack = new Stack<Character>();
		HashMap<Character, Character> mappings = new HashMap<Character, Character>();
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (mappings.containsKey(c)) {
				char topElement = stack.empty() ? '#' : stack.pop();
				if (topElement != mappings.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	/*
	 * 921. Minimum Add to Make Parentheses Valid
	 * 
	 * Given a string S of '(' and ')' parentheses, we add the minimum number of
	 * parentheses ( '(' or ')', and in any positions ) so that the resulting
	 * parentheses string is valid.
	 * 
	 * Formally, a parentheses string is valid if and only if:
	 * 
	 * It is the empty string, or It can be written as AB (A concatenated with
	 * B), where A and B are valid strings, or It can be written as (A), where A
	 * is a valid string. Given a parentheses string, return the minimum number
	 * of parentheses we must add to make the resulting string valid.
	 */
	public int minAddToMakeValid(String S) {
		int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            bal += S.charAt(i) == '(' ? 1 : -1;
            if (bal == -1) {
                ans++;
                bal++;
            }
        }

        return ans + bal;
	}

	public static void main(String[] args) {
		StackApplication sa = new StackApplication();
		System.out.println(sa.binaryConversion(89, 8));
	}

}
