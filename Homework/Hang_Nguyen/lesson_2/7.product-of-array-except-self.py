# https://leetcode.com/problems/product-of-array-except-self/
class Solution:
    def productExceptSelf(self, nums):
        prefix = [1 for _ in range(len(nums) + 1)]
        for i in range(0,len(nums)):
            prefix[i + 1] = prefix[i] * nums[i] 
        # print(prefix)
        
        postfix = [1 for _ in range(len(nums) + 1)]
        for i in range(len(nums) - 1, -1, -1):
            postfix[i] = postfix[i+1]*nums[i] 
        # print(postfix)

        output = []
        for i in range(len(nums)):
            output.append(prefix[i]*postfix[i+1])
        
        return output


# TEST CASE
solution = Solution()

nums = [1,2,3,4]
print("TEST 1 is " + str(solution.productExceptSelf(nums)))

nums = [-1,1,0,-3,3]
print("TEST 2 is " + str(solution.productExceptSelf(nums)))
