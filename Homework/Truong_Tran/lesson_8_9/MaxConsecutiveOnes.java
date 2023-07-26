package lesson7;

public class MaxConsecutiveOnes {
    public int longestOnes(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int res = 0;
        while (end < nums.length) {
            if (nums[end] == 0) {
                if (k > 0) {
                    k--;
                    end++;
                } else {
                    res = Math.max(res, end - start);
                    if (nums[start] == 0) {
                        k++;
                    }
                    start++;
                }
            } else {
                end++;
            }
        }
        return Math.max(end - start, res);
    }
}
