class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stack = []
        for c in s:
            if stack and stack[-1][0] == c:
                stack[-1][1] += 1
            else:
                stack.append([c, 1])

            if stack[-1][1] == k:
                stack.pop()
        return ''.join([e[0] * e[1] for e in stack])

if __name__ == "__main__":
    sol = Solution()
    print(sol.removeDuplicates("abcd", 2) == "abcd")
    print(sol.removeDuplicates("deeedbbcccbdaa", 3) == "aa")
    print(sol.removeDuplicates("pbbcggttciiippooaais", 2) == "ps")
