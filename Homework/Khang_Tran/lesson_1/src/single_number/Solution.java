package single_number;

public class Solution {

    public static int singleNumber(int[] nums) {
        int rs = 0;
        for(int i = 0; i < nums.length; i++) {
            rs ^= nums[i];
        }
        return rs;
    }
}
