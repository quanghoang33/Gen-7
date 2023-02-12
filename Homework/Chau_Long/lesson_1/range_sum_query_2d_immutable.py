from typing import List


class NumMatrix:

    def __init__(self, matrix: List[List[int]]):
        m, n = len(matrix), len(matrix[0])
        self.prefix_sum = [[0 for _ in range(n + 1)] for _ in range(m + 1)]

        for i in range(m):
            for j in range(n):
                self.prefix_sum[i + 1][j + 1] = self.prefix_sum[i][j + 1] + self.prefix_sum[i + 1][j] - \
                                                self.prefix_sum[i][j] + matrix[i][j]

    def sumRegion(self, row1: int, col1: int, row2: int, col2: int) -> int:
        return self.prefix_sum[row2 + 1][col2 + 1] - self.prefix_sum[row1][col2 + 1] - self.prefix_sum[row2 + 1][col1] + \
            self.prefix_sum[row1][col1]

if __name__ == "__main__":
    mat = NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]])
    print(mat.sumRegion(2, 1, 4, 3) == 8)
    print(mat.sumRegion(1, 1, 2, 2) == 11)
    print(mat.sumRegion(1, 2, 2, 4) == 12)