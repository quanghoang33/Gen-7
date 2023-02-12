def singleNumber(nums):
    result = 0
    for i in range(0, len(nums)):
        result ^= nums[i]
    return result


# TEST CASE
nums = [2,2,1]
res = singleNumber(nums)
print("TEST 1 is " + str(res == 1))

nums = [4,1,2,1,2]
res = singleNumber(nums)
print("TEST 2 is " + str(res == 4))

nums = [1]
res = singleNumber(nums)
print("TEST 3 is " + str(res == 1))
