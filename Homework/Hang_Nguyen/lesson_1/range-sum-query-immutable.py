class NumArray:

    def __init__(self, nums):
        self.sumLst = []
        s = 0
        for num in nums:
            s += num
            self.sumLst.append(s)

    
    def sumRange(self, left, right):
        if left > 0:
            return self.sumLst[right] - self.sumLst[left-1]
        return self.sumLst[right]


# TEST CASE
numArray = NumArray([-2, 0, 3, -5, 2, -1])

t = numArray.sumRange(0, 2)
print("TEST 1 is " + str(t == 1))

t = numArray.sumRange(2, 5)
print("TEST 2 is " + str(t == -1))

t = numArray.sumRange(0, 5)
print("TEST 3 is " + str(t == -3))