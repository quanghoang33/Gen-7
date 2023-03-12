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
    fn dfs(node: Option<&Rc<RefCell<TreeNode>>>) -> i32 {
        if let Some(node) = node {
            let node_borrow = node.as_ref().borrow();
            let mut val = node_borrow.val;
            if let Some(left) = node_borrow.left.as_ref() {
                match (
                    left.as_ref().borrow().left.as_ref(),
                    left.as_ref().borrow().right.as_ref(),
                ) {
                    (None, Some(left_right)) => {
                        val += dfs(None) + dfs(Some(&left_right));
                    }
                    (Some(left_left), None) => {
                        val += dfs(Some(&left_left)) + dfs(None);
                    }
                    (Some(left_left), Some(left_right)) => {
                        val += dfs(Some(&left_left)) + dfs(Some(&left_right));
                    }
                    _ => (),
                }
            }

            if let Some(right) = node_borrow.right.as_ref() {
                match (
                    right.as_ref().borrow().left.as_ref(),
                    right.as_ref().borrow().right.as_ref(),
                ) {
                    (None, Some(right_right)) => {
                        val += dfs(None) + dfs(Some(&right_right));
                    }
                    (Some(right_left), None) => {
                        val += dfs(Some(&right_left)) + dfs(None);
                    }
                    (Some(right_left), Some(right_right)) => {
                        val += dfs(Some(&right_left)) + dfs(Some(&right_right));
                    }
                    _ => (),
                }
            }

            return val.max(dfs(node_borrow.left.as_ref()) + dfs(node_borrow.right.as_ref()));
        } else {
            return 0;
        }
    }

    return dfs(root.as_ref());
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
