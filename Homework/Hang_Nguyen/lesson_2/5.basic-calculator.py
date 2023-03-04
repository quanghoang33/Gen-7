# https://leetcode.com/problems/basic-calculator/
class Solution:
    def calculate(self, s: str) -> int:
        sign = 1 
        num = 0 
        output = 0
        stack = [sign]

        for char in s:
            if char.isdigit():
                num = num * 10 + int(char)
            elif char == '(':
                stack.append(sign)
            elif char == ')':
                stack.pop()
            elif char == '+' or char == '-':
                output += sign * num
                sign = stack[-1] * (-1 if char == '-' else 1)
                num = 0

        return output + sign * num


#TEST CASE
solution = Solution()

s = "1+1"
# print("TEST 1 is " + str(solution.calculate(s) == 2))

s = "2-1+2 "
# print("TEST 2 is " + str(solution.calculate(s) == 3))

s = "(1+(4+5+2)-3)+(6+8)"
# print("TEST 3 is " + str(solution.calculate(s) == 23))

s = "2147483647"
print("TEST 4 is " + str(solution.calculate(s) == 2147483647))