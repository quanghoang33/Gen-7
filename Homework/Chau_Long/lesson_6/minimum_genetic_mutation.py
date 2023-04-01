from collections import deque
from typing import List


class Solution:
    def minMutation(self, start: str, end: str, bank: List[str]) -> int:
        bank, mutated = set(bank), set()
        adn = ['A', 'C', 'G', 'T']
        q = deque([start])
        mutate = 0

        while q:
            n = len(q)
            for _ in range(n):
                gene = q.popleft()
                if gene in mutated: continue
                if gene == end: return mutate

                mutated.add(gene)
                for i in range(8):
                    for c in adn:
                        tmp = gene[:i] + c + gene[i + 1:]
                        if tmp in bank:
                            q.append(tmp)
            mutate += 1
        return -1

if __name__ == "__main__":
    sol = Solution()
    print(sol.minMutation("AACCGGTT", "AACCGGTA", ["AACCGGTA"]) == 1)
    print(sol.minMutation("AACCGGTT", "AAACGGTA", ["AACCGGTA","AACCGCTA","AAACGGTA"]) == 2)