class Solution:
    def isHappy(self, n: int) -> bool:
        def get_next_n(num: int) -> int:
            res = 0
            while num:
                num, digit = divmod(num, 10)
                res += digit ** 2

            return res

        slow = n
        fast = get_next_n(n)
        while fast != 1 and fast != slow:
            slow = get_next_n(slow)
            fast = get_next_n(get_next_n(fast))

        return fast == 1

if __name__ == "__main__":
    sol = Solution()
    print(sol.isHappy(19) == True)
    print(sol.isHappy(2) == False)