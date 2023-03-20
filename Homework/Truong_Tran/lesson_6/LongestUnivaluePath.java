public class LongestUnivaluePath {

    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return max;
    }
    private int helper(TreeNode curr){
        if(curr == null) return 0;
        int left = helper(curr.left), right = helper(curr.right);
        if(curr.left != null && curr.val == curr.left.val)
            left += 1;
        else
            left = 0;
        if(curr.right != null && curr.val == curr.right.val)
            right += 1;
        else
            right = 0;
        max = Math.max(max, left+right);
        return Math.max(left, right);
    }
}
