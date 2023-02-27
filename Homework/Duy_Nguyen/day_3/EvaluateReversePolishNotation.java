package org.global.dev.day_3;

import java.util.Stack;

public class EvaluateReversePolishNotation {
	
	static Stack<Integer> stack = new Stack<>();
	
	static int calculate(int a, int b, String operator) {
		switch (operator) {
			case ("+"):
				return a + b;
			case ("-"):
				return a - b;
			case ("*"):
				return a * b;
			case ("/"):
				return a / b;
			default:
				return 0;
		}
	}
	
	static public int evalRPN(String[] tokens) {
		for (String token : tokens) {
			if (token.equals("+") ||
				    token.equals("-") ||
				    token.equals("/") ||
				    token.equals("*")) {
				
				int second = stack.pop();
				int first = stack.pop();
				int result = calculate(first, second, token);
				stack.push(result);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.peek();
	}
	
}
