class Solution:
    def calculate(self, s: str) -> int:
        stack = []
        operand, res, sign = 0, 0, 1

        for ch in s:
            if ch.isdigit():
                operand = (operand * 10) + int(ch)
            elif ch == '+':
                res += sign * operand
                sign = 1
                operand = 0
            elif ch == '-':
                res += sign * operand
                sign = -1
                operand = 0
            elif ch == '(':
                stack.append(res)
                stack.append(sign)
                sign = 1
                res = 0
            elif ch == ')':
                res += sign * operand
                res *= stack.pop()
                res += stack.pop()
                operand = 0

        return res + sign * operand

if __name__ == "__main__":
    sol = Solution()
    print(sol.calculate("1 + 1") == 2)
    print(sol.calculate(" 2-1 + 2 ") == 3)
    print(sol.calculate("(1+(4+5+2)-3)+(6+8)") == 23)