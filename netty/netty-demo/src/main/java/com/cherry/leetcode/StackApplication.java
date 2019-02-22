package com.cherry.leetcode;

import java.util.HashMap;
import java.util.Stack;

import com.cherry.netty.utils.JsonUtil;

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
				sum += newtop;
			} else if (op.equals("C")) {
				sum -= stack.pop();
				;
			} else if (op.equals("D")) {
				stack.push(2 * stack.peek());
				sum += stack.peek();
			} else {
				stack.push(Integer.valueOf(op));
				sum += stack.peek();
			}
		}
		return sum;
	}

	/*
	 * 739. Daily Temperatures 
	 * 
	 * Given a list of daily temperatures T, return a
	 * list such that, for each day in the input, tells you how many days you
	 * would have to wait until a warmer temperature. If there is no future day
	 * for which this is possible, put 0 instead.
	 * 
	 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72,
	 * 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
	 * 
	 * Note: The length of temperatures will be in the range [1, 30000]. Each
	 * temperature will be an integer in the range [30, 100].
	 */
	public int[] dailyTemperatures(int[] T) {
		int[] result = new int[T.length];
		for (int i = 0; i < T.length-1; i++) {
			for (int j = i+1; j < result.length; j++) {
				if (T[i] < T[j]) {
					result[i] = j-i;
					break;
				}
			}
		}
		return result;
	}
	
	 public int[] dailyTemperatures2(int[] T) {
	        int[] ans = new int[T.length];
	        Stack<Integer> stack = new Stack<Integer>();
	        for (int i = T.length - 1; i >= 0; --i) {
	            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
	            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
	            stack.push(i);
	        }
	        return ans;
	    }

	public static void main(String[] args) {
		StackApplication sa = new StackApplication();
		// System.out.println(sa.binaryConversion(89, 8));
		String[] ops = { "5", "2", "C", "D", "+" };
//		System.out.println(sa.calPoints(ops));
		int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
		System.out.println(JsonUtil.toJson(sa.dailyTemperatures(nums)));
		/*byte x = 126;
		byte y = (byte) (x+3);
		System.out.println(y);
		y = (byte) (y-2);
		System.out.println(y);*/
	}

}
