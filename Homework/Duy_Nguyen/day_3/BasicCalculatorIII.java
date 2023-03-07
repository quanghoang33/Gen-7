package org.global.dev.day_3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BasicCalculatorIII {
	
	/**
	 *
	 * Example 1:
	 *
	 * Input: s = "1+1"
	 * Output: 2
	 * Example 2:
	 *
	 * Input: s = "6-4/2"
	 * Output: 4
	 * Example 3:
	 *
	 * Input: s = "2*(5+5*2)/3+(6/2+8)"
	 * Output: 21
	 */
	
	/**
	 * IDEA:
	 * <p>
	 * 2 * ( 5 + 5 * 2 ) / 3 + ( 6 / 2 + 8 )
	 * <p>
	 * Declare:
	 * - number
	 * - sign
	 * - result
	 * <p>
	 * Case number: than update number = number * 10 + curChar - '0'
	 * Case + :  update result += sign * number than reset number => update sign = 1
	 * Case - :  update result += sign * number than reset number => update sign = -1
	 * Case ( : temporary cache current operation -> push to stack result, sign, reset result, sign
	 * Case ) : update result += sign * number => update with previous cache result:
	 * result *= stack.pop // for sign
	 * result += stack.pop // for previous result, reset number = 0
	 */
	
	static public int calculate(String s) {
		Deque<Character> deque = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			deque.offer(s.charAt(i));
		}
		deque.offer('+');
		return process(deque);
	}
	
	static int process(Deque<Character> deque) {
		
		int number = 0;
		char prevOp = '+';
		Stack<Integer> stack = new Stack<>();
		
		while (!deque.isEmpty()) {
			Character cur = deque.poll(); // Get first
			
			if (Character.isDigit(cur)) {
				number = number * 10 + (cur - '0');
			} else if (cur == '(') {
				// Call recursive
				number = process(deque);
			} else if (cur == ')') {
				break;
			} else if ("+-*/".indexOf(cur) != -1) {
				eval(prevOp, stack, number);
				prevOp = cur;
				number = 0;
			}
		}
		
		eval(prevOp, stack, number);
		return stack.stream().mapToInt(a -> a).sum();
	}
	
	// Formula: stack[prev] [op] currentNumber [newOp]
	// prev is '+' : stack.push( + currentNumber)
	// prev is '-' : stack.push( - currentNumber)
	// prev is '*' : stack.push( stack.pop * currentNumber)
	// prev is '/' : stack.push( stack.pop / currentNumber)
	
	static void eval(char op, Stack<Integer> stack, int number) {
		switch (op) {
			case '+':
				stack.push(number);
				break;
			case '-':
				stack.push(-number);
				break;
			case '*':
				stack.push(stack.pop() * number);
				break;
			case '/':
				stack.push(stack.pop() / number);
				break;
			default:
				break;
		}
	}
}
