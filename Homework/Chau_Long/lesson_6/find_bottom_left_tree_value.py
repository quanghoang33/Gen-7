from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def findBottomLeftValue(self, root: Optional[TreeNode]) -> int:
        last_left, last_row = 0, -1

        stack = [(root, 0)]
        while stack:
            cur_node, row = stack.pop()

            if row > last_row:
                last_row = row
                last_left = cur_node.val

            if cur_node.right:
                stack.append((cur_node.right, row + 1))

            if cur_node.left:
                stack.append((cur_node.left, row + 1))

        return last_left