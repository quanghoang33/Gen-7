# https://leetcode.com/problems/longest-univalue-path/

class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution(object):

    def __init__(self):
        self.path = 0

    def countPath(self, treeNode, val):
        if not treeNode:
            return 0
        
        pathLeft = self.countPath(treeNode.left, treeNode.val)
        pathRight = self.countPath(treeNode.right, treeNode.val)

        self.path = max(self.path, pathLeft + pathRight)

        if val == treeNode.val:
            return max(pathLeft, pathRight) + 1
        return 0

    def longestUnivaluePath(self, root):
        if not root:
            return 0
        self.countPath(root, root.val)
        return self.path


# TEST CASE
solution = Solution()

left = TreeNode(4, TreeNode(1), TreeNode(1))
right = TreeNode(5, None, TreeNode(5))
tree = TreeNode(5, left, right)
print("TEST 1 is " + str(solution.longestUnivaluePath(tree)))

left = TreeNode(4, TreeNode(4), TreeNode(4))
right = TreeNode(5, None, TreeNode(5))
tree = TreeNode(1, left, right)
print("TEST 2 is " + str(solution.longestUnivaluePath(tree)))