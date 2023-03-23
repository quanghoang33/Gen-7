# https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/

from collections import deque

class Solution(object):
    def shortestPath(self, grid, k):
        m, n = len(grid), len(grid[0])
        q = deque([[0, 0, 0]])
        visited = {(0, 0): 0}
        steps = 0
        
        while q:
            size = len(q)
            for _ in range(size):
                r, c, obs = q.popleft()
                if obs > k: continue
                if r == m - 1 and c == n - 1: 
                    return steps
                for r2, c2 in [[r+1, c], [r-1, c], [r, c+1], [r, c-1]]:
                    if 0 <= r2 < m and 0 <= c2 < n:
                        next_obs = obs + 1 if grid[r2][c2] == 1 else obs
                        if next_obs < visited.get((r2, c2), float('inf')):
                            visited[(r2, c2)] = next_obs
                            q.append([r2, c2, next_obs])
            steps += 1
        
        return -1

# TEST CASE
solution = Solution()

grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]]
k = 1
print(solution.shortestPath(grid, k))

grid = [[0,1,1],[1,1,1],[1,0,0]]
k = 1
print(solution.shortestPath(grid, k))