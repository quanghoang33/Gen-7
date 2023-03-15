# https://leetcode.com/problems/house-robber-iii/
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def rob(self, root):
        def robPlan(node):
            if not node: return (0, 0)
            left, right = robPlan(node.left), robPlan(node.right)
            now = node.val + left[1] + right[1]
            later = max(left) + max(right)
            return (now, later)
            
        return max(robPlan(root))


# TEST CASE
solution = Solution()

left = TreeNode(2, None, TreeNode(3))
right = TreeNode(3, None, TreeNode(1))
root = TreeNode(3, left, right)

print("TEST 1 is " + str(solution.rob(root)))

left = TreeNode(4, TreeNode(1), TreeNode(3))
right = TreeNode(5, None, TreeNode(1))
root = TreeNode(3, left, right)

print("TEST 2 is " + str(solution.rob(root)))