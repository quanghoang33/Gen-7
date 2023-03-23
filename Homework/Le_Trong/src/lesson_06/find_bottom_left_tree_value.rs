use std::{borrow::Borrow, cell::RefCell, collections::VecDeque, rc::Rc};

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

fn bottom_left_tree_value(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let mut queue = VecDeque::new();
    let mut leftmost = root.as_ref().unwrap().as_ref().borrow().val;
    queue.push_back(root);

    while !queue.is_empty() {
        let len = queue.len();
        for _ in 0..len {
            if let Some(Some(node)) = queue.pop_front() {
                if let Some(left) = &node.as_ref().borrow().left {
                    queue.push_back(Some(Rc::clone(&left)));
                }
                if let Some(right) = &node.as_ref().borrow().right {
                    queue.push_back(Some(Rc::clone(&right)));
                }
            }
        }

        if !queue.is_empty() {
            if let Some(node) = queue.front().unwrap().as_ref() {
                leftmost = node.as_ref().borrow().val;
            }
        }
    }

    leftmost
}

#[cfg(test)]
mod tests {
    use std::{cell::RefCell, rc::Rc};

    use super::{bottom_left_tree_value, TreeNode};

    #[test]
    fn test_bottom_left_tree_value() {
        let mut root = TreeNode::new(1);
        let left = Rc::new(RefCell::new(TreeNode::new(2)));
        let right = Rc::new(RefCell::new(TreeNode::new(3)));
        let left_left = Rc::new(RefCell::new(TreeNode::new(4)));
        let right_left = Rc::new(RefCell::new(TreeNode::new(5)));
        let right_right = Rc::new(RefCell::new(TreeNode::new(6)));
        let right_left_left = Rc::new(RefCell::new(TreeNode::new(7)));
        root.left = Some(Rc::clone(&left));
        root.right = Some(Rc::clone(&right));
        left.as_ref().borrow_mut().left = Some(Rc::clone(&left_left));
        right.as_ref().borrow_mut().left = Some(Rc::clone(&right_left));
        right.as_ref().borrow_mut().right = Some(Rc::clone(&right_right));
        right_left.as_ref().borrow_mut().left = Some(Rc::clone(&right_left_left));
        let result = bottom_left_tree_value(Some(Rc::new(RefCell::new(root))));
        assert_eq!(7, result);

        let mut root = TreeNode::new(2);
        let left = Rc::new(RefCell::new(TreeNode::new(1)));
        let right = Rc::new(RefCell::new(TreeNode::new(3)));
        root.left = Some(Rc::clone(&left));
        root.right = Some(Rc::clone(&right));
        let result = bottom_left_tree_value(Some(Rc::new(RefCell::new(root))));
        assert_eq!(1, result);

        let root = TreeNode::new(1);
        let result = bottom_left_tree_value(Some(Rc::new(RefCell::new(root))));
        assert_eq!(1, result);
    }
}
