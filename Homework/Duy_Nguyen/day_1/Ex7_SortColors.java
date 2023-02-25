package org.global.dev.day_1;

/**
 * @author duyntc 02/2023
 */
public class Ex7_SortColors {
	
	static void swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
	
	/**
	 * https://leetcode.com/problems/sort-colors/
	 * Time: O(n)
	 * Space: O(1)
	 */
	public void sortColors(int[] nums) {
		int left = 0, i = 0;
		int right = nums.length - 1;
		
		while (i <= right) {
			int temp = nums[i];
			if (nums[i] == 2) {
				nums[i] = nums[right];
				nums[right] = temp;
				right--;
			} else if (nums[i] == 0) {
				nums[i] = nums[left];
				nums[left] = temp;
				left++;
				i++;
			} else {
				i++;
			}
		}
	}
	
	// Alternative approach
	public void sortColorsAlternative(int[] nums) {
		int pivot = 0;
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				swap(nums, pivot, i);
				pivot++;
			}
		}
		
		pivot = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] == 2) {
				swap(nums, pivot, i);
				pivot--;
			}
		}
	}
}
