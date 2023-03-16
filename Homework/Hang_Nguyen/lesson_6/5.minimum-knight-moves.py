# https://leetcode.com/problems/minimum-knight-moves/
class Solution():
    def minKnightMoves(self, x, y):
        cache = {(0, 0): 0, (1, 0): 3, (0, 1): 3}
        def dfs(x, y):
            if (x, y) in cache: return cache[(x, y)]
            res = min(dfs(abs(x-1), abs(y-2)), dfs(abs(x-2), abs(y-1))) + 1
            cache[(x, y)] = res
            return res
        return dfs(abs(x), abs(y))


# TEST CASE
solution = Solution()

x = 2
y = 1
print(str(solution.minKnightMoves(x,y)))

x = 5
y = 5
print(str(solution.minKnightMoves(x,y)))