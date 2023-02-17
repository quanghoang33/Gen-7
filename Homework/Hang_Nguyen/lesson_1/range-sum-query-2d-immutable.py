class NumMatrix:

    def __init__(self, matrix):
        rows =  len(matrix)
        cols = len(matrix[0])
        self.dp = [[0] * (cols + 1) for r in range(rows + 1)]
        
        for r in range(0, rows):
            prefix = 0
            for c in range(0, cols):
                prefix += matrix[r][c]
                above = self.dp[r][c+1]
                self.dp[r+1][c+1] = prefix + above
        

    def sumRegion(self, row1, col1, row2, col2):
        r1, r2 = row1 + 1, row2 + 1
        c1, c2 = col1 + 1, col2 + 1

        bottomRightRegion = self.dp[r2][c2]
        aboveRegion = self.dp[r1 - 1][c2]
        leftRegion = self.dp[r2][c1 - 1]
        topLeftRegion = self.dp[r1 - 1][c1 - 1]

        return bottomRightRegion - aboveRegion - leftRegion + topLeftRegion


# TEST CASE
numMatrix = NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]])

t = numMatrix.sumRegion(2, 1, 4, 3)
print("TEST 1 is " + str(t == 8))

t = numMatrix.sumRegion(1, 1, 2, 2)
print("TEST 2 is " + str(t == 11))

t = numMatrix.sumRegion(1, 2, 2, 4)
print("TEST 3 is " + str(t == 12))