class Solution:
    def calculate(self, s: str) -> int:
        def calc(it):
            def update(op, v):
                if op == "+": stack.append(v)
                if op == "-": stack.append(-v)
                if op == "*": stack.append(stack.pop() * v)
                if op == "/": stack.append(int(stack.pop() / v))

            num, stack, sign = 0, [], "+"

            while it < len(s):
                if s[it].isdigit():
                    num = num * 10 + int(s[it])
                elif s[it] in "+-*/":
                    update(sign, num)
                    num, sign = 0, s[it]
                elif s[it] == "(":
                    num, j = calc(it + 1)
                    it = j - 1
                elif s[it] == ")":
                    update(sign, num)
                    return sum(stack), it + 1
                it += 1
            update(sign, num)
            return sum(stack)

        return calc(0)

if __name__ == "__main__":
    sol = Solution()
    print(sol.calculate("1+1") == 2)
    print(sol.calculate("6-4/2") == 4)
    print(sol.calculate("2*(5+5*2)/3+(6/2+8)") == 21)