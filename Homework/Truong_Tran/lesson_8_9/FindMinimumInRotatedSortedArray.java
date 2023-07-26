package lesson7;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }
        while (left < right) {
            mid = (left + right) / 2;
            if (mid == 0) {
                return Math.min(nums[0], nums[1]);
            }
            if (mid == nums.length - 1) {
                return Math.min(nums[nums.length - 2], nums[nums.length - 1]);
            }
            if (nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return nums[left];
    }
}
