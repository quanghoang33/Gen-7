from typing import List


class Solution:
    def sortArrayByParity(self, nums: List[int]) -> List[int]:
        i, j = 0, len(nums) - 1
        while i < j:
            if nums[i] % 2 > nums[j] % 2:
                nums[i], nums[j] = nums[j], nums[i]

            if nums[i] % 2 == 0: i += 1
            if nums[j] % 2 == 1: j -= 1

        return nums

if __name__ == "__main__":
    sol = Solution()
    print(sol.sortArrayByParity([3, 1, 2, 4]) in [[2, 4, 3, 1], [4, 2, 3, 1], [2, 4, 1, 3], [4, 2, 1, 3]])
    print(sol.sortArrayByParity([0]) in [[0]])