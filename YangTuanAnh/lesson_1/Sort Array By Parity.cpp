class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& nums) {
        int len = nums.size(), p = 0;
        while (nums[p]%2==0 && p+1<len) p++;
        for (int i=0; i<len; i++) {
            if (nums[i]%2==0 && i > p) {
                swap(nums[i], nums[p]);
                while (nums[p]%2==0 && p+1<len) p++;
            }
        }
        return nums;
    }
};