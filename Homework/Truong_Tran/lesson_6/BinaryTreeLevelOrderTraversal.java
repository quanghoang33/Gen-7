import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return new ArrayList<>();
        }
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> subRes = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                subRes.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            res.add(subRes);
            queue.addAll(next);
        }
        List<List<Integer>> res1 = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            res1.add(res.get(i));
        }
        return res1;
    }
}
