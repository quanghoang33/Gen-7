def getBit(x, position):
        return (x >> position) & 1

def hammingWeight(n):
    i = 0
    count = 0
    while i < 32:
        if getBit(n, i) == 1:
            count += 1
        i += 1
    return count

# TEST CASE
n = int('00000000000000000000000000001011', 2)
res = hammingWeight(n)
print("TEST 1 is " + str(res == 3))

n = int('00000000000000000000000010000000', 2)
res = hammingWeight(n)
print("TEST 2 is " + str(res == 1))

n = int('11111111111111111111111111111101', 2)
res = hammingWeight(n)
print("TEST 3 is " + str(res == 31))
