from collections import deque
from typing import List


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        deq, res = deque(), []

        for i in range(len(nums)):
            while deq and nums[i] > nums[deq[-1]]:
                deq.pop()
            deq.append(i)
            if i >= k - 1:
                res.append(nums[deq[0]])
                if deq[0] <= i - k + 1:
                    deq.popleft()

        return res


if __name__ == "__main__":
    sol = Solution()
    print(sol.maxSlidingWindow([1, 3, -1, -3, 5, 3, 6, 7], 3) == [3, 3, 5, 5, 6, 7])
    print(sol.maxSlidingWindow([1], 1) == [1])
