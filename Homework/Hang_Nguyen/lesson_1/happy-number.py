class Solution:
    def squareSumDigit(self, n):
        s = 0
        while n:
            s += (n%10) * (n%10)
            n = int(n/10)
        return s

    def isHappy(self, n):
        slow = n
        fast = n
        while True:
            slow = self.squareSumDigit(slow)
            fast = self.squareSumDigit(self.squareSumDigit(fast))
            if slow == fast: break
        return slow == 1

# TEST CASE
solution = Solution()

print("TEST 1 is " + str(solution.isHappy(19)))
print("TEST 2 is " + str(solution.isHappy(2)))
print("TEST 3 is " + str(solution.isHappy(3)))
print("TEST 4 is " + str(solution.isHappy(7)))