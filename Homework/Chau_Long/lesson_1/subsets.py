from typing import List


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        ans = []
        n = len(nums)

        def dfs(sub: List[int], idx: int):
            ans.append(sub)

            if idx == n:
                return

            for i in range(idx, n):
                dfs(sub + [nums[i]], i + 1)

        dfs([], 0)
        return ans