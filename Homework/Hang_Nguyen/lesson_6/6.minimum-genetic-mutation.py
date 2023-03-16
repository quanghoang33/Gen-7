# https://leetcode.com/problems/minimum-genetic-mutation/

from collections import deque

class Solution():
    def minMutation(self, startGene, endGene, bank):
        def checkNeighbor(a,b):
            return sum([1 for i in range(len(a)) if a[i]!=b[i]]) == 1
        
        q = deque([startGene])
        visited = set()
        mutations = 0
        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                if cur == endGene:
                    return mutations
                if cur not in visited:
                    visited.add(cur)
                    for nei in bank:
                        if checkNeighbor(cur,nei):
                            q.append(nei)
            mutations += 1
        return -1

# TEST CASE
solution = Solution()

startGene = "AACCGGTT"
endGene = "AACCGGTA"
bank = ["AACCGGTA"]
print(solution.minMutation(startGene, endGene, bank))

startGene = "AACCGGTT"
endGene = "AAACGGTA"
bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
print(solution.minMutation(startGene, endGene, bank))