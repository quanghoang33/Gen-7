package com.example.hibernate;

import java.util.HashMap;
import java.util.Map;

public class NumSubMatrixSumTarget {
    public int numSubMatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] prefixSum = new int[m + 1][n + 1];
        int count = 0;

        // tính toán ma trận prefix sum
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        // tính toán số lượng submatrix có tổng bằng target
        for (int top = 1; top <= m; top++) {
            for (int bottom = top; bottom <= m; bottom++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1); // thêm 1 vào để tính trường hợp prefixSum[i][j] = target
                for (int j = 1; j <= n; j++) {
                    int curSum = prefixSum[bottom][j] - prefixSum[top - 1][j];
                    int diff = curSum - target;
                    if (map.containsKey(diff)) {
                        count += map.get(diff);
                    }
                    map.put(curSum, map.getOrDefault(curSum, 0) + 1);
                }
            }
        }

        return count;
    }
}
