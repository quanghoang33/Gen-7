# https://leetcode.com/problems/sliding-window-maximum/


import collections


class Solution:
    def maxSlidingWindow(self, nums, k):
        res = []
        queue = collections.deque()
        left = right = 0

        while right < len(nums):
            # pop index of smaller value
            while queue and nums[queue[-1]] < nums[right]:
                queue.pop()
            queue.append(right)

            # remove index of left value
            if left > queue[0]:
                queue.popleft()

            if right + 1 >= k:
                res.append(nums[queue[0]])
                left += 1
            right += 1
        
        return res

# TEST CASE
solution = Solution()

nums = [1,3,-1,-3,5,3,6,7] 
k = 3
# print("TEST 1 is " + str(solution.maxSlidingWindow(nums, k)))

nums = [1] 
k = 1
# print("TEST 2 is " + str(solution.maxSlidingWindow(nums, k)))

nums = [1,-1]
k = 1
# print("TEST 3 is " + str(solution.maxSlidingWindow(nums, k)))

nums = [3,1,-1,-3] 
k = 2
print("TEST 4 is " + str(solution.maxSlidingWindow(nums, k)))
