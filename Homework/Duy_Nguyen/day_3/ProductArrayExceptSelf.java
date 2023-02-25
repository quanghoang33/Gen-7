package org.global.dev.day_3;

public class ProductArrayExceptSelf {
	
	
	public int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];
		int[] leftProduct = new int[nums.length];
		int[] rightProduct = new int[nums.length];
		leftProduct[0] = 1;
		rightProduct[0] = 1;
		
		for (int i = 1; i < nums.length; i++) {
			leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
			rightProduct[i] = rightProduct[i - 1] * nums[nums.length - i];
		}
		
		for (int i = 0; i < nums.length; i++) {
			result[i] = leftProduct[i] * rightProduct[nums.length - i - 1];
		}
		return result;
	}
	
}
