class Solution:
    def sortArrayByParity(self, nums):
        last = len(nums) - 1
        begin = 0
        while begin < last: 
            if nums[begin] % 2 != 0:
                nums[begin], nums[last] = nums[last], nums[begin]
                last -=1           
            else: 
                begin +=1           
        return nums


# TEST CASE
solution = Solution()

nums = [3,1,2,4]
print("TEST 1 is " + str(solution.sortArrayByParity(nums)))

nums = [0]
print("TEST 2 is " + str(solution.sortArrayByParity(nums)))