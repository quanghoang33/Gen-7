from typing import List

class NumArray:

    def __init__(self, nums: List[int]):
        self.ps = [0] + nums
        for i in range(1, len(self.ps)):
            self.ps[i] += self.ps[i - 1]

    def sumRange(self, left: int, right: int) -> int:
        return self.ps[right + 1] - self.ps[left]

if __name__ == "__main__":
    arr = NumArray([-2, 0, 3, -5, 2, -1])
    print(arr.sumRange(0, 2) == 1)
    print(arr.sumRange(2, 5) == -1)
    print(arr.sumRange(0, 5) == -3)