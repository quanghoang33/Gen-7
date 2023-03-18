from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [1 for _ in range(n)]
        l, r = 1, 1
        for i in range(n):
            ans[i] *= l
            ans[-1 - i] *= r
            l *= nums[i]
            r *= nums[-1 - i]
        return ans