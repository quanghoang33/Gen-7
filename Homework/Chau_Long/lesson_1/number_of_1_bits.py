class Solution:
    def hammingWeight(self, n: int) -> int:
        res = 0
        while n:
            res += 1
            n &= n - 1

        return res

if __name__ == "__main__":
    sol = Solution()
    print(sol.hammingWeight(int("00000000000000000000000000001011", 2)) == 3)
    print(sol.hammingWeight(int("00000000000000000000000010000000", 2)) == 1)
    print(sol.hammingWeight(int("11111111111111111111111111111101", 2)) == 31)