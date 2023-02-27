package org.global.dev.day_3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
	
	// https://leetcode.com/problems/sliding-window-maximum/
	
	
	/**
	 * Example 1:
	 * <p>
	 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
	 * Output: [3,3,5,5,6,7]
	 * Explanation:
	 * Window position                Max
	 * ---------------               -----
	 * [1  3  -1] -3  5  3  6  7       3
	 * 1 [3  -1  -3] 5  3  6  7       3
	 * 1  3 [-1  -3  5] 3  6  7       5
	 * 1  3  -1 [-3  5  3] 6  7       5
	 * 1  3  -1  -3 [5  3  6] 7       6
	 * 1  3  -1  -3  5 [3  6  7]      7
	 * Example 2:
	 * <p>
	 * Max 3
	 * <p>
	 * Input: nums = [1], k = 1
	 * Output: [1]
	 *
	 * @param nums
	 * @param k
	 * @return
	 */
	
	static public int[] maxSlidingWindow(int[] nums, int k) {
		
		PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
		
		for (int i = 0; i < k; i++) {
			heap.add(nums[i]);
		}
		System.out.println("I " + 0 + " heap " + Arrays.toString(heap.toArray()));
		
		int[] result = new int[nums.length - k + 1];
		
		for (int i = k; i < nums.length; i++) {
			result[i - k] = heap.peek();
			heap.remove(nums[i - k]);
			heap.add(nums[i]);
			System.out.println("I " + i + " heap " + Arrays.toString(heap.toArray()));
		}
		result[nums.length - k] = heap.peek();
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
		System.out.println(Arrays.toString(arr)); //  3 3 5 5 6 7
	}
	
}
