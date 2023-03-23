package org.global.dev.day_7.bfs;

import org.global.dev.common.TreeNode;

public class HouseRobberIII {
	
	/**
	 * https://leetcode.com/problems/house-robber-iii/?fbclid=IwAR0hSqeGKZJr4cunLzKAkVnSzkdxR9it6A7noc1VNHRo3nTU8rzXIhKxmQQ
	 */
	
	public int rob(TreeNode root) {
		return helperRecursive(root, false);
	}
	
	public int helperRecursive(TreeNode root, boolean isParentRobbed) {
		if (root == null) {
			return 0;
		}
		if (isParentRobbed) {
			// Can not rob this;
			return helperRecursive(root.left, false) + helperRecursive(root.right, false);
		} else {
			// There two choice rob or not rob this
			int chooseRob = root.val + helperRecursive(root.right, true) + helperRecursive(root.left, true);
			int notRob = helperRecursive(root.right, false) + helperRecursive(root.left, false);
			return Math.max(chooseRob, notRob);
		}
	}
	
	public int robOp(TreeNode root) {
		int[] res = helperOptimize(root);
		return Math.max(res[0], res[1]);
	}
	
	public int[] helperOptimize(TreeNode root) {
		// return [rob this node, not rob this node]
		if (root == null) {
			return new int[] { 0, 0 };
		}
		
		int[] left = helperOptimize(root.left);
		int[] right = helperOptimize(root.right);
		
		int notRob = Math.max(left[1], left[0]) + Math.max(right[0], right[1]);
		int rob = root.val + left[1] + right[1];
		
		return new int[]{rob, notRob};
	}
	
	
}
