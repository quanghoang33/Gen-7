import random


class RandomizedSet:

    def __init__(self):
        self.dict = dict()
        self.data = []

    def insert(self, val: int) -> bool:
        if val in self.dict:
            return False

        self.dict[val] = len(self.data)
        self.data.append(val)
        return True

    def remove(self, val: int) -> bool:
        if val not in self.dict:
            return False

        last_elt = self.data[-1]
        remove_idx = self.dict[val]
        self.data[remove_idx], self.data[-1] = self.data[-1], self.data[remove_idx]
        self.dict[last_elt] = remove_idx
        self.data.pop()
        del self.dict[val]

        return True

    def getRandom(self) -> int:
        return random.choice(self.data)

if __name__ == "__main__":
    obj = RandomizedSet()
    print(obj.insert(1) == True)
    print(obj.remove(2) == False)
    print(obj.insert(2) == True)
    print(obj.getRandom() in [1, 2])
    print(obj.remove(1) == True)
    print(obj.insert(2) == False)
    print(obj.getRandom() == 2)