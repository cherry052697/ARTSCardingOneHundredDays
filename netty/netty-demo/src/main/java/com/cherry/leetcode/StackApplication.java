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

	public int minAddToMakeValid2(String S) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '(') {
				stack.push('(');
			} else if (S.charAt(i) == ')') {
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				} else {
					stack.push(S.charAt(i));
				}
			}
		}
		return stack.size();
	}

	/*
	 * 682. Baseball Game
	 * 
	 * You're now a baseball game point recorder.
	 * 
	 * Given a list of strings, each string can be one of the 4 following types:
	 * 
	 * Integer (one round's score): Directly represents the number of points you
	 * get in this round. "+" (one round's score): Represents that the points
	 * you get in this round are the sum of the last two valid round's points.
	 * "D" (one round's score): Represents that the points you get in this round
	 * are the doubled data of the last valid round's points. "C" (an operation,
	 * which isn't a round's score): Represents the last valid round's points
	 * you get were invalid and should be removed. Each round's operation is
	 * permanent and could have an impact on the round before and the round
	 * after.
	 * 
	 * You need to return the sum of the points you could get in all the rounds.
	 */
	public int calPoints(String[] ops) {
		int sum = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (String op : ops) {
			if (op.equals("+")) {
				int top = stack.pop();
				int newtop = top + stack.peek();
				stack.push(top);
				stack.push(newtop);
			} else if (op.equals("C")) {
				stack.pop();
			} else if (op.equals("D")) {
				stack.push(2 * stack.peek());
			} else {
				stack.push(Integer.valueOf(op));
			}
		}
		for(int score : stack) sum += score;
		return sum;
	}

	public static void main(String[] args) {
		StackApplication sa = new StackApplication();
		// System.out.println(sa.binaryConversion(89, 8));
		String[] ops = { "5", "2", "C", "D", "+" };
		System.out.println(sa.calPoints(ops));
	}

}
