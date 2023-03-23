package org.global.dev.day_7.bfs;

import org.global.dev.common.TreeNode;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null) return new ArrayList<>();
		List<List<Integer>> levels = new ArrayList<>();
		Queue<TreeNode> deque = new ArrayDeque<>();
		int level = 0;
		deque.add(root);
		
		while (!deque.isEmpty()) {
			levels.add(new ArrayList<>());
			int levelLen = deque.size();
			for (int i = 0; i < levelLen; i++) {
				
				TreeNode remove = deque.remove();
				levels.get(level).add(remove.val);
				
				if (remove.left != null) deque.add(remove.left);
				if (remove.right != null) deque.add(remove.right);
			}
			level++;
		}
		
		Collections.reverse(levels);
		return levels;
		
	}

}
