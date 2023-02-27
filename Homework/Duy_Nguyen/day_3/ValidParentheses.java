package org.global.dev.day_3;

import java.util.Stack;

public class ValidParentheses {
	
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (c == '[' || c == '{' || c == '(') {
				stack.add(c);
			}
			
			if (c == ']') {
				if (stack.isEmpty() || stack.peek() != '[') return false;
				stack.pop();
			}
			
			if (c == '}') {
				if (stack.isEmpty() || stack.peek() != '{') return false;
				stack.pop();
			}
			
			if (c == ')') {
				if (stack.isEmpty() || stack.peek() != '(') return false;
				stack.pop();
			}
		}
		return stack.size() == 0;
	}
	
}
