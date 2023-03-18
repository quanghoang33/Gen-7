package org.global.dev.day_7.dfs;

import org.global.dev.common.TreeNode;

public class LongestUnivaluePath {
	
	int max = Integer.MIN_VALUE;
	
	public int longestUnivaluePath(TreeNode root) {
		if (root == null) return 0;
		helper(root);
		return max;
	}
	
	public int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.left);
		int right = helper(root.right);
		
		if (root.left != null && root.val == root.left.val) {
			left = left + 1;
		}
		
		if (root.right != null && root.val == root.right.val) {
			right = right + 1;
		}
		
		max = Math.max(max, right + left);
		return Math.max(right, left);
		
	}
	
}

