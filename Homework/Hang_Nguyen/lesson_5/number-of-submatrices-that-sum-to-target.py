# https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
class Solution:
    def numSubmatrixSumTarget(self, matrix, target):
        rows = len(matrix)
        columns = len(matrix[0])

        for r in range(rows):
            for c in range(1, columns):
                matrix[r][c] += matrix[r][c-1]
        
        count = 0
        for c1 in range(columns):
            for c2 in range(c1, columns):
                hashmap = {}
                hashmap[0] = 1
                s = 0
                for r in range(rows):
                    s += matrix[r][c2] - (matrix[r][c1-1] if c1 > 0 else 0)
                    count += hashmap.get(s-target, 0)
                    hashmap[s] = hashmap.get(s,0) + 1
        return count

#TEST CASE
solution = Solution()

matrix = [[0,1,0],[1,1,1],[0,1,0]]
target = 0
print(solution.numSubmatrixSumTarget(matrix, target))

matrix = [[1,-1],[-1,1]]
target = 0
print(solution.numSubmatrixSumTarget(matrix, target))

matrix = [[904]]
target = 0
print(solution.numSubmatrixSumTarget(matrix, target))