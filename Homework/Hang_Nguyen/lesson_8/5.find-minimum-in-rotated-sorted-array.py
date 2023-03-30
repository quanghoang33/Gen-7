# https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
class Solution(object):
    def findMin(self, nums):
        left, right = 0, len(nums)-1
        while left < right:
            mid = (left + right) // 2
            if nums[mid] > nums[right]:
                left = mid + 1
            else:
                right = mid
        return nums[left]


# TEST CASE
solution = Solution()

nums = [3,4,5,1,2]

print(solution.findMin(nums))

nums = [4,5,6,0,1,2,3]
print(solution.findMin(nums))

nums = [11,13,15,17]
print(solution.findMin(nums))