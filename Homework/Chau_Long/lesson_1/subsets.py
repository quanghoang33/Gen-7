from typing import List


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        ans = []

        def dfs(sub: List[int], left: List[int]):

            if not left:
                ans.append(sub)
                return

            for idx in range(len(left)):
                dfs(sub + [left[idx]], left[idx + 1:])

        dfs([], nums)
        return ans