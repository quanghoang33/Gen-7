class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& nums) {
        int len = nums.size(), p = 0;
        for (int i=0; i<len; i++) {
            if (nums[p]%2) {
                nums.push_back(nums[p]);
                nums.erase(nums.begin()+p);
            } else p++;
        }
        return nums;
    }
};