# https://leetcode.com/problems/k-closest-points-to-origin/
import heapq

class Solution:

    def distance(self, x, y):
        return x ** 2 + y ** 2

    def kClosest(self, points, k):
        dict = {}
        for i in range(len(points)):
            dict[i] = self.distance(points[i][0], points[i][1])
        
        heap = list(dict.values())
        heapq.heapify(heap)
        closest = []
        while k > 0:
            closestDistance = heapq.heappop(heap)
            closest.append(closestDistance)
            k -= 1
        
        output = []
        for index, distance in dict.items():
            if distance in closest:
                output.append(points[index])
        return output


# TEST CASE
solution = Solution()

points = [[1,3],[-2,2]]
k = 1

print("TEST 1 is " + str(solution.kClosest(points, k)))

points = [[3,3],[5,-1],[-2,4]]
k = 2

print("TEST 2 is " + str(solution.kClosest(points, k)))
