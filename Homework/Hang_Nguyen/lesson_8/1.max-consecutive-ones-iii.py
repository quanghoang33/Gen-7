# https://leetcode.com/problems/max-consecutive-ones-iii/
class Solution():
    def longestOnes(self, nums, k):
        i = 0
        for j in range(len(nums)):
            k -= 1 - nums[j]
            if k < 0:
                k += 1 - nums[i]
                i += 1
        return j - i + 1

# TEST CASE
solution = Solution()

nums = [1,1,1,0,0,0,1,1,1,1,0]
k = 2
print('Test 1 is ' + str(solution.longestOnes(nums, k)))

nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]
k = 3
print('Test 2 is ' + str(solution.longestOnes(nums, k)))