package org.global.dev.day_3;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
	
	public String removeDuplicates(String s) {
		Stack<Character> stack = new Stack<>();
		stack.add(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			
			if (stack.isEmpty()) {
				stack.add(s.charAt(i));
			} else {
				if (!stack.peek().equals(s.charAt(i))) {
					stack.add(s.charAt(i));
				} else {
					stack.pop();
				}
			}
		}
		
		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty()) {
			builder.append(stack.pop());
		}
		
		return builder.reverse().toString();
	}
	
	
}
