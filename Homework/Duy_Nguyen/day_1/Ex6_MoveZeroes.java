package org.global.dev.day_1;

/**
 * @author duyntc 02/2023
 */
public class Ex6_MoveZeroes {
	
	static void swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
	
	/**
	 * Time: O(n)
	 * Space: O(1)
	 */
	public void moveZeroes(int[] nums) {
		int pivot = 0;
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				swap(nums, pivot, i);
				pivot++;
			}
		}
	}
	
}
