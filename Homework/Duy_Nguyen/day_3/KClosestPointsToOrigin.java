package org.global.dev.day_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KClosestPointsToOrigin {
	 
	 /**
	  * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
	  * Output: [[3,3],[-2,4]]
	  * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
	  */
	
	 public static void main(String[] args) {
		 KClosestPointsToOrigin origin = new KClosestPointsToOrigin();
		 int[][] arr = {
			 {3, 3},
			 {5, -1},
			 {-2, 4}
		 };
		 System.out.println(Arrays.deepToString(origin.kClosest(arr, 2)));
		
	 }
	
	static void swap(double[] arr, int first, int second) {
		double temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
	
	public int[][] kClosest(int[][] points, int k) {
		double[] distances = new double[points.length];
		
		for (int i = 0; i < points.length; i++) {
			int[] point = points[i];
			double dist = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
			distances[i] = dist;
		}
		
		double smallestKDist = findKthSmallest(distances, k);
		
		List<int[]> list = new ArrayList<>();
		
		for (int[] point : points) {
			double dist = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
			if (dist <= smallestKDist) {
				list.add(point);
			}
		}
		
		int[][] result = new int[list.size()][];
		
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	// Finding kth smallest element
	public double findKthSmallest(double[] arr, int k) {
		return select(arr, 0, arr.length - 1, k);
	}
	
	public double select(double[] arr, int begin, int end, int k) {
		if (begin == end) {
			return arr[begin];
		}
		
		int partition = partition(arr, begin, end);
		
		if (partition == k - 1) {
			return arr[partition];
		} else if (partition < k - 1) {
			return select(arr, partition + 1, end, k);
		} else {
			return select(arr, begin, partition - 1, k);
		}
	}
	
	public int partition(double[] arr, int begin, int end) {
		int loc = begin;
		double val = arr[end];
		
		
		for (int i = begin; i < end; i++) {
			if (arr[i] < val) {
				swap(arr, loc, i);
				loc++;
			}
		}
		
		swap(arr, end, loc);
		return loc;
	}
	
}
