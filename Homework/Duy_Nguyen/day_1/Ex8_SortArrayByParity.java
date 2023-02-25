package org.global.dev.day_1;

/**
 * @author duyntc 02/2023
 */
public class Ex8_SortArrayByParity {
	
	static void swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
	
	/**
	 * https://leetcode.com/problems/sort-array-by-parity/
	 * Time: O(n)
	 * Space: O(1)
	 */
	public int[] sortArrayByParity(int[] nums) {
		int pivot = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 2 == 0) {
				swap(nums, pivot, i);
				pivot++;
			}
		}
		
		pivot = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] % 2 != 0) {
				swap(nums, pivot, i);
				pivot--;
			}
		}
		
		return nums;
	}
}
