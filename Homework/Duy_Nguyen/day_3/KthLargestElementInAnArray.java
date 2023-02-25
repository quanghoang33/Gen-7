package org.global.dev.day_3;

import org.global.dev.common.QuickSelect;

public class KthLargestElementInAnArray {
	
	static public int findKthLargest(int[] nums, int k) {
		return quickSelect(nums, 0, nums.length - 1, k);
	}
	
	static private int quickSelect(int[] arr, int left, int right, int k) {
		
		if (left == right) {
			return arr[left];
		}
		
		int pivot = partition(arr, left, right);
		
		if (pivot == k - 1) {
			return arr[pivot];
		} else if (pivot < k - 1){
			return quickSelect(arr, pivot + 1, right, k);
		} else {
			return quickSelect(arr, left, pivot - 1, k);
		}
		
	}
	
	static private int partition(int[] arr, int begin, int end) {
		
		int pivotVal = arr[end];
		int pivotIdx = begin;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > pivotVal) {
				swap(arr, pivotIdx, i);
				pivotIdx++;
			}
		}
		
		swap(arr, pivotIdx, end);
		
		return pivotIdx;
	}
	
	static private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
