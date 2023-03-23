import java.util.HashMap;
import java.util.Map;

public class HouseRobber {

    Map<TreeNode, Integer> visitedMap = new HashMap<>();

    public int rob(TreeNode root) {
        return robSub(root);
    }

    private int robSub(TreeNode root) {
        if (root == null) return 0;
        if (visitedMap.containsKey(root)) return visitedMap.get(root);

        int val = root.val;

        if (root.left != null) {
            val += robSub(root.left.left) + robSub(root.left.right);
        }

        if (root.right != null) {
            val += robSub(root.right.left) + robSub(root.right.right);
        }

        val = Math.max(val, robSub(root.left) + robSub(root.right));
        visitedMap.put(root, val);

        return val;
    }
}
