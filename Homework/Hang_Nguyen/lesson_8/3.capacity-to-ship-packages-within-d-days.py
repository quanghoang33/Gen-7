# https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
class Solution():
    def shipWithinDays(self, weights, days):
        capacityLow = max(weights)
        capacityHigh = sum(weights)
        while capacityLow < capacityHigh:
            middle, need, current = capacityLow + (capacityHigh - capacityLow)//2, 1, 0

            for w in weights:
                if current + w > middle:
                    need += 1
                    current = 0
                current += w

            if need > days: 
                capacityLow = middle + 1
            else: 
                capacityHigh = middle
                
        return capacityLow

# TEST CASE
solution = Solution()

weights = [1,2,3,4,5,6,7,8,9,10] # middle = 32
days = 5

print(str(solution.shipWithinDays(weights, days)))

weights = [3,2,2,4,1,4] 
days = 3
print(str(solution.shipWithinDays(weights, days)))

weights = [1,2,3,1,1] 
days = 4
print(str(solution.shipWithinDays(weights, days)))