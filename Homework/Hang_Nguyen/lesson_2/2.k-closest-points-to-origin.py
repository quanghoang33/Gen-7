# https://leetcode.com/problems/k-closest-points-to-origin/
import heapq

class Solution:
    def distance(self, x, y): 
        return x ** 2 + y ** 2

    def kClosest(self, points, k):
        heap = []
        for i, (x, y) in enumerate(points):
            d = self.distance(x, y)
            if len(heap) == k:
                heapq.heappushpop(heap, (-d, i))
            else: 
                heapq.heappush(heap, (-d, i))

        output = []
        for (d, i) in heap:
            output.append(points[i])
        return output


# TEST CASE
solution = Solution()

points = [[1,3],[-2,2]]
k = 1

print("TEST 1 is " + str(solution.kClosest(points, k)))

points = [[3,3],[5,-1],[-2,4]]
k = 2

print("TEST 2 is " + str(solution.kClosest(points, k)))
