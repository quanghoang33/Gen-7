import random
from typing import List


class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        # def dis(point: List[int]) -> int:
        #     return point[0] ** 2 + point[1] ** 2

        # max_heap = [(-dis(points[i]), i) for i in range(k)]
        # heapq.heapify(max_heap)
        # for i in range(k, len(points)):
        #     heapq.heappushpop(max_heap, (-dis(points[i]), i))

        # return [points[max_heap[i][1]] for i in range(k)]

        if k == len(points):
            return points

        distance = [(points[i][0] ** 2 + points[i][1] ** 2, i) for i in range(len(points))]

        def partition(l: int, r: int, p: int) -> int:
            p_val = distance[p][0]
            distance[p], distance[r] = distance[r], distance[p]
            cur_p = l
            for i in range(l, r):
                if distance[i][0] < p_val:
                    distance[i], distance[cur_p] = distance[cur_p], distance[i]
                    cur_p += 1
            distance[cur_p], distance[r] = distance[r], distance[cur_p]
            return cur_p

        def select(l: int, r: int) -> int:
            if l == r:
                return l

            p = random.randint(l, r)
            p = partition(l, r, p)

            if p > k:
                return select(l, p - 1)
            elif p < k:
                return select(p + 1, r)
            else:
                return p

        pivot = select(0, len(distance) - 1)
        res = []
        for _, d in enumerate(distance[:pivot]):
            res.append(points[d[1]])

        return res

if __name__ == "__main__":
    sol = Solution()
    print(sol.kClosest([[1, 3], [-2, 2]], 1) == [[-2, 2]])
    print(sol.kClosest([[3, 3], [5, -1], [-2, 4]], 2) == [[3, 3], [-2, 4]])