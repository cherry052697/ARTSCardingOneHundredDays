package com.cherry.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

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
	 * Given a list of daily temperatures T, return a list such that, for each
	 * day in the input, tells you how many days you would have to wait until a
	 * warmer temperature. If there is no future day for which this is possible,
	 * put 0 instead.
	 * 
	 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72,
	 * 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
	 * 
	 * Note: The length of temperatures will be in the range [1, 30000]. Each
	 * temperature will be an integer in the range [30, 100].
	 */
	public int[] dailyTemperatures(int[] T) {
		int[] result = new int[T.length];
		for (int i = 0; i < T.length - 1; i++) {
			for (int j = i + 1; j < result.length; j++) {
				if (T[i] < T[j]) {
					result[i] = j - i;
					break;
				}
			}
		}
		return result;
	}

	public int[] dailyTemperatures2(int[] T) {
		int[] result = new int[T.length];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = T.length - 1; i >= 0; --i) {
			while (!stack.isEmpty() && T[i] >= T[stack.peek()])
				stack.pop();
			result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
			stack.push(i);
		}
		return result;
	}

	/*
	 * 726. Number of Atoms
	 * 
	 * Given a chemical formula (given as a string), return the count of each
	 * atom.
	 * 
	 * An atomic element always starts with an uppercase character, then zero or
	 * more lowercase letters, representing the name.
	 * 
	 * 1 or more digits representing the count of that element may follow if the
	 * count is greater than 1. If the count is 1, no digits will follow. For
	 * example, H2O and H2O2 are possible, but H1O2 is impossible.
	 * 
	 * Two formulas concatenated together produce another formula. For example,
	 * H2O2He3Mg4 is also a formula.
	 * 
	 * A formula placed in parentheses, and a count (optionally added) is also a
	 * formula. For example, (H2O2) and (H2O2)3 are formulas.
	 * 
	 * Given a formula, output the count of all elements as a string in the
	 * following form: the first name (in sorted order), followed by its count
	 * (if that count is more than 1), followed by the second name (in sorted
	 * order), followed by its count (if that count is more than 1), and so on.
	 * 
	 * Input:formula = "Mg(OH)2" Output: "H2MgO2" Explanation: The count of
	 * elements are {'H': 2, 'Mg': 1, 'O': 2}
	 * 
	 * Input: formula = "K4(ON(SO3)2)2" Output: "K4N2O14S4" Explanation: The
	 * count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
	 */
	int index;

	public String countOfAtoms(String formula) {
		StringBuilder ans = new StringBuilder();
		index = 0;
		Map<String, Integer> count = parse(formula);
		for (String name : count.keySet()) {
			ans.append(name);
			int multiplicity = count.get(name);
			if (multiplicity > 1)
				ans.append("" + multiplicity);
		}
		return new String(ans);
	}

	public Map<String, Integer> parse(String formula) {
		int len = formula.length();
		Map<String, Integer> count = new TreeMap<String, Integer>();
		while (index < len && formula.charAt(index) != ')') {
			if (formula.charAt(index) == '(') {
				index++;
				for (Map.Entry<String, Integer> entry : parse(formula).entrySet()) {
					count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
				}
			} else {
				int iStart = index++;
				while (index < len && Character.isLowerCase(formula.charAt(index)))
					index++;
				String name = formula.substring(iStart, index);
				iStart = index;
				while (index < len && Character.isDigit(formula.charAt(index)))
					index++;
				int multiplicity = iStart < index ? Integer.parseInt(formula.substring(iStart, index)) : 1;
				count.put(name, count.getOrDefault(name, 0) + multiplicity);
			}
		}
		int iStart = ++index;
		while (index < len && Character.isDigit(formula.charAt(index)))
			index++;
		if (iStart < index) {
			int multiplicity = Integer.parseInt(formula.substring(iStart, index));
			for (String key : count.keySet()) {
				count.put(key, count.get(key) * multiplicity);
			}
		}
		return count;
	}

	public String countOfAtoms2(String formula) {
		int N = formula.length();
		Stack<Map<String, Integer>> stack = new Stack<Map<String,Integer>>();
		stack.push(new TreeMap<String, Integer>());

		for (int i = 0; i < N;) {
			if (formula.charAt(i) == '(') {
				stack.push(new TreeMap<String, Integer>());
				i++;
			} else if (formula.charAt(i) == ')') {
				Map<String, Integer> top = stack.pop();
				int iStart = ++i, multiplicity = 1;
				while (i < N && Character.isDigit(formula.charAt(i)))
					i++;
				if (i > iStart)
					multiplicity = Integer.parseInt(formula.substring(iStart, i));
				for (String c : top.keySet()) {
					int v = top.get(c);
					stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
				}
			} else {
				int iStart = i++;
				while (i < N && Character.isLowerCase(formula.charAt(i)))
					i++;
				String name = formula.substring(iStart, i);
				iStart = i;
				while (i < N && Character.isDigit(formula.charAt(i)))
					i++;
				int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
				stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
			}
		}

		StringBuilder ans = new StringBuilder();
		for (String name : stack.peek().keySet()) {
			ans.append(name);
			int multiplicity = stack.peek().get(name);
			if (multiplicity > 1)
				ans.append("" + multiplicity);
		}
		return new String(ans);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * byte x = 126; byte y = (byte) (x+3); System.out.println(y); y =
		 * (byte) (y-2); System.out.println(y);
		 */
		StackApplication sa = new StackApplication();
		// System.out.println(sa.binaryConversion(89, 8));
		String[] ops = { "5", "2", "C", "D", "+" };
		// System.out.println(sa.calPoints(ops));
		int[] nums = { 73, 74, 75, 71, 69, 72, 76, 73 };
		// System.out.println(JsonUtil.toJson(sa.dailyTemperatures(nums)));
		System.out.println(sa.countOfAtoms2("K4(ON(SO3)2)2"));
		System.out.println(sa.countOfAtoms2("Mg(OH)2"));

	}

}
