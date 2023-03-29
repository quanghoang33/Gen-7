# https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
class Solution():
    def shipWithinDays(self, weights, days):
        low = max(weights)
        high = sum(weights)
        while low < high:
            mid, need, cur = low + (high-low)//2, 1, 0
            for w in weights:
                if cur + w > mid:
                    need += 1
                    cur = 0
                cur += w
            if need > days: low = mid + 1
            else: high = mid
        return low

# TEST CASE
solution = Solution()

weights = [1,2,3,4,5,6,7,8,9,10]
days = 5

print(str(solution.shipWithinDays(weights, days)))