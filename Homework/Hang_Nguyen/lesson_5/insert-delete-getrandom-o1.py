# https://leetcode.com/problems/insert-delete-getrandom-o1/
import random

class RandomizedSet:

    def __init__(self):
        self.map = {}
        

    def insert(self, val: int) -> bool:
        if val in self.map:
            return False
        self.map[val] = val
        return True

    def remove(self, val: int) -> bool:
        if val in self.map:
            self.map.pop(val)
            return True
        return False

    def getRandom(self) -> int:
        vals = list(self.map.values())
        return random.choice(vals)

#TEST CASE
randomizedSet = RandomizedSet()
print(randomizedSet.insert(1))
print(randomizedSet.remove(2))
print(randomizedSet.insert(2))
print(randomizedSet.getRandom())
print(randomizedSet.remove(1))
print(randomizedSet.insert(2))
print(randomizedSet.getRandom())

