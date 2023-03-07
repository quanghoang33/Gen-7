import collections
from typing import List


class Solution:
    def numSubmatrixSumTarget(self, matrix: List[List[int]], target: int) -> int:
        m, n = len(matrix), len(matrix[0])
        for row in matrix:
            for i in range(n - 1):
                row[i + 1] += row[i]
        res = 0
        for i in range(n):
            for j in range(i, n):
                c = collections.defaultdict(int)
                cur_sum, c[0] = 0, 1
                for k in range(m):
                    cur_sum += matrix[k][j] - (matrix[k][i - 1] if i > 0 else 0)
                    res += c[cur_sum - target]
                    c[cur_sum] += 1
        return res

if __name__ == "__main__":
    sol = Solution()
    print(sol.numSubmatrixSumTarget([[0,1,0],[1,1,1],[0,1,0]], 0) == 4)
    print(sol.numSubmatrixSumTarget([[1,-1],[-1,1]], 0) == 5)
    print(sol.numSubmatrixSumTarget([[904]], 0) == 0)
