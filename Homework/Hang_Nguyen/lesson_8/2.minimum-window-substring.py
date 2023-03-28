# https://leetcode.com/problems/minimum-window-substring/
import collections

class Solution(object):
    def minWindow(self, s, t):
        target_count = collections.Counter(t)
        start, end = 0, 0
        min_window = ''
        target_len = len(t)
        
        for end in range(len(s)):
            if target_count[s[end]] > 0:
                target_len -= 1
                
            target_count[s[end]] -= 1
            while target_len == 0:
                window_len = end - start + 1
                
                if not min_window or window_len < len(min_window):
                    min_window = s[start:end + 1]
                    
                target_count[s[start]] += 1
                if target_count[s[start]] > 0:
                    target_len += 1
                start += 1
                
        return min_window

# TEST CASE
solution = Solution()

s = "ADOBECODEBANC"
t = "ABC"

print(solution.minWindow(s, t))