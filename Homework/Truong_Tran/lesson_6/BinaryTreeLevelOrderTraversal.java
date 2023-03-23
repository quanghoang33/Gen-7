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
        int queueSize;
        List<Integer> subRes;
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            subRes = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                subRes.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(subRes);
        }
        List<List<Integer>> reverseList = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            reverseList.add(res.get(i));
        }
        return reverseList;
    }
}
