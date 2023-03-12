# https://leetcode.com/problems/insert-delete-getrandom-o1/
import random

class RandomizedSet:

    def __init__(self):
        self.map = {}
        self.arr = []
        

    def insert(self, val: int) -> bool:
        if val in self.map:
            return False
        n = len(self.arr)
        self.map[val] = n
        self.arr.append(val)
        return True

    def remove(self, val: int) -> bool:
        if val in self.map:
            index = self.map[val]
            # swap val with the last item in list
            self.arr[index], self.arr[-1] = self.arr[-1], self.arr[index]
            # then remove the last item
            del self.arr[-1]
            self.map.pop(val)
            # update the last item with new index
            self.map[self.arr[index]] = index
            return True
        return False

    def getRandom(self) -> int:
        return random.choice(self.arr)

#TEST CASE
randomizedSet = RandomizedSet()
print(randomizedSet.insert(1))
print(randomizedSet.remove(2))
print(randomizedSet.insert(2))
print(randomizedSet.getRandom())
print(randomizedSet.remove(1))
print(randomizedSet.insert(2))
print(randomizedSet.getRandom())

