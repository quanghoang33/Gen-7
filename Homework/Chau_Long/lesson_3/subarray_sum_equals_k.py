from collections import defaultdict
from typing import List


class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        ans = prefsum = 0
        d= defaultdict(int)
        d[0] = 1

        for num in nums:
            prefsum += num
            ans += d[prefsum - k]
            d[prefsum] += 1

        return ans

if __name__ == "__main__":
    sol = Solution()
    print(sol.subarraySum([1, 1, 1], 2) == 2)
    print(sol.subarraySum([1, 2, 3], 3) == 2)