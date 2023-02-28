# https://leetcode.com/problems/subarray-sum-equals-k/
class Solution:
    def subarraySum(self, nums, k):
        res = 0
        currentSum = 0
        prefixSum = { 0 : 1 }

        for num in nums:
            currentSum += num
            diff = currentSum - k
            res += prefixSum.get(diff, 0)
            prefixSum[currentSum] = 1 + prefixSum.get(currentSum, 0)
        
        return res
        
               

# TEST CASE
solution = Solution()

nums = [1,1,1] 
k = 2
# print(solution.subarraySum(nums, k))

nums = [0,3,1,2,3]
k = 3
print(solution.subarraySum(nums, k))