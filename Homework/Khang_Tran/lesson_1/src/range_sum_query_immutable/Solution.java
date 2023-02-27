package range_sum_query_immutable;

import java.util.Arrays;

public class Solution {

    private int[] nums;

    public Solution(int[] nums) {
        this.nums = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            this.nums[i] = nums[i];
            if (i > 0) {
                this.nums[i] += this.nums[i - 1];
            }
        }
    }

    public int sumRange(int left, int right) {
        return this.nums[right] - (left > 0 ? this.nums[left - 1] : 0);
    }
}
