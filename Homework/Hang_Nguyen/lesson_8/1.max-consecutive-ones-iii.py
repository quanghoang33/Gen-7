# https://leetcode.com/problems/max-consecutive-ones-iii/
class Solution():
    def longestOnes(self, nums, k):
        left = 0
        for right in range(len(nums)):
            if nums[right] == 0: k -= 1
            if k < 0:
                if nums[left] == 0: k += 1
                left += 1
        return right - left + 1

# TEST CASE
solution = Solution()

nums = [1,1,1,0,0,0,1,1,1,1,0]
k = 2
print('Test 1 is ' + str(solution.longestOnes(nums, k)))

nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]
k = 3
print('Test 2 is ' + str(solution.longestOnes(nums, k)))