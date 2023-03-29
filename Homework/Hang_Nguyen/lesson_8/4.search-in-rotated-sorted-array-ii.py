# https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
class Solution(object):
    def search(self, nums, target):
        # If the length of the given array list is 1, then check the first element and return accordingly
        if len(nums)==1:
            if nums[0]!=target:
                return False
            else:
                return True

        left=0
        right=len(nums)-1
        # binary search 
        while(left<=right):

            # shifting to remove duplicate elements
            while left<right and nums[left] == nums[left+1]:
                left+=1
            while left<right and nums[right] == nums[right-1]:
                right-=1

            # step 1 calculate the mid    
            mid=(left+right)//2

            #step 2
            if nums[mid]==target:
                return True

            #step 3
            elif nums[left]<=nums[mid]:
                if nums[mid]>=target and nums[left]<=target:
                    right=mid-1
                else:
                    left=mid+1

            # step 4
            else:
                if target>=nums[mid] and target<=nums[right]:
                    left=mid+1
                else:
                    right=mid-1

        # step 5
        return False

# TEST CASE
solution = Solution()

nums = [2,5,6,0,0,1,2] 
target = 0

print(solution.search(nums, target))