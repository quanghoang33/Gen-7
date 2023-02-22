# https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stack = []

        for char in s:
            if stack and stack[-1][0] == char:
                stack[-1][1] += 1
            else:
                stack.append([char, 1])
            
            if stack[-1][1] == k:
                stack.pop()
        
        res = ''
        for char, count in stack:
            res += (char * count)
        return res
    


#TEST CASE
solution = Solution()

s = "abcd"
print("TEST 1 is " + str(solution.removeDuplicates(s, 2) == "abcd"))

s = "deeedbbcccbdaa"
print("TEST 2 is " + str(solution.removeDuplicates(s, 3) == "aa"))

s = "pbbcggttciiippooaais"
print("TEST 3 is " + str(solution.removeDuplicates(s, 2) == "ps"))