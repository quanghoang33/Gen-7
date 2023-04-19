# https://leetcode.com/problems/as-far-from-land-as-possible/
from collections import deque


class Solution(object):
    def maxDistance(self, grid):
        n = len(grid)
        q = deque()
        for r in range(n):
            for c in range(n):
                if grid[r][c]:
                    q.append([r,c])

        res = -1
        direct = [[0,1], [0,-1], [1,0], [-1,0]] #right, left, down, up

        while q:
            r, c = q.popleft()

            res = grid[r][c]
            for dr, dc in direct:
                newR, newC = r + dr, c + dc
                if min(newR, newC) >= 0 and max(newR, newC) < n and grid[newR][newC] == 0: # check out of bound and water cell
                    q.append([newR, newC])
                    grid[newR][newC] = grid[r][c] + 1
        
        return res - 1 if res > 1 else - 1


# TEST CASE
solution = Solution()

grid = [[1,0,1],[0,0,0],[1,0,1]]
print(solution.maxDistance(grid))

grid = [[1,0,0],[0,0,0],[0,0,0]]
print(solution.maxDistance(grid))