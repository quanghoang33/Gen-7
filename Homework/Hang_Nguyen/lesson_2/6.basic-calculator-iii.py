# https://leetcode.com/problems/basic-calculator-iii/

class Solution:
    def cal(self, operator, second, first):
        if operator == "+":
            return first + second
        elif operator == "-":
            return first - second
        elif operator == "*":
            return first * second
        elif operator == "/":
            return first // second
    
    def shouldCalculatePrecedence(self, currentOperator, latestOperator):
        if latestOperator == "(" or latestOperator == ")":
            return False
        if (currentOperator == "*" or currentOperator == "/") and (latestOperator == "+" or latestOperator == "-"):
            return False
        return True

    def calculate(self, s):
        if not s: 
            return 0
        nums, operators = [], []
        i = 0
        while i < len(s):
            char = s[i]
            if char == " ":
                i += 1
                continue
            elif char.isdigit():
                num = int(char)
                while i < len(s) - 1 and s[i + 1].isdigit():
                    num = num * 10 + int(s[i + 1])
                    i += 1
                nums.append(num)
            elif char == "(":
                operators.append(char)
            elif char == ")":
                while operators[-1] != "(":
                    nums.append(self.cal(operators.pop(), nums.pop(), nums.pop()))
                operators.pop()
            elif char in ["+", "-", "*", "/"]:
                while len(operators) != 0 and self.shouldCalculatePrecedence(char, operators[-1]):
                    nums.append(self.cal(operators.pop(), nums.pop(), nums.pop()))
                operators.append(char)
            i += 1

        while len(operators) > 0:
            nums.append(self.cal(operators.pop(), nums.pop(), nums.pop()))

        return nums.pop()



# TEST CASE
solution = Solution()

s = "1+1"
print("TEST 1 is " + str(solution.calculate(s) == 2))

s = "6-4/2"
print("TEST 2 is " + str(solution.calculate(s) == 4))

s = "2*(5+5*2)/3+(6/2+8)"
print("TEST 3 is " + str(solution.calculate(s) == 21))