package org.global.dev.day_1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyntc 02/2023
 */
public class Ex13_HappyNumber {
	
	public int takeSquare(int n) {
		List<Integer> segment = new ArrayList<>();
		while (n != 0) {
			segment.add(n % 10);
			n /= 10;
		}
		int total = 0;
		for (Integer val : segment) {
			total += Math.pow(val, 2);
		}
		return total;
	}
	
	public boolean isHappy(int n) {
		int slow = takeSquare(n);
		int fast = takeSquare(takeSquare(n));
		
		while (true) {
			if (slow == fast && fast != 1)
				return false;
			if (fast == 1)
				return true;
			
			slow = takeSquare(slow);
			fast = takeSquare(takeSquare(fast));
		}
	}
}
