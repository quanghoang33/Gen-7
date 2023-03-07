package org.global.dev.day_3;

import java.util.Queue;
import java.util.Stack;

public class BasicCalculator {
	
	/**
	 *
	 * Only + - and ( ) and number
	 *
	 * Example 1:
	 * <p>
	 * Input: s = "1 + 1"
	 * Output: 2
	 * Example 2:
	 * <p>
	 * Input: s = " 2-1 + 2 "
	 * Output: 3
	 * Example 3:
	 * <p>
	 * Input: s = "(1+(4+5+2)-3)+(6+8)"
	 * Output: 23
	 */
	
	/**
	 * IDEA:
	 * <p>
	 * "1+(4+5+2)-3+(6+8)"
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
		Stack<Integer> stack = new Stack<>();
		
		int result = 0;
		int number = 0;
		int sign = 1;
		
		for (int i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			if (Character.isDigit(curChar)) {
				number = number * 10 + (curChar - '0');
			} else if (curChar == '+') {
				result += sign * number;
				number = 0;
				sign = 1;
			} else if (curChar == '-') {
				result += sign * number;
				number = 0;
				sign = -1;
			} else if (curChar == '(') {
				
				// Cache current result
				stack.push(result);
				stack.push(sign);
				
				// Reset for a new operation
				result = 0;
				sign = 1;
			} else if (curChar == ')') {
				result += sign * number;
				result *= stack.pop();
				result += stack.pop();
				number = 0;
			}
			
		}
		
		return result + (sign * number);
	}
	
	
	public static void main(String[] args) {
		System.out.println(calculate("1+1")); // 2
		System.out.println(calculate("1-1")); // 0
		System.out.println(calculate(" 2-1 + 2")); // 3
		System.out.println(calculate("(4+5+2) + (2+3)")); // 16
		System.out.println(calculate("(6+6) + (1+1)")); // 14
	}
}
