from functools import cache


class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        @cache
        def dp(x: int, y: int) -> int:
            if x + y == 0: return 0
            if x + y == 2: return 2

            return min(dp(abs(x - 1), abs(y - 2)), dp(abs(x - 2), abs(y - 1))) + 1

        return dp(abs(x), abs(y))

if __name__ == "__main__":
    sol = Solution()