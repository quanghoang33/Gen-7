class Solution:
    def get_bit(self, num, bit):
        return 0 if num & (1 << bit) == 0 else 1

    def subsets(self, nums):
        subsets_size = pow(2, len(nums))
        subsets = []
        for i in range(0, subsets_size):
            subs = []
            for j in range(0, len(nums)):
                # print(str(i) + "------" + str(j) + "------" + str(self.get_bit(i, j)))
                if self.get_bit(i, j) == 1:
                    subs.append(nums[j])
            # print("---------------------------------------------------------")
            subsets.append(subs)
        return subsets


# TEST CASE
solution = Solution()

nums = [1,2,3]
print("Subsets for TEST 1 is: " + str(solution.subsets(nums)))

nums = [0]
print("Subsets for TEST 2 is: " + str(solution.subsets(nums)))