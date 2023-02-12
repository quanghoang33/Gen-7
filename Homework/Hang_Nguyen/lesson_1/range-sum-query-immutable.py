class NumArray:

    def __init__(self, nums):
        self.sumLst = []
        s = 0
        for num in nums:
            s += num
            self.sumLst.append(s)

    
    def sumRange(self, left, right):
        if left > 0 and right > 0:
            return self.sumLst[right] - self.sumLst[left-1]
        return self.sumLst[right]


# TEST CASE
obj = NumArray([-2, 0, 3, -5, 2, -1])
output = [obj.sumRange(0, 2), obj.sumRange(2, 5), obj.sumRange(0,5)]
print("OUTPUT is " + str(output))