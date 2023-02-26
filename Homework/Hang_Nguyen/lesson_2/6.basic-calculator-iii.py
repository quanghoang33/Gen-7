# https://leetcode.com/problems/basic-calculator-iii/

class Solution:
    def operation(self, op, second, first):
        if op == "+":
            return first + second
        elif op == "-":
            return first - second
        elif op == "*":
            return first * second
        elif op == "/":
            return first // second
    
    def precedence(self, current_op, op_from_ops):
        if op_from_ops == "(" or op_from_ops == ")":
            return False
        if (current_op == "*" or current_op == "/") and (op_from_ops == "+" or op_from_ops == "-"):
            return False
        return True

    def calculate(self, s):
        if not s: 
            return 0
        nums, ops = [], []
        i = 0
        while i < len(s):
            c = s[i]
            if c == " ":
                i += 1
                continue
            elif c.isdigit():
                num = int(c)
                while i < len(s) - 1 and s[i + 1].isdigit():
                    num = num * 10 + int(s[i + 1])
                    i += 1
                nums.append(num)
            elif c == "(":
                ops.append(c)
            elif c == ")":
                # do the math when we encounter a ')' until '('
                while ops[-1] != "(":
                    nums.append(self.operation(ops.pop(), nums.pop(), nums.pop()))
                ops.pop()
            elif c in ["+", "-", "*", "/"]:
                while len(ops) != 0 and self.precedence(c, ops[-1]):
                    nums.append(self.operation(ops.pop(), nums.pop(), nums.pop()))
                ops.append(c)
            i += 1

        while len(ops) > 0:
            nums.append(self.operation(ops.pop(), nums.pop(), nums.pop()))

        return nums.pop()



# TEST CASE
solution = Solution()

s = "1+1"
print("TEST 1 is " + str(solution.calculate(s) == 2))

s = "6-4/2"
print("TEST 2 is " + str(solution.calculate(s) == 4))

s = "2*(5+5*2)/3+(6/2+8)"
print("TEST 3 is " + str(solution.calculate(s) == 21))