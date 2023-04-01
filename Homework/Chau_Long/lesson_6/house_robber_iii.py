from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def rob(self, root: Optional[TreeNode]) -> int:
        def dfs(node: Optional[TreeNode]) -> (int, int):
            if not node: return (0, 0)

            left, right = dfs(node.left), dfs(node.right)
            now = max(left[0] + right[0], node.val + left[1] + right[1])

            return (now, left[0] + right[0])

        return max(dfs(root))