# https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
class TreeNode(object):
    def __init__(self, x, left = None, right = None):
        self.val = x
        self.left = left
        self.right = right

class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        small = min(p, q)
        large = max(p, q)
        while root:
            if root.val > large:
                root = root.left
            elif root.val < small:
                root = root.right
            else:
                return root
        return None


# TEST CASE
solution = Solution()

left = TreeNode(2, TreeNode(0), TreeNode(4))
right = TreeNode(8, TreeNode(7), TreeNode(9))
root = TreeNode(6, left, right)

solution.lowestCommonAncestor(root, 7, 9)