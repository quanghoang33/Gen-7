import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FindBottomLeftValue {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int res = root.val;
        int queueSize;
        TreeNode node;
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            res = queue.peek().val;
            for (int i = 0; i < queueSize; i++) {
                node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

}
