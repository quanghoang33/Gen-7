use std::{cell::RefCell, rc::Rc};

// Definition for a binary tree node.
#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
    pub val: i32,
    pub left: Option<Rc<RefCell<TreeNode>>>,
    pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    #[inline]
    pub fn new(val: i32) -> Self {
        TreeNode {
            val,
            left: None,
            right: None,
        }
    }
}

pub fn rob(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    fn dfs(node: Option<&Rc<RefCell<TreeNode>>>) -> (i32, i32) {
        if let Some(node) = node {
            let node_borrow = node.as_ref().borrow();

            let left = dfs(node_borrow.left.as_ref());
            let right = dfs(node_borrow.right.as_ref());

            return (
                node_borrow.val + left.1 + right.1,
                left.0.max(left.1) + right.0.max(right.1),
            );
        } else {
            return (0, 0);
        }
    }

    let (root_is_robbed, root_is_not_robbed) = dfs(root.as_ref());
    return root_is_robbed.max(root_is_not_robbed);
}

#[cfg(test)]
mod tests {
    use std::{cell::RefCell, rc::Rc};

    use super::{rob, TreeNode};

    #[test]
    fn test_rob() {
        let root = Rc::new(RefCell::new(TreeNode::new(3)));
        let left = Rc::new(RefCell::new(TreeNode::new(4)));
        let right = Rc::new(RefCell::new(TreeNode::new(5)));
        let left_left = Rc::new(RefCell::new(TreeNode::new(1)));
        let left_right = Rc::new(RefCell::new(TreeNode::new(3)));
        let right_left = Rc::new(RefCell::new(TreeNode::new(1)));
        root.borrow_mut().left = Some(Rc::clone(&left));
        root.borrow_mut().right = Some(Rc::clone(&right));
        left.borrow_mut().left = Some(Rc::clone(&left_left));
        left.borrow_mut().right = Some(Rc::clone(&left_right));
        right.borrow_mut().left = Some(Rc::clone(&right_left));

        let result = rob(Some(Rc::clone(&root)));

        assert_eq!(result, 9);
    }
}
