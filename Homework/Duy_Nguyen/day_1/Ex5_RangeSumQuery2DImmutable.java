package org.global.dev.day_1;

/**
 * @author duyntc 02/2023
 */
public class Ex5_RangeSumQuery2DImmutable {
	
	static class NumMatrix {
		
		int[][] arr;
		int row;
		int col;
		
		/**
		 * https://leetcode.com/problems/range-sum-query-2d-immutable/description/
		 * Intuitive just the same as 1D version but run each row
		 */
		
		/**
		 * Time: O(n + m + q)
		 * Space: O(n + m)
		 */
		public NumMatrix(int[][] matrix) {
			row = matrix.length;
			col = matrix[0].length;
			arr = new int[row][col];
			
			for (int i = 0; i < row; i++) {
				arr[i][0] = matrix[i][0];
				for (int j = 1; j < col; j++) {
					arr[i][j] = matrix[i][j] + arr[i][j - 1];
				}
			}
		}
		
		public int sumRegion(int row1, int col1, int row2, int col2) {
			int sum = 0;
			if (col1 == 0) {
				for (int row = row1; row <= row2; row++) {
					sum += arr[row][col2];
				}
			} else {
				for (int row = row1; row <= row2; row++) {
					sum += arr[row][col2] - arr[row][col1 - 1];
				}
			}
			return sum;
		}
	}
	
}
