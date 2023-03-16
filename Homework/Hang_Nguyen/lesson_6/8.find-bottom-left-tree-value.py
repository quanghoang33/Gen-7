# https://leetcode.com/problems/find-bottom-left-tree-value/

from collections import deque


class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution(object):
    def findBottomLeftValue(self, root):
        q  = deque()
        q.append(root)
        res = 0
        while q:
            node = q.popleft()
            res = node.val            
            if node.right:
                q.append(node.right)

            if node.left:
                q.append(node.left)        
        return res


# TEST CASE
solution = Solution()

left = TreeNode(1)
right = TreeNode(3)
root = TreeNode(2, left, right)
print(solution.findBottomLeftValue(root))

left = TreeNode(2, TreeNode(4))
right = TreeNode(3, TreeNode(5, TreeNode(7)), TreeNode(6))
root = TreeNode(1, left, right)
print(solution.findBottomLeftValue(root))
