# https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
class Solution(object):
    def search(self, nums, target):
        if len(nums) == 1:
            return nums[0] != target

        left, right = 0, len(nums)-1

        while left <= right:
            
            # remove duplicate ones
            while left < right and nums[left] == nums[left+1]:
                left += 1
            while left < right and nums[right] == nums[right-1]:
                right -= 1
  
            mid= (left+right)//2

            if nums[mid] == target:
                return True

            if nums[left] <= nums[mid]:
                if nums[left] <= target and target <= nums[mid] :
                    right = mid-1
                else:
                    left = mid+1

            else:
                if nums[mid] <= target and target <= nums[right]:
                    left = mid+1
                else:
                    right = mid-1

        # step 5
        return False

# TEST CASE
solution = Solution()

nums = [2,5,6,0,0,1,2] 
target = 5

print(solution.search(nums, target))

target = 3
print(solution.search(nums, target))