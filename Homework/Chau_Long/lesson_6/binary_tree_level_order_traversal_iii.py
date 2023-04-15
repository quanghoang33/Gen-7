from collections import deque
from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        res = []
        q = deque([root])

        while q:
            size = len(q)
            temp = []
            for _ in range(size):
                node = q.popleft()
                if not node:
                    continue
                temp.append(node.val)
                q.extend([node.left, node.right])
            if temp:
                res.append(temp)

        return res[::-1]