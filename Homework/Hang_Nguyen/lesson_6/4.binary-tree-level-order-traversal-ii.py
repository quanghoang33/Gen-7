# https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
class TreeNode():
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
    
class Solution():
    def levelOrderBottom(self, root):
        stack = [(root, 0)]
        res = []
        while stack:
            node, level = stack.pop()
            if node:
                if len(res) < level+1:
                    res.insert(0, [])
                res[-(level+1)].append(node.val)
                stack.append((node.right, level+1))
                stack.append((node.left, level+1))
        return res

# TEST CASE
solution = Solution()

left = TreeNode(9)
right = TreeNode(20, TreeNode(15), TreeNode(7))
tree = TreeNode(3, left, right)

print(str(solution.levelOrderBottom(tree)))

tree = TreeNode(1)
print(str(solution.levelOrderBottom(tree)))
