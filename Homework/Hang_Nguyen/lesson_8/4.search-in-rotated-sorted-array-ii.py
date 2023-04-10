# https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
class Solution(object):
    def search(self, nums, target):
        l, r = 0, len(nums)-1
        while l <= r:
            mid = l + (r-l)/2
            if nums[mid] == target:
                return True
            elif nums[l] < nums[mid]: #[left..mid] increase
                if nums[l] <= target and target < nums[mid]: # target between [left..mid]
                    r = mid
                else:
                    l = mid + 1
            elif nums[l] > nums[mid]: #[mid..right] increase
                if nums[mid] < target and target <= nums[r]: # target between [mid..right]
                    l = mid + 1
                else:
                    r = mid
            else:
                l += 1
        return False

# TEST CASE
solution = Solution()

nums = [2,5,6,0,0,1,2] 
target = 5

print(solution.search(nums, target))

target = 3
print(solution.search(nums, target))